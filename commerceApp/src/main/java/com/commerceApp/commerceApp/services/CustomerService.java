package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.Models.Address;
import com.commerceApp.commerceApp.Models.Customer;
import com.commerceApp.commerceApp.dtos.AddressDto;
import com.commerceApp.commerceApp.dtos.AdminCustomerDto;
import com.commerceApp.commerceApp.dtos.CustomerRegistrationDto;
import com.commerceApp.commerceApp.dtos.CustomerViewProfileDto;
import com.commerceApp.commerceApp.repositories.AddressRepository;
import com.commerceApp.commerceApp.repositories.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    AddressService addressService;
    @Autowired
    AddressRepository addressRepository;


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

    public Customer toCustomer(CustomerViewProfileDto customerViewProfileDto) {
        Customer customer = modelMapper.map(customerViewProfileDto, Customer.class);
        return customer;
    }

    public CustomerViewProfileDto tocustomerViewProfileDto(Customer customer) {
        CustomerViewProfileDto customerViewProfileDto = modelMapper.map(customer, CustomerViewProfileDto.class);
        return customerViewProfileDto;
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
        AdminCustomerDto adminCustomerDto = toCustomerDto(customer);
        return adminCustomerDto;
    }

    public CustomerViewProfileDto getUserProfile(String email) {
        Customer customer = customerRepository.findByEmail(email);
        CustomerViewProfileDto customerViewProfileDto = tocustomerViewProfileDto(customer);
        return customerViewProfileDto;
    }

    public ResponseEntity<String> updateProfile(String email,CustomerViewProfileDto customerViewProfileDto){
        Customer customer=customerRepository.findByEmail(email);
        if(customerViewProfileDto.getFirstName()!=null)
            customer.setFirstName(customerViewProfileDto.getFirstName());
        if(customerViewProfileDto.getMiddleName()!=null)
            customer.setFirstName(customerViewProfileDto.getFirstName());
        if(customerViewProfileDto.getContact() != null)
            customer.setContact(customerViewProfileDto.getContact());
        customerRepository.save(customer);
        return new ResponseEntity<>("Profile Updated",HttpStatus.OK);

    }

    public Set getCustomerAddresses(String email) {
        Customer customer = customerRepository.findByEmail(email);
        Set<AddressDto> addressDtos = new HashSet<>();
        Set<Address> addresses = customer.getAddresses();

        addresses.forEach(
                (a)->addressDtos.add(addressService.toAddressDto(a))
        );
        return addressDtos;

    }

    public ResponseEntity<String> addNewAddress(String email, AddressDto addressDto) {
        Customer customer = customerRepository.findByEmail(email);
        Address newAddress = addressService.toAddress(addressDto);
        customer.addAddress(newAddress);
        customerRepository.save(customer);
        String message="Address added successfully";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
    public  ResponseEntity<String> deleteAddress(String email,Long id){
        Optional<Address> address=addressRepository.findById(id);
        if (address==null)
            return new ResponseEntity<>("Address not found",HttpStatus.NOT_FOUND);
            Address address1=address.get();
            if(email==address1.getUser().getEmail())
                addressRepository.deleteById(id);
        return new ResponseEntity<>("Address deleted",HttpStatus.OK);


    }




}

