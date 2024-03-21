package com.hubgamers.api.mapper;

import com.hubgamers.api.model.Invitation;
import com.hubgamers.api.model.dto.InvitationDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Component
@Mapper(componentModel = "spring")
public class InvitationMapper implements com.hubgamers.api.mapper.Mapper<Invitation, InvitationDTO> {

	private final List<String> IGNORE_FIELDS = List.of("id");

	@Override
	public Class<Invitation> getEntityClass() {
		return Invitation.class;
	}

	@Override
	public Class<InvitationDTO> getDTOClass() {
		return InvitationDTO.class;
	}

	@Override
	public List<String> getAdminColumns() {
		List<String> proprieties = new ArrayList<>();
		Field[] champs = Invitation.class.getDeclaredFields();
		for (Field champ : champs) {
			proprieties.add(champ.getName());
		}
		return proprieties;
	}

	@Override
	public List<String> getColumns() {
		List<String> proprieties = new ArrayList<>();
		Field[] champs = Invitation.class.getDeclaredFields();
		for (Field champ : champs) {
			if (!IGNORE_FIELDS.contains(champ.getName())) {
				proprieties.add(champ.getName());
			}
		}
		return proprieties;
	}
}
