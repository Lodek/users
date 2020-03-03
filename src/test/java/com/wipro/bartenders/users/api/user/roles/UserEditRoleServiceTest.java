package com.wipro.bartenders.users.api.user.editrole;

import com.wipro.bartenders.users.api.role.detail.RoleDetailService;
import com.wipro.bartenders.users.api.user.detail.UserDetailService;
import com.wipro.bartenders.users.api.user.update.UserUpdateService;
import com.wipro.bartenders.users.domain.role.Role;
import com.wipro.bartenders.users.domain.user.User;
import com.wipro.bartenders.users.utils.UserTestUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class UserEditRoleServiceTest {

    TestSpec ts;

    @Before
    public void createTestSpec(){
         this.ts = new TestSpec();
    }

    @Test
    public void appendRoles_userWithListOfRolesAndListOfExistingRoles_expectNewRolesToBeSet(){
        this.ts.given_user_with_list_of_roles()
        .given_list_of_roles_to_append()
        .when_appendRoles_is_called()
        .then_user_should_have_new_roles()
        .then_old_roles_should_remain_unchanged();
    }

    @Test
    public void addRole_addingRoleToUserWithNoRoles_userHasNewRole() {
        this.ts.given_user()
        .given_role()
        .when_called_add_role()
        .then_user_should_have_role();
    }

}

class TestSpec {

    @InjectMocks
    UserEditRoleService service;

    @Mock
    UserUpdateService userUpdateService;

    @Mock
    RoleDetailService roleDetailService;

    @Mock
    UserDetailService userDetailService;

    User bob;

    String roleName = "root";
    String userName = "bob";
    Long userId = 1L;
    Set<Role> newRoles;
    Set<Role> ogRoles;
    Role role;


    TestSpec() {
        MockitoAnnotations.initMocks(this);
        newRoles = new HashSet<>();
        ogRoles = new HashSet<>();
    }


    TestSpec given_user_with_list_of_roles() {
        User bob = UserTestUtils.getBob(10L);
        Role painter = new Role("painter", 5);
        Role hippie = new Role("hippie", 9);
        this.ogRoles.add(painter);
        this.ogRoles.add(hippie);
        bob.setRoles(this.ogRoles);
        this.bob = bob;
        return this;
    }

    TestSpec given_list_of_roles_to_append() {
        return this;
    }

    TestSpec when_appendRole_is_called(){
        return this;
    }

    TestSpec when_appendRoles_is_called() {
        //this.service.appendRoles(this.bob.getId(), this.newRoles);
        return this;
    }
    TestSpec then_user_should_have_new_roles() {
        assertThat(this.bob.getRoles()).containsAll(this.newRoles);
        return this;
    }
    TestSpec then_old_roles_should_remain_unchanged() {
        assertThat(this.bob.getRoles()).containsAll(this.ogRoles);
        return this;
    }

    TestSpec given_user() {
        this.bob = new User();
        this.bob.setFirstName(this.userName);
        this.bob.setId(this.userId);
        given(this.userDetailService.getUser(this.userId)).willReturn(this.bob);
        return this;
    }

    TestSpec given_role() {
        this.role = new Role();
        this.role.setName(this.roleName);
        given(this.roleDetailService.getRoleByName(this.roleName)).willReturn(this.role);
        return this;
    }

    TestSpec when_called_add_role() {
        service.addRole(this.userId, this.roleName);
        return this;
    }

    TestSpec then_user_should_have_role() {
        assertThat(this.bob.getRoles()).contains(this.role);
        return this;
    }

}
