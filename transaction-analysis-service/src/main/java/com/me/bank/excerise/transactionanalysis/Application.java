package com.me.bank.excerise.transactionanalysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.me.bank.excerise.transactionanalysis.model.AccountLedgerEntry;
import com.me.bank.excerise.transactionanalysis.model.Transaction;
import com.me.bank.excerise.transactionanalysis.util.CsvUtils;
import com.me.bank.excerise.transactionanalysis.util.TransactionType;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			String filePath = args[0];
			File csvFile = new File(filePath);

			if (!csvFile.exists()) {
				throw new FileNotFoundException("Invalid File Path");
			} else {
				List<Transaction> transactions = CsvUtils.read(Transaction.class, csvFile);
				Map<String, Double> accountBalanceMap = new HashMap<String, Double>();
				List<AccountLedgerEntry> ledger = new ArrayList<AccountLedgerEntry>();

				transactions.forEach(t -> {
					String fromAccount = t.getFromAccountId();
					String toAccount = t.getToAccountId();
					Double amount = t.getAmount();
					Double fromAcBalance = accountBalanceMap.get(fromAccount);
					Double toAcBalance = accountBalanceMap.get(toAccount);
					
					Double fromAcBalanceAfterTrx;
					Double toAcBalanceAfterTrx;
					
					if (fromAcBalance == null) {
						fromAcBalanceAfterTrx = 0 - amount;
					}else {
						fromAcBalanceAfterTrx  = fromAcBalance - amount;
					}

					if (toAcBalance == null) {
						toAcBalanceAfterTrx = 0 + t.getAmount();
					} else {
						toAcBalanceAfterTrx = toAcBalance + amount;
					}
					
					accountBalanceMap.put(fromAccount, fromAcBalanceAfterTrx);
					accountBalanceMap.put(toAccount, toAcBalanceAfterTrx);
					
					ledger.add(fromAccountLegerEntry(t, fromAcBalanceAfterTrx));
					ledger.add(toAccountLegerEntry(t, toAcBalanceAfterTrx));

				});
				
				ledger.stream().forEach(System.out::println);

			}

		};

	}
	
	
	private AccountLedgerEntry fromAccountLegerEntry(Transaction t, Double fromAcBalance) {
		AccountLedgerEntry  fromLedgerEntry =new AccountLedgerEntry();
		
		fromLedgerEntry.setAccountNumber(t.getFromAccountId());
		fromLedgerEntry.setTransactionId(t.getTransactionId());
		fromLedgerEntry.setAmount(t.getAmount());
		fromLedgerEntry.setTransactionType(TransactionType.DEBIT);
		fromLedgerEntry.setTransactionDetails(t.getTransactionType().trim());
		fromLedgerEntry.setBalance(fromAcBalance);
		
		return fromLedgerEntry;
	
	}
	
	private AccountLedgerEntry toAccountLegerEntry(Transaction t, Double toAcBalance) {
		AccountLedgerEntry  toLedgerEntry =new AccountLedgerEntry();
		
		toLedgerEntry.setAccountNumber(t.getToAccountId());
		toLedgerEntry.setTransactionId(t.getTransactionId());
		toLedgerEntry.setAmount(t.getAmount());
		toLedgerEntry.setTransactionType(TransactionType.CREDIT);
		toLedgerEntry.setTransactionDetails(t.getTransactionType().trim());
		
		toLedgerEntry.setBalance(toAcBalance);
		
		return toLedgerEntry;
	
	}
	
	
	
}
