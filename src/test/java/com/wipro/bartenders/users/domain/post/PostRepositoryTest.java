package com.wipro.bartenders.users.domain.post;

import com.wipro.bartenders.users.domain.role.RoleRepository;
import com.wipro.bartenders.users.domain.user.User;
import com.wipro.bartenders.users.domain.user.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PostRepository postRepository;

    private TestSpec ts;

    @Before
    public void getSpec(){
        this.ts  = new TestSpec(userRepository, roleRepository, postRepository);
    }

    @Test
    public void findByIdEager_postWithUser_returnPost(){
        this.ts.given_op()
        .given_post_in_base()
        .when_find_post_by_id_with_eager()
        .then_optional_contains_post()
        .then_post_has_user();
    }

    @Test
    public void findByOpUserName_postWithOpName_returnPosts(){
        this.ts.given_op()
        .given_post_in_base()
        .when_query_post_by_op_name_hql()
        .then_posts_contains_created_post();
    }

    @Test
    public void findByOp_UserName_postWithOpName_returnPosts(){
        this.ts.given_op()
        .given_post_in_base()
        .when_query_post_by_op_name()
        .then_posts_contains_created_post();
    }

}

class TestSpec {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PostRepository postRepository;

    private Post post;
    private Optional<Post> opt;
    private User op;
    private Post fetchedPost;
    private Set<Post> posts;

    TestSpec (UserRepository userRepository, RoleRepository roleRepository, PostRepository postRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.postRepository = postRepository;
    }


    TestSpec then_optional_contains_post() {
        assertThat(this.opt).isNotEmpty();
        this.fetchedPost = this.opt.get();
        return this;
    }


    TestSpec given_post_in_base() {
        this.post = new Post();
        post.setContent("post");
        post.setOp(this.op);
        postRepository.save(post);
        return this;
    }

    TestSpec given_op() {
        this.op = new User();
        this.op.setUserName("bob");
        userRepository.save(op);
        return this;
    }

    TestSpec when_find_post_by_id_with_eager() {
        this.opt = postRepository.findByIdEager(this.post.getId());
        return this;
    }

    TestSpec then_post_has_user() {
        assertThat(this.fetchedPost.getOp().getId()).isEqualTo(this.op.getId());
        return this;
    }

    TestSpec when_query_post_by_op_name_hql() {
        this.posts = postRepository.findPostByOpUserName(this.op.getUserName());
        return this;
    }

    TestSpec then_posts_contains_created_post() {
        assertThat(this.posts).contains(this.post);
        return this;
    }

     TestSpec when_query_post_by_op_name() {
        this.posts = postRepository.findByOp_UserName(this.op.getUserName());
        return this;
    }
}
