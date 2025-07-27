package com.geek.spring.security.repository;

import com.geek.spring.security.model.Loans;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends CrudRepository<Loans, Long> {

	@PreAuthorize("hasRole('USER')")
	//@PreAuthorize("hasRole('ROOT')") // Negative Case
	List<Loans> findByCustomerIdOrderByStartDtDesc(long customerId);

}
