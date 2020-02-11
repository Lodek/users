package com.wipro.bartenders.users.api.role.list;

import com.wipro.bartenders.users.api.role.common.RoleMapper;
import com.wipro.bartenders.users.domain.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleListController {

    private final static int maxPageSize = 1;

    @Autowired
    RoleMapper mapper;

    @Autowired
    RoleListService roleListService;

    @GetMapping
    public Page<RoleListResponse> listRoles(@RequestParam(defaultValue = "0", name="page") int pageNum,
                                            @RequestParam(defaultValue = "1", name="results") int pageSize){
        pageSize = pageSize > maxPageSize ? maxPageSize : pageSize;
        Page<Role> roles = roleListService.listRoles(PageRequest.of(pageNum, pageSize));
        return roles.map((role) -> RoleListResponse.class.cast(mapper.toRoleIdDto(role)));
    }
}
