package com.me.bank.excerise.transactionanalysis.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.TimeZone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.me.bank.excerise.transactionanalysis.util.CustomLocalDateTimeDeserializer;

@Entity()
@Table(name = "TRANSACTION")
public class Transaction {

	@Id
	@Column(name = "TRANSACTION_ID")
	private String transactionId;
	
	@Column(name = "FROM_ACCOUNT_ID")
	private String fromAccountId;

	@Column(name = "TO_ACCOUNT_ID")
	private String toAccountId;

	@Column(name = "TRANSACTION_TYPE")
	private String transactionType;

	@Column(name = "AMOUNT")
	private Double amount;

	@Column(name = "RELATED_TRANSACTION")
	private String relatedTransaction;

	//@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss" )
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
	@Column(name = "CREATED_AT")
	private LocalDateTime createdAt;

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getFromAccountId() {
		return fromAccountId;
	}

	public void setFromAccountId(String fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	public String getToAccountId() {
		return toAccountId;
	}

	public void setToAccountId(String toAccountId) {
		this.toAccountId = toAccountId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getRelatedTransaction() {
		return relatedTransaction;
	}

	public void setRelatedTransaction(String relatedTransaction) {
		this.relatedTransaction = relatedTransaction;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", fromAccountId=" + fromAccountId + ", toAccountId="
				+ toAccountId + ", transactionType=" + transactionType + ", amount=" + amount + ", relatedTransaction="
				+ relatedTransaction + ", createdAt=" + createdAt + "]";
	}
	
	

}