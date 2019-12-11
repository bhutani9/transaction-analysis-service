package com.me.bank.excerise.transactionanalysis.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.me.bank.excerise.transactionanalysis.model.Response;
import com.me.bank.excerise.transactionanalysis.model.Transaction;

public interface TransactionAnalysisService {

	List<Transaction> saveTransactions(File file) throws IOException;

	Response printTransactions(String accountId, String fromDate, String toDate) throws Exception;

}
