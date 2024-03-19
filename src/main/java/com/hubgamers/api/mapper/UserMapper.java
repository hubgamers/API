package com.hubgamers.api.mapper;

import com.hubgamers.api.model.User;
import com.hubgamers.api.model.dto.UserDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Component
@Mapper(componentModel = "spring")
public class UserMapper implements com.hubgamers.api.mapper.Mapper<User, UserDTO> {

	@Override
	public Class<User> getEntityClass() {
		return User.class;
	}

	@Override
	public Class<UserDTO> getDTOClass() {
		return UserDTO.class;
	}

	@Override
	public List<String> getColumns() {
		List<String> proprieties = new ArrayList<>();
		Field[] champs = User.class.getDeclaredFields();
		for (Field champ : champs) {
			proprieties.add(champ.getName());
		}
		return proprieties;
	}
}
