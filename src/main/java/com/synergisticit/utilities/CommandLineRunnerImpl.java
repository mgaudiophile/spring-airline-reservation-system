package com.synergisticit.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.synergisticit.domain.Airline;
import com.synergisticit.domain.Airport;
import com.synergisticit.service.AirlineService;
import com.synergisticit.service.AirportService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

	@Autowired
	AirportService airportService;
	
	@Autowired
	AirlineService airlineService;
	
	@Autowired
	ResourceLoader resourceLoader;
	
	@Override
	public void run(String... args) throws Exception {
	
		Resource resource = resourceLoader.getResource("classpath:airports.json");
	    InputStream inputStream = resource.getInputStream();
	   
        try {
            byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
            String data = new String(bdata, StandardCharsets.UTF_8);

            Gson gson = new Gson();

            Type listType = new TypeToken<List<Airport>>() {}.getType();

            List<Airport> list = gson.fromJson(data, listType);

            for (Airport a : list) {
            	log.debug("id: " + a.getAirportId()
            			+ ", name: " + a.getAirportName()
            			+ ", code: " + a.getAirportCode()
            			+ ", city: " + a.getAirportCity()
            			+ ", state: " + a.getAirportState());
            }
            
        } catch (IOException e) {
            log.error("IOException", e);
        }
        
        resource = resourceLoader.getResource("classpath:airlines.json");
        inputStream = resource.getInputStream();
        try {
            byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
            String data = new String(bdata, StandardCharsets.UTF_8);

            Gson gson = new Gson();

            Type listType = new TypeToken<List<Airline>>() {}.getType();

            List<Airline> list = gson.fromJson(data, listType);

            for (Airline a : list) {
            	log.debug("id: " + a.getAirlineId()
            			+ ", name: " + a.getAirlineName()
            			+ ", code: " + a.getAirlineCode());
            }
            
        } catch (IOException e) {
            log.error("IOException", e);
        }
	}

}
