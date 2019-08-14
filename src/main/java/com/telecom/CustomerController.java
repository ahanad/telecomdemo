package com.telecom;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.telecom.error.CustomerNotFoundException;




public class CustomerController {
	
	@Autowired
	private CustomerRepository repository;
	
	@GetMapping("/customers")
    List<Customer> findAll() {
        return repository.findAll();
    }
	
	// Save
    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    Customer newCustomer(@RequestBody Customer newCustomer) {
        return repository.save(newCustomer);
    }

    // Find
    @GetMapping("/customers/{id}")
    Customer findOne(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    // Save or update
    @PutMapping("/customers/{id}")
    Customer saveOrUpdate(@RequestBody Customer newCustomer, @PathVariable Long id) {

        return repository.findById(id)
                .map(x -> {
                    x.setFirstName(newCustomer.getFirstName());
                    x.setLastName(newCustomer.getLastName());
                    x.setPhoneNumbers(newCustomer.getPhoneNumbers());
                    return repository.save(x);
                })
                .orElseGet(() -> {
                	newCustomer.setId(id);
                    return repository.save(newCustomer);
                });
    }


    @DeleteMapping("/books/{id}")
    void deleteCustomer(@PathVariable Long id) {
        repository.deleteById(id);
    }
    
    //Find phones for a particular customer
    @GetMapping ("/customers/{id}/phones")
    List<Phone> findPhonesForCustomer(@PathVariable Long id){
    	Customer customer = repository.findById(id)
        .orElseThrow(() -> new CustomerNotFoundException(id));
    	
    	return customer.getPhoneNumbers();
    }
	
}
