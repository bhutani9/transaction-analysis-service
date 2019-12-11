Command to run the program - 
mvn spring-boot:run

Console Input : 
Please select option :
1 Upload Csv File
2 Get account transaction details.
3 Exit

When you select option 1 - please enter csv file path. This option will load the transactions data in in-memory H2 DB.
eg.
CSV File Path:
/Users/tkmalrk/Documents/transactions.csv



As the second step please enter valid account number, fromDatetime and toDateTime to get transaction details.




Test Case to validate the logic:
com.me.bank.excerise.transactionanalysis.TransactionAnalysisServiceTest


