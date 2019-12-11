package com.me.bank.excerise.transactionanalysis;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.me.bank.excerise.transactionanalysis.model.Response;
import com.me.bank.excerise.transactionanalysis.service.TransactionAnalysisService;
import com.me.bank.excerise.transactionanalysis.service.TransactionAnalysisServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@TestPropertySource(locations = "classpath:application.properties")
@ActiveProfiles("test")
public class TransactionAnalysisServiceTest {

	private static Logger log = LoggerFactory.getLogger(TransactionAnalysisServiceTest.class);

	@Autowired
	TransactionAnalysisService service;

	@Test
	public void accountDetailsWithReversalTest() {
		
		String accountId = "ACC334455";
		String fromDate = "20/10/2018 12:00:00";
		String toDate = "20/10/2018 19:00:00";
		
		try {
			Response response = service.printTransactions(accountId, fromDate, toDate);
			Assertions.assertThat(response.getTotal()).isEqualTo(-25);
			Assertions.assertThat(response.getTransactionCount()).isEqualTo(1);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("getAccountDetailsTest Failed");
		}

	}
	
	@Test
	public void accountDetailsWithReversalTest1() {
		
		String accountId = "ACC998877";
		String fromDate = "20/10/2018 12:00:00";
		String toDate = "20/10/2018 19:00:00";
		
		try {
			Response response = service.printTransactions(accountId, fromDate, toDate);
			Assertions.assertThat(response.getTotal()).isEqualTo(-5);
			Assertions.assertThat(response.getTransactionCount()).isEqualTo(1);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("getAccountDetailsTest Failed");
		}

	}
	
	@Test
	public void accountDetailsTest() {
		
		String accountId = "ACC778899";
		String fromDate = "20/10/2018 12:00:00";
		String toDate = "21/10/2018 19:00:00";
		
		try {
			Response response = service.printTransactions(accountId, fromDate, toDate);
			Assertions.assertThat(response.getTotal()).isEqualTo(37.25);
			Assertions.assertThat(response.getTransactionCount()).isEqualTo(3);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("getAccountDetailsTest Failed");
		}

	}

	@TestConfiguration
	static class TransactionAnalysisServiceTestContextConfiguration {

		@Bean
		public TransactionAnalysisService transactionAnalysisService() {
			return new TransactionAnalysisServiceImpl();
		}
	}


	@Before
	public void setUp() throws Exception {
		URL fileUrl = getClass().getClassLoader().getResource("transactions.csv");

		File file = new File(fileUrl.getFile());
		try {
			service.saveTransactions(file);
		} catch (IOException ex) {
			fail("File Load Fail");
			ex.printStackTrace();

		}

	}

}
