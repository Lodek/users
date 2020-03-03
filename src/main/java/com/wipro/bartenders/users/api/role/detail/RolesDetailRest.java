package com.wipro.bartenders.users.api.role.detail;

import com.wipro.bartenders.users.domain.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RolesDetailRest {

    @Autowired
    private RolesDetailService service;

    @Autowired
    RolesDetailMapper mapper;

    @GetMapping("/{id}")
    public RolesDetailResponse getRole(@PathVariable Long id) throws RoleNotFoundException{
        Role role = service.getByIdEager(id);
        return mapper.roleToResponse(role);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String roleNotFoundExceptionHandler(RoleNotFoundException e ){
        return e.getMessage();
    }

}
