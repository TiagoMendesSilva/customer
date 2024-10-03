package com.customer.customer.resources;

import com.customer.customer.dto.CustomerDTO;
import com.customer.customer.resources.exceptions.StandardError;
import com.customer.customer.services.CustomerService;
import com.customer.customer.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> findAll(){
        List<CustomerDTO> customers = service.findAll();
        return ResponseEntity.ok(customers);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id, HttpServletRequest request){

        try{
            CustomerDTO customerDTO = service.findById(id);
            return ResponseEntity.ok(customerDTO);

        }catch (ResourceNotFoundException e){
            StandardError error = new StandardError();
            error.setTimestamp(Instant.now());
            error.setStatus(HttpStatus.NOT_FOUND.value());
            error.setError(HttpStatus.NOT_FOUND.name());
            error.setMessage("Customer not found");
            error.setPath(request.getRequestURI());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }
}
