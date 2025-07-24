package com.geek.spring.security.repository;

import com.geek.spring.security.model.Accounts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends CrudRepository<Accounts, Long> {

    com.geek.spring.security.model.Accounts findByCustomerId(long customerId);

}
