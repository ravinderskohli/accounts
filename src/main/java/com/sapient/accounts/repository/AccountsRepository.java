package com.sapient.accounts.repository;

import com.sapient.accounts.entity.Accounts;
import com.sapient.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {

    Optional<Accounts> findByCustomerId(Long customerId);
    void deleteByCustomerId(Long customerId);

}
