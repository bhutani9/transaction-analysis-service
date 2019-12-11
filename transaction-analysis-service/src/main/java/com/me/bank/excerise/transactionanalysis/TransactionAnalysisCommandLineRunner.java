package com.me.bank.excerise.transactionanalysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.me.bank.excerise.transactionanalysis.model.Response;
import com.me.bank.excerise.transactionanalysis.model.Transaction;
import com.me.bank.excerise.transactionanalysis.service.TransactionAnalysisService;

@Component
@Profile("!test")
public class TransactionAnalysisCommandLineRunner implements CommandLineRunner {

	@Autowired
	TransactionAnalysisService service;

	@Override
	public void run(String... args) throws Exception {

		int inputCode = 0;
		Scanner scanner = new Scanner(System.in);
		while (inputCode != 3) {

			String inputString = null;
		

			System.out.println("Please select option :");
			System.out.println("1 Upload Csv File");
			System.out.println("2 Get account transaction details.");
			System.out.println("3 Exit");
			inputString = scanner.nextLine();

			if (inputString != null && !inputString.isEmpty()) {
				inputCode = Integer.parseInt(inputString);
			} else {
				continue;
			}

			try {
				switch (inputCode) {

				case 1:
					System.out.println("CSV File Path:");
					String filePath = scanner.nextLine();
					File csvFile = new File(filePath);

					if (!csvFile.exists()) {
						throw new FileNotFoundException("Invalid File Path");
					} else {
						List<Transaction> list = service.saveTransactions(csvFile);
						System.out.println("Number of transactions loaded:" + list.size());
					}

					break;

				case 2:
					System.out.println("Accont Number:");
					String accountId = scanner.nextLine();

					System.out.println("From Date Time:");
					String fromDate = scanner.nextLine();

					System.out.println("To Date Time:");
					String toDate = scanner.nextLine();

					Response response = service.printTransactions(accountId, fromDate, toDate);
					
					System.out.println("Final Amount: " + response.getTotal());
					System.out.println("Transaction Count:" + response.getTransactionCount());

				case 3:
					break;
				default:
					System.out.println("Invalid Input");
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}

		}
		scanner.close();

	}

}
