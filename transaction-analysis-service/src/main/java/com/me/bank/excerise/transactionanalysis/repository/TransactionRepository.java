package com.me.bank.excerise.transactionanalysis.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.me.bank.excerise.transactionanalysis.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	@Query(value = "select * from TRANSACTION where (FROM_ACCOUNT_ID=:accountId OR TO_ACCOUNT_ID=:accountId) "
			+ "AND CREATED_AT between :fromDateTime and :toDateTime AND TRANSACTION_TYPE = 'PAYMENT' ", nativeQuery = true)
	public List<Transaction> getAccountTransactions(@Param("accountId") String accountId,
			@Param("fromDateTime") LocalDateTime fromDateTime, @Param("toDateTime") LocalDateTime toDateTime);

	@Query(value = "select * from TRANSACTION where (FROM_ACCOUNT_ID=:accountId OR TO_ACCOUNT_ID=:accountId) "
			+ " AND CREATED_AT >= :fromDateTime AND TRANSACTION_TYPE = 'REVERSAL' ", nativeQuery = true)
	public List<Transaction> getReverseTransactions(@Param("accountId") String accountId,
			@Param("fromDateTime") LocalDateTime fromDateTime);

}
