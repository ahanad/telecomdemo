package com.telecom;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.telecom.error.PhoneNotFoundException;

public class PhoneController {
	
	@Autowired
	private PhoneRepository repository;

	@GetMapping("/phones")
    List<Phone> findAll() {
        return repository.findAll();
    }
	
	// Find
    @GetMapping("/phones/{id}")
    Phone findOne(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new PhoneNotFoundException(id));
    }
	
	@PutMapping("/phones/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	void activatePhone(@RequestBody Boolean isActive, @PathVariable Long id) {
		repository.findById(id)
                .map(x -> {
                    x.setIsActive(isActive);
                    return repository.save(x);
                });

	}
	
}
