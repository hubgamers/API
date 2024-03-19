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
public class UserMapper implements MapperInstance<UserDTO, User>{
	
	@Override
	public UserDTO toDTO(User entity) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(entity.getId());
		userDTO.setEmail(entity.getEmail());
		userDTO.setUsername(entity.getUsername());
		userDTO.setPassword(entity.getPassword());
		userDTO.setRoles(entity.getRoles());
		return userDTO;
	}
	
	@Override
	public List<UserDTO> toDTO(List<User> entityList) {
		List<UserDTO> userDTOList = new ArrayList<>();
		entityList.forEach(e -> {
			userDTOList.add(toDTO(e));
		});
		return userDTOList;
	}
	
	@Override
	public User toEntity(UserDTO dto) {
		User user = new User();
		user.setId(dto.getId());
		user.setEmail(dto.getEmail());
		user.setUsername(dto.getUsername());
		user.setPassword(dto.getPassword());
		user.setRoles(dto.getRoles());
		return user;
	}
	
	@Override
	public List<User> toEntity(List<UserDTO> dtoList) {
		List<User> userList = new ArrayList<>();
		dtoList.forEach(d -> {
			userList.add(toEntity(d));
		});
		return userList;
	}

	@Override
	public List<String> getColumns() {
		List<String> proprietes = new ArrayList<>();
		Field[] champs = User.class.getDeclaredFields();
		for (Field champ : champs) {
			proprietes.add(champ.getName());
		}
		return proprietes;
	}
}
