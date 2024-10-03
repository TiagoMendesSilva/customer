package com.customer.customer.services;

import com.customer.customer.dto.CustomerDTO;
import com.customer.customer.entities.Customer;
import com.customer.customer.repositories.CustomerRepository;
import com.customer.customer.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Transactional(readOnly = true)
    public List<CustomerDTO> findAll(){
        List<Customer> customers = repository.findAll();
        return customers.stream().map(CustomerDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CustomerDTO findById(Long id){
        Optional<Customer> obj = repository.findById(id);
        Customer customer = obj.orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        return new CustomerDTO(customer);
    }

    @Transactional
    public CustomerDTO insert (CustomerDTO dto){
        Customer entity = new Customer();
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setIncome(dto.getIncome());
        entity.setbirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
        entity = repository.save(entity);
        return new CustomerDTO(entity);
    }
}
