package com.example.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DemoLoader implements CommandLineRunner {

	@Autowired
	ContactRepository repository;

	
	public DemoLoader(ContactRepository repository) {
		super();
		this.repository = repository;
	}
	

	@Override
	public void run(String... args) throws Exception {
		this.repository.save(new Contact("Emmanuel", "Henri", "me@me.gr"));
	}

}
