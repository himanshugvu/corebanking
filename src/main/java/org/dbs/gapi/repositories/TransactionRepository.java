package org.dbs.gapi.repositories;


import org.dbs.gapi.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Page<Transaction> findBySourceAccountId(long sourceAccountId, Pageable pageable);
}
