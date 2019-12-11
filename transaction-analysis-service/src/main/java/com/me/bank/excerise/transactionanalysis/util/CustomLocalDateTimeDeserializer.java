package com.me.bank.excerise.transactionanalysis.util;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class CustomLocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {

	private static final long serialVersionUID = 1L;

	protected CustomLocalDateTimeDeserializer(Class<LocalDateTime> vc) {
		super(vc);
	}

	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	

	public CustomLocalDateTimeDeserializer() {
		this(null);
	}

	@Override
	public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
		String date = p.getText();
		return LocalDateTime.from(formatter.parse(date));
	}

}
