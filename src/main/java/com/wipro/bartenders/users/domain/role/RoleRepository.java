package com.wipro.bartenders.users.domain.role;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Set<Role> findByName(String name);
}
