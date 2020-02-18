package com.wipro.bartenders.users.domain.user;

import com.wipro.bartenders.users.domain.post.Post;
import com.wipro.bartenders.users.domain.post.PostRepository;
import com.wipro.bartenders.users.domain.role.Role;
import com.wipro.bartenders.users.domain.role.RoleRepository;
import org.hibernate.LazyInitializationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    RoleRepository roleRepository;

    @Test
    public void findByIdEager_userWithPosts_returnMatchingUser(){
        TestSpec ts = new TestSpec(userRepository, postRepository, roleRepository)
        .given_bob()
        .given_bobs_posts()
        .when_queried_for_bob_eagerly()
        .then_query_returned_non_empty_optional()
        .then_assert_opt_posts_has_size_2();
    }

    @Test
    public void findByIdEager_UserWithRoles_returnMatchingUser(){
        TestSpec ts = new TestSpec(userRepository, postRepository, roleRepository);
        ts.given_bob()
        .given_bobs_roles()
        .when_queried_for_bob_eagerly()
        .then_query_returned_non_empty_optional()
        .then_assert_opt_roles_has_size_2();
    }
    @Test
    public void findByIdEager_userWithRolesAndPosts_returnUserWithPopulatedCollections() {
        TestSpec ts = new TestSpec(userRepository, postRepository, roleRepository);
        ts.given_bob()
        .given_bobs_posts()
        .given_bobs_roles()
        .when_queried_for_bob_eagerly()
        .then_query_returned_non_empty_optional()
        .then_assert_opt_roles_has_size_2()
        .then_assert_opt_posts_has_size_2();

    }

    @Test
    public void findById_useWithRoles_raisesLazyInitializationWhenAcessed(){
        TestSpec ts = new TestSpec(userRepository, postRepository, roleRepository);
        ts.given_bob()
        .given_bobs_posts()
        .when_queried_for_bob_lazily()
        .then_getPost_raises_lazy_exception();

    }
}

class TestSpec{

    UserRepository userRepository;
    PostRepository postRepository;
    RoleRepository roleRepository;

    TestSpec(UserRepository ur, PostRepository pr, RoleRepository rr){
        this.userRepository = ur;
        this.postRepository = pr;
        this.roleRepository = rr;
    }

    User bob;
    Post p1;
    Post p2;

    Optional<User> opt;
    Role r1;
    Role r2;

    TestSpec given_bob(){
        this.bob = new User();
        this.bob.setUserName("bob");
        userRepository.save(this.bob);
        return this;
    }

    TestSpec given_bobs_posts(){
        this.p1 = new Post();
        this.p1.setContent("post 1");
        this.p1.setOp(this.bob);
        postRepository.save(p1);

        this.p2= new Post();
        this.p2.setContent("post 2");
        this.p2.setOp(this.bob);
        postRepository.save(p2);
        return this;
    }

    TestSpec when_queried_for_bob_eagerly() {
        this.opt = userRepository.findByIdEagerly(this.bob.getId());
        return this;
    }

    TestSpec given_bobs_roles() {
        Set<User> users = new HashSet<>();
        users.add(this.bob);
        this.r1 = new Role();
        this.r1.setName("painter");
        this.r1.setUsers(users);
        roleRepository.save(this.r1);

        this.r2 = new Role();
        this.r2.setName("bob");
        this.r2.setUsers(users);
        roleRepository.save(this.r2);

        return this;
    }

    TestSpec then_query_returned_non_empty_optional(){
        assertThat(this.opt).isNotEmpty();
        return this;
    }


    TestSpec then_assert_opt_posts_has_size_2() {
        assertThat(this.opt.get().getPosts())
                .isNotEmpty()
                .hasSize(2);
        return this;
    }

    TestSpec then_assert_opt_roles_has_size_2() {
        assertThat(this.opt.get().getRoles())
                .isNotEmpty()
                .hasSize(2);
        return this;
    }

    TestSpec when_queried_for_bob_lazily() {
        this.opt = userRepository.findById(this.bob.getId());
        return this;
    }

    TestSpec then_getPost_raises_lazy_exception() {
        User u = this.opt.get();
        LazyInitializationException e = null;
        Set<Post> posts;
        try {
            posts = u.getPosts();
            posts.size();
        } catch (LazyInitializationException exception) {
            e = exception;
        }
        assertThat(e).isInstanceOf(LazyInitializationException.class);
        return this;
    }
}
