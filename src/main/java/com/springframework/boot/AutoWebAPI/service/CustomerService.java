package com.springframework.boot.AutoWebAPI.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springframework.boot.AutoWebAPI.dto.CustomerDto;
import com.springframework.boot.AutoWebAPI.exception.CustomerNotFoundException;
import com.springframework.boot.AutoWebAPI.model.Customer;
import com.springframework.boot.AutoWebAPI.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private static final Logger LOGGER= LoggerFactory.getLogger(CustomerService.class);

    private  final CustomerRepository customerRepository;

    private final ObjectMapper objectMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, ObjectMapper objectMapper) {
        this.customerRepository = customerRepository;
        this.objectMapper = objectMapper;
    }

    //creating CRUD operations

    public Customer createCustomer(CustomerDto dto){
        LOGGER.info("Creating Customer {}",dto);

        Customer customer=objectMapper.convertValue(dto,Customer.class);

        return customerRepository.save(customer);


        /**
         * another way for creating Customer

         * Customer customer = new Customer();
         *customer.setFirstName(createCustomerRequest.getFirstName());
         *customer.setLastName(createCustomerRequest.getLastName());
         *customer.setAge(createCustomerRequest.getAge());
         *customer.seteMail(createCustomerRequest.geteMail());

         return customerRepository.save(customer);
         */
    }

    public List<Customer> getCustomers(){
        LOGGER.info("Getting list of customers");
        return customerRepository.findAll();
    }

    public Customer getCustomer(Long id) throws CustomerNotFoundException {

        LOGGER.info("Getting customer by id {}",id);
        return customerRepository.findById(id).orElseThrow(()->new CustomerNotFoundException("Customer "+id+" does not exist."));

    }

    public Customer updateCustomer(Long id ,CustomerDto dto) throws CustomerNotFoundException {

        LOGGER.info("Update customer {} with {}",id,dto);
        Customer customer = getCustomer(id);

        BeanUtils.copyProperties(dto,customer);

        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id){
        LOGGER.info("Deleting customer {}",id);
        customerRepository.deleteById(id);
        LOGGER.info("Customer {} deleted",id);
    }

}