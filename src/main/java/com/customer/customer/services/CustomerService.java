package com.customer.customer.services;

import com.customer.customer.dto.CustomerDTO;
import com.customer.customer.entities.Customer;
import com.customer.customer.repositories.CustomerRepository;
import com.customer.customer.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public List<CustomerDTO> findAll(){
        List<Customer> customers = repository.findAll();
        return customers.stream().map(CustomerDTO::new).collect(Collectors.toList());
    }


    public CustomerDTO findById(Long id){
        Optional<Customer> obj = repository.findById(id);
        if(obj.isPresent()){
            Customer customer = obj.get();
            return new CustomerDTO(customer);
        } else throw new ResourceNotFoundException("Customer not found");
    }
}
