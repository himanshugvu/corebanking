package org.dbs.gapi.repositories;


import org.dbs.gapi.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findById(long id);
    Optional<Account> findByProductCodeAndAccountNumber(String productCode, String accountNumber);
}
