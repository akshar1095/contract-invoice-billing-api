package com.aksharapatel.fastcontract.contractbillingapi.repositories;

import com.aksharapatel.fastcontract.contractbillingapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
