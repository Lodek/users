package com.wipro.bartenders.users.domain.role;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Set;

public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {

    Set<Role> findByName(String name);
}
