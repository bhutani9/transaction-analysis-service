package com.me.bank.excerise.transactionanalysis.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.bank.excerise.transactionanalysis.model.Response;
import com.me.bank.excerise.transactionanalysis.model.Transaction;
import com.me.bank.excerise.transactionanalysis.repository.TransactionRepository;
import com.me.bank.excerise.transactionanalysis.util.CsvUtils;

@Service
public class TransactionAnalysisServiceImpl implements TransactionAnalysisService {

	@Autowired
	TransactionRepository repo;

	@Override
	public List<Transaction> saveTransactions(File csvFile) throws IOException {
		List<Transaction> transactions = CsvUtils.read(Transaction.class, csvFile);
		return repo.saveAll(transactions);

	}

	@Override
	public Response printTransactions(String accountNumber, String fromDateString, String toDateString) throws Exception {

		LocalDateTime fromDateTime = this.parseDate(fromDateString);
		LocalDateTime toDateTime = this.parseDate(toDateString);

		Set<String> reverseIds = repo.getReverseTransactions(accountNumber, fromDateTime)
				.stream()
				.map(t -> t.getRelatedTransaction())
				.collect(Collectors.toSet());
		
		List<Transaction> transactions = repo.getAccountTransactions(accountNumber, fromDateTime, toDateTime)
				.stream()
				.filter(t -> !reverseIds.contains(t.getTransactionId()))
				.collect(Collectors.toList());

		Double amountDebited = transactions.stream()
				.filter(t -> t.getFromAccountId().equals(accountNumber))
				.mapToDouble(t -> t.getAmount()).reduce(0, Double::sum);

		Double amountCredited = transactions.stream()
				.filter(t -> t.getToAccountId().equals(accountNumber))
				.mapToDouble(t -> t.getAmount()).reduce(0, Double::sum);

		Double total = amountCredited - amountDebited;
		

		return new Response(total,transactions.size());

	}

	private LocalDateTime parseDate(String dateString) throws Exception {
		LocalDateTime dateTime = null;

		if (dateString != null && !dateString.isEmpty()) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			dateTime = LocalDateTime.from(formatter.parse(dateString.trim()));
		} else {
			throw new Exception("Invalid date String");

		}
		return dateTime;

	}

}
