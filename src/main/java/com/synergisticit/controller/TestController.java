package com.synergisticit.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TestController {

	
	@GetMapping("/test")
	public String test() {
		
		String str = "2020-10-09 07:30:00";
		
//		ObjectMapper om = new ObjectMapper();
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		om.setDateFormat(df);
//	
//		LocalDateTime dt = om.convertValue(str, LocalDateTime.class);
//		
//		log.debug(dt.toString());
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withLocale(Locale.ENGLISH);

		LocalDateTimeDeserializer dateTimeDeserializer = new LocalDateTimeDeserializer(formatter);
		LocalDateTimeSerializer dateTimeSerializer = new LocalDateTimeSerializer(formatter);

		JavaTimeModule javaTimeModule = new JavaTimeModule(); 
		javaTimeModule.addDeserializer(LocalDateTime.class, dateTimeDeserializer);
		javaTimeModule.addSerializer(LocalDateTime.class, dateTimeSerializer);

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(javaTimeModule);
		
		LocalDateTime dt = mapper.convertValue(str, LocalDateTime.class);
		
		log.debug(dt.toString());
		
		return "login";
	}
}
