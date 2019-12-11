package com.me.bank.excerise.transactionanalysis.util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.TimeZone;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class CsvUtils {
	private static final CsvMapper mapper = new CsvMapper();

	public static <T> List<T> read(Class<T> clazz, File csvFile) throws IOException {
		CsvSchema schema = mapper.schemaFor(clazz).withHeader().withColumnReordering(true);
		ObjectReader reader = mapper.readerFor(clazz).with(schema).with(TimeZone.getDefault());
		return reader.<T>readValues(csvFile).readAll();
	}
}
