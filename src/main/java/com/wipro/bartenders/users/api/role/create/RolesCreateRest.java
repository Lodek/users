package com.wipro.bartenders.users.api.role.create;

import com.wipro.bartenders.users.api.common.audit.AuditRequestHeaders;
import com.wipro.bartenders.users.domain.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/roles")
@CrossOrigin
public class RolesCreateRest {

    @Autowired
    RolesCreateService service;

    @Autowired
    RolesCreateMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RolesCreateResponse addRole(@RequestBody @Valid RolesCreateRequest request, AuditRequestHeaders headers) {
        Role role = mapper.roleFromRequest(request);
        service.addRole(role);
        return mapper.roleToResponse(role);
    }

}
