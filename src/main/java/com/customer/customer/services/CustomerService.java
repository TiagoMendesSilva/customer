package com.customer.customer.services;

import com.customer.customer.dto.CustomerDTO;
import com.customer.customer.entities.Customer;
import com.customer.customer.repositories.CustomerRepository;
import com.customer.customer.services.exceptions.DatabaseException;
import com.customer.customer.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Transactional(readOnly = true)
    public Page<CustomerDTO> findAllPaged(PageRequest pageRequest){
        Page<Customer> customers = repository.findAll(pageRequest);
        return customers.map(CustomerDTO::new);
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

    @Transactional
    public CustomerDTO update(Long id, CustomerDTO dto){

        try {
            Customer entity = repository.getReferenceById(id);
            entity.setName(dto.getName());
            entity.setCpf(dto.getCpf());
            entity.setIncome(dto.getIncome());
            entity.setIncome(dto.getIncome());
            entity.setbirthDate(dto.getBirthDate());
            entity.setChildren(dto.getChildren());
            entity = repository.save(entity);
            return new CustomerDTO(entity);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Id not found "+id);
        }
    }

    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Id not found "+id);
        }
        try{
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException("Integrity violation");
        }
    }
}
