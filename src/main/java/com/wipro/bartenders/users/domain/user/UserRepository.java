package com.wipro.bartenders.users.domain.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    @Query("select distinct u from User u left join fetch u.posts left join fetch u.roles where u.id = :id")
    Optional<User> findByIdEagerly(@Param("id") Long id);

}
