package com.wipro.bartenders.users.domain.user;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

@Service
public interface UserRepository extends PagingAndSortingRepository<User, Long> {


}


