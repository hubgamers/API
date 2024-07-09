package com.hubgamers.api.service;


import com.hubgamers.api.model.Invitation;
import com.hubgamers.api.model.User;
import com.hubgamers.api.model.dto.InvitationDTO;
import com.hubgamers.api.repository.InvitationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.security.auth.login.AccountNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class InvitationServiceTest {
	
	@InjectMocks
	InvitationService invitationService = new InvitationService();
	
	@Mock
	UserService userService;
	
	@Mock
	InvitationRepository invitationRepository;
	
	private User getUserConnectedMock() {
		User user = new User();
		user.setId(1L);
		user.setUsername("test");
		user.setEmail("test@example.com");
		return user;
	}

	@Test
	public void testCreateInvitation() throws AccountNotFoundException {
		InvitationDTO invitationDTO = new InvitationDTO();
		invitationDTO.setUserId(1L);
		invitationDTO.setTeamId(1L);
		invitationDTO.setType(Invitation.InvitationType.JOIN_STAFF);
		Mockito.when(userService.getUserConnected()).thenReturn(getUserConnectedMock());
		invitationService.createInvitation(invitationDTO);
		Mockito.verify(invitationRepository, Mockito.times(1)).save(Mockito.any());
	}
}


