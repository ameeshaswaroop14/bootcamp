package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.Models.Customer;
import com.commerceApp.commerceApp.dtos.AdminCustomerDto;
import com.commerceApp.commerceApp.dtos.CustomerRegistrationDto;
import com.commerceApp.commerceApp.repositories.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;


    public Customer toCustomer(CustomerRegistrationDto customerRegistrationDto) {
        Customer customer = modelMapper.map(customerRegistrationDto, Customer.class);
        return customer;
    }

    public AdminCustomerDto toCustomerDto(Customer customer) {
        AdminCustomerDto adminCustomerDto = modelMapper.map(customer, AdminCustomerDto.class);
        adminCustomerDto.setFirstName(customer.getFirstName());
        adminCustomerDto.setMiddleName(customer.getMiddleName());
        adminCustomerDto.setLastName(customer.getLastName());
        return adminCustomerDto;
    }

    public List<AdminCustomerDto> getAllCustomers(String offset, String size, String field) {
        Integer pageNo = Integer.parseInt(offset);
        Integer pageSize = Integer.parseInt(size);

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(field).ascending());

        List<Customer> customers = customerRepository.findAll(pageable);

        List<AdminCustomerDto> customerAdminApiDtos = new ArrayList<>();

        customers.forEach((customer) -> customerAdminApiDtos.add(toCustomerDto(customer)));
        return customerAdminApiDtos;
    }

    public AdminCustomerDto getCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email);
        AdminCustomerDto adminCustomerDto =toCustomerDto(customer);
        return adminCustomerDto;
    }
}

