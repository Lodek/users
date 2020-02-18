package com.wipro.bartenders.users.api.user.editrole;

import com.wipro.bartenders.users.domain.role.Role;
import com.wipro.bartenders.users.domain.user.User;
import com.wipro.bartenders.users.domain.user.UserRepository;
import com.wipro.bartenders.users.utils.UserTestUtils;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class UserEditRoleServiceTest {

    @Test
    public void appendRoles_userWithListOfRolesAndListOfExistingRoles_expectNewRolesToBeSet(){
        new TestSpec()
        .given_user_with_list_of_roles()
        .given_list_of_roles_to_append()
        .when_appendRoles_is_called()
        .then_user_should_have_new_roles()
        .then_old_roles_should_remain_unchanged();
    }
}

class TestSpec {

    @InjectMocks
    UserEditRoleService service;

    @Mock
    UserRepository repository;

    User bob;
    Set<Role> newRoles;
    Set<Role> ogRoles;

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
        Role sergeant = new Role("sergeant", 15);
        Role storyTeller = new Role("story-teller", 100);
        this.newRoles.add(sergeant);
        this.newRoles.add(storyTeller);
        given(repository.findById(this.bob.getId())).willReturn(Optional.of(this.bob));
        given(repository.save(this.bob)).willReturn(this.bob);
        given(repository.save(this.bob)).willReturn(this.bob);
        return this;
    }

    TestSpec when_appendRole_is_called(){
        return this;
    }

    TestSpec when_appendRoles_is_called() {
        this.service.appendRoles(this.bob.getId(), this.newRoles);
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

}
