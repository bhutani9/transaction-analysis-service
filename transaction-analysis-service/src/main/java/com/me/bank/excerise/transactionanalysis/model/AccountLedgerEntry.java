package com.me.bank.excerise.transactionanalysis.model;

import com.me.bank.excerise.transactionanalysis.util.TransactionType;

public class AccountLedgerEntry {
	
	private String accountNumber;
	
	private String transactionId;
	
	private TransactionType transactionType;
	
	private String transactionDetails;
	
	private Double amount;
	
	private Double balance;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public String getTransactionDetails() {
		return transactionDetails;
	}

	public void setTransactionDetails(String transactionDetails) {
		this.transactionDetails = transactionDetails;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "AccountLedgerEntry [accountNumber=" + accountNumber + ", transactionId=" + transactionId
				+ ", transactionType=" + transactionType + ", transactionDetails=" + transactionDetails + ", amount="
				+ amount + ", balance=" + balance + "]";
	}
	
	
	
	

}
