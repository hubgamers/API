package com.hubgamers.api.controller;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.hubgamers.api.response.ResponseJson;
import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.Price;
import com.stripe.model.PriceCollection;
import com.stripe.model.StripeObject;
import com.stripe.model.Subscription;
import com.stripe.net.ApiResource;
import com.stripe.net.Webhook;
import com.stripe.param.PriceListParams;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
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

	@PostMapping("/create-checkout-session")
	public ResponseJson<String> createCheckoutSession(@RequestParam("lookup_key") String lookupKey) throws Exception {
		PriceListParams priceParams = PriceListParams.builder().addLookupKeys(lookupKey).build();
		PriceCollection prices = Price.list(priceParams);
		System.out.println("prices " + prices);

		SessionCreateParams params = SessionCreateParams.builder()
				.addLineItem(
						SessionCreateParams.LineItem.builder()
								.setPrice(prices.getData().get(0).getId())
								.setQuantity(1L)
								.build()
				)
				.setMode(SessionCreateParams.Mode.SUBSCRIPTION)
				.setSuccessUrl(domain + "/checkout/success?session_id={CHECKOUT_SESSION_ID}")
				.setCancelUrl(domain + "/checkout/cancel")
				.setSubscriptionData(SessionCreateParams.SubscriptionData.builder().setTrialPeriodDays(14L).build())
				.build();
		com.stripe.model.checkout.Session session = com.stripe.model.checkout.Session.create(params);

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
