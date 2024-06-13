package com.hubgamers.api.controller;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.hubgamers.api.mapper.UserMapper;
import com.hubgamers.api.model.dto.UserDTO;
import com.hubgamers.api.response.ResponseJson;
import com.hubgamers.api.service.UserService;
import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.model.checkout.Session;
import com.stripe.net.ApiResource;
import com.stripe.net.Webhook;
import com.stripe.param.*;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stripe")
public class StripeController {

	@Value("${stripe.apiKey}")
	private String apiKey;

	@Value("${stripe.endpointSecret}")
	private String endpointSecret;

	@Value("${stripe.domain}")
	private String domain;

	private static final Gson gson = new Gson();

	@PostConstruct
	public void init() {
		Stripe.apiKey = apiKey;
	}
	
	@Autowired
	private UserService userService;
	
	private final UserMapper userMapper = new UserMapper();

	@PostMapping("/create-checkout-session")
	public ResponseJson<String> createCheckoutSession(@RequestParam("lookup_key") String lookupKey) throws Exception {
		PriceListParams priceParams = PriceListParams.builder().addLookupKeys(lookupKey).build();
		PriceCollection prices = Price.list(priceParams);
		String priceId = prices.getData().get(0).getId();

		SessionCreateParams params = SessionCreateParams.builder()
				.addLineItem(
						SessionCreateParams.LineItem.builder()
								.setPrice(priceId)
								.setQuantity(1L)
								.build()
				)
				.setMode(SessionCreateParams.Mode.SUBSCRIPTION)
				.setSuccessUrl(domain + "/checkout/success?session_id={CHECKOUT_SESSION_ID}&price_id=" + priceId)
				.setCancelUrl(domain + "/checkout/cancel")
				.setSubscriptionData(SessionCreateParams.SubscriptionData.builder().setTrialPeriodDays(14L).build())
				.build();
		com.stripe.model.checkout.Session session = com.stripe.model.checkout.Session.create(params);
		
		UserDTO userDTO = userMapper.toDTO(userService.getUserConnected());
		userDTO.setStripeSessionId(session.getId());
		userDTO.setStripePriceId(priceId);
		userService.updateUser(userDTO);

		return new ResponseJson<>(session.getUrl(), 200);
	}


	@PostMapping("/create-portal-session")
	public ResponseJson<String> createPortalSession(@RequestParam("session_id") String sessionId) throws Exception {
		com.stripe.model.checkout.Session checkoutSession = com.stripe.model.checkout.Session.retrieve(sessionId);
		String customer = checkoutSession.getCustomer();

		com.stripe.param.billingportal.SessionCreateParams params =
				com.stripe.param.billingportal.SessionCreateParams.builder()
						.setReturnUrl(domain)
						.setCustomer(customer)
						.build();

		com.stripe.model.billingportal.Session portalSession = com.stripe.model.billingportal.Session.create(params);

		return new ResponseJson<>(portalSession.getUrl(), 200);
	}
	
	private List<Subscription> getSubscriptionsByCustomer(@RequestParam("customer_id") String customerId) {
		try {
			SubscriptionListParams params = SubscriptionListParams.builder()
					.setCustomer(customerId)
					.build();
			
			return Subscription.list(params).getData();
		} catch (StripeException e) {
			throw new RuntimeException("Error while getting subscriptions by customer", e);
		}
	}
	
	@PostMapping("/update-subscription")
	public ResponseJson<String> updateSubscription(@RequestParam("session_id") String sessionId,
											   @RequestParam("newPriceId") String newPriceId) throws StripeException {
		// Récupérer la session de paiement Stripe
		Session checkoutSession = Session.retrieve(sessionId);
		String oldPriceId = Subscription.retrieve(checkoutSession.getSubscription()).getItems().getData().get(0).getPrice().getId();
		
		// Récupérer l'abonnement correspondant à la session
		List<Subscription> subscriptions = getSubscriptionsByCustomer(checkoutSession.getCustomer());
		String subscriptionId = subscriptions.get(0).getId();
		Subscription subscription = Subscription.retrieve(subscriptionId);
		
		// Récupérer l'article d'abonnement correspondant à l'abonnement Silver
		SubscriptionItem subscriptionItem = getSubscriptionItemByPriceId(subscription.getItems().getData(), oldPriceId);
		
		if (subscriptionItem == null) {
			throw new RuntimeException("Subscription item not found");
		}
		
		PlanCollection plans = Plan.list(PlanListParams.builder().build());
		
		for (Plan plan : plans.getData()) {
			System.out.println("ID du plan : " + plan.getId());
			System.out.println("Nom du plan : " + plan.getNickname());
			System.out.println("Nom du plan : " + plan.getProduct());
			// Ajoutez d'autres détails du plan si nécessaire
		}
		
		// Créer les paramètres de mise à jour de l'abonnement
		SubscriptionUpdateParams params = SubscriptionUpdateParams.builder()
				.addItem(SubscriptionUpdateParams.Item.builder()
						.setId(subscription.getItems().getData().get(0).getId())
						.setPlan(newPriceId) // ID du nouveau plan
						.build())
				.build();
		
		// Mettre à jour l'abonnement
		subscription.update(params);

		return new ResponseJson<>("Subscription updated", 200);
	}
	
	// Méthode utilitaire pour récupérer l'article d'abonnement correspondant au prix donné
	private SubscriptionItem getSubscriptionItemByPriceId(List<SubscriptionItem> items, String priceId) {
		for (SubscriptionItem item : items) {
			if (item.getPrice().getId().equals(priceId)) {
				return item;
			}
		}
		return null; // Gérer le cas où aucun article correspondant n'est trouvé
	}

	@PostMapping("/webhook")
	public void handleWebhook(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader, HttpServletResponse response) {
		Event event;
		try {
			event = ApiResource.GSON.fromJson(payload, Event.class);
		} catch (JsonSyntaxException e) {
			response.setStatus(400);
			return;
		}

		if (endpointSecret != null && sigHeader != null) {
			try {
				event = Webhook.constructEvent(payload, sigHeader, endpointSecret);
			} catch (SignatureVerificationException e) {
				response.setStatus(400);
				return;
			}
		}

		StripeObject stripeObject = null;
		EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
		if (dataObjectDeserializer.getObject().isPresent()) {
			stripeObject = dataObjectDeserializer.getObject().get();
		}

		if (stripeObject != null) {
			handleStripeEvent(event, stripeObject);
		}

		response.setStatus(200);
	}

	private void handleStripeEvent(Event event, StripeObject stripeObject) {
		Subscription subscription;
		switch (event.getType()) {
			case "customer.subscription.deleted":
				subscription = (Subscription) stripeObject;
				// Handle customer.subscription.deleted event
				break;
			case "customer.subscription.trial_will_end":
				subscription = (Subscription) stripeObject;
				// Handle customer.subscription.trial_will_end event
				break;
			case "customer.subscription.created":
				subscription = (Subscription) stripeObject;
				// Handle customer.subscription.created event
				break;
			case "customer.subscription.updated":
				subscription = (Subscription) stripeObject;
				// Handle customer.subscription.updated event
				break;
			case "entitlements.active_entitlement_summary.updated":
				subscription = (Subscription) stripeObject;
				// Handle entitlements.active_entitlement_summary.updated event
				break;
			default:
				System.out.println("Unhandled event type: " + event.getType());
				break;
		}
	}
}
