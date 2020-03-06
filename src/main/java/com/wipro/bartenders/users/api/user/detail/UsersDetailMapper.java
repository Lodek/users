package com.wipro.bartenders.users.api.user.detail;

import com.wipro.bartenders.users.domain.user.User;
import org.mapstruct.Mapper;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public interface UsersDetailMapper {
    default UsersDetailResponse userToResponse(User user){
        UsersDetailResponse response = new UsersDetailResponse();
        response.setRoles(new ArrayList<>());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setUserName(user.getUserName());
        response.setBirthDate(user.getBirthDate());
        response.setEmail(user.getEmail());
        response.setId(user.getId());
        user.getRoles().forEach((role) -> response.getRoles().add(role.getId()));
        return response;
    }
}
