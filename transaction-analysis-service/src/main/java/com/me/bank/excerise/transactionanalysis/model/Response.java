package com.me.bank.excerise.transactionanalysis.model;

public class Response {
	
	private Double total;
	
	private Integer transactionCount;

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getTransactionCount() {
		return transactionCount;
	}

	public void setTransactionCount(Integer transactionCount) {
		this.transactionCount = transactionCount;
	}

	@Override
	public String toString() {
		return "Relative balance for the period is:" + total +"\n " + "Number of transactions included is:" + transactionCount ;
	}

	public Response() {
		super();
	}

	public Response(Double total, Integer transactionCount) {
		super();
		this.total = total;
		this.transactionCount = transactionCount;
	}
	
	
	
	

}
