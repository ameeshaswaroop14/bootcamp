package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.Models.Customer;
import com.commerceApp.commerceApp.dtos.CustomerRegistrationDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private ModelMapper modelMapper;
    public Customer tocustomer(CustomerRegistrationDto customerRegistrationDto){
        Customer customer=modelMapper.map(customerRegistrationDto,Customer.class);
        return customer;
    }
}
