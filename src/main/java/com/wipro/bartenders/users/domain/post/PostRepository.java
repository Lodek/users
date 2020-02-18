package com.wipro.bartenders.users.domain.post;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;


public interface PostRepository extends CrudRepository<Post, Long> {

    @Query("select p from Post p join fetch p.op where p.id = :id")
    Optional<Post> findByIdEager(@Param("id") Long id);

    @Query("select p from Post p join fetch p.op where p.op.userName = :name")
    Set<Post> findPostByOpUserName(@Param("name") String name);

    Set<Post> findByOp_UserName(String userName);
}
