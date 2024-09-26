package com.customer.customer.resources;

import com.customer.customer.entities.Customer;
import com.customer.customer.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {




    @GetMapping
    public ResponseEntity<List<Customer>> findAll(){
        List<Customer> list = new ArrayList<>();

        list.add(new Customer(1L, "Caetano", "12345678901", 0.0, Instant.now(),0));
        list.add(new Customer(2L, "Tiago", "12345678901", 6000.0, Instant.now(),1));
        list.add(new Customer(3L, "Itamara", "12345678901", 7000.0, Instant.now(),1));

        return ResponseEntity.ok(list);
    }

}
