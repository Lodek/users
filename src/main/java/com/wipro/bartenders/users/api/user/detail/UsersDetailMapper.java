package com.wipro.bartenders.users.api.user.detail;

import com.wipro.bartenders.users.domain.role.Role;
import com.wipro.bartenders.users.domain.user.User;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UsersDetailMapper {

    default UsersDetailResponse userToResponse(User user){
         UsersDetailResponse response = new UsersDetailResponse();
         response.setId(user.getId());
         response.setEmail(user.getEmail());
         response.setBirthDate(user.getBirthDate());
         response.setUserName(user.getUserName());
         response.setFirstName(user.getFirstName());
         response.setLastName(user.getLastName());
         List<Long> roles = user.getRoles().stream().map(Role::getId).collect(Collectors.toList());
         response.setRoles(roles);
         return response;
    }

}
