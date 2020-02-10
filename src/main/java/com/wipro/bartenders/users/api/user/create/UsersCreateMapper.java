package com.wipro.bartenders.users.api.user.create;

import com.wipro.bartenders.users.domain.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface UsersCreateMapper {

    @Mappings({@Mapping(target="birthDate", source="birthDate",
            dateFormat = "yyyy-MM-dd")})
    User fromDto(UsersCreateRequest request);

    UsersCreateResponse toDto(User user);
}
