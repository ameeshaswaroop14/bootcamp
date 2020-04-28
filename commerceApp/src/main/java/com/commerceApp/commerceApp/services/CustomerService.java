package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.models.Address;
import com.commerceApp.commerceApp.models.Customer;
import com.commerceApp.commerceApp.models.User;
import com.commerceApp.commerceApp.dtos.AddressDto;
import com.commerceApp.commerceApp.dtos.AdminCustomerDto;
import com.commerceApp.commerceApp.dtos.profileDtos.CustomerViewProfileDto;
import com.commerceApp.commerceApp.repositories.AddressRepository;
import com.commerceApp.commerceApp.repositories.CustomerRepository;
import com.commerceApp.commerceApp.repositories.UserRepository;
import com.commerceApp.commerceApp.util.EntityDtoMapping;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.commerceApp.commerceApp.util.EntityDtoMapping.*;


@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    UserRepository userRepository;

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

    public ResponseEntity<String> updateProfile(String email, CustomerViewProfileDto customerViewProfileDto) {
        Customer customer = customerRepository.findByEmail(email);
        if (customerViewProfileDto.getFirstName() != null)
            customer.setFirstName(customerViewProfileDto.getFirstName());
        if (customerViewProfileDto.getMiddleName() != null)
            customer.setFirstName(customerViewProfileDto.getFirstName());
        if (customerViewProfileDto.getContact() != null)
            customer.setContact(customerViewProfileDto.getContact());
        customerRepository.save(customer);
        return new ResponseEntity<>("Profile Updated", HttpStatus.OK);

    }

    public Set getCustomerAddresses(String email) {
        Customer customer = customerRepository.findByEmail(email);
        Set<AddressDto> addressDtos = new HashSet<>();
        Set<Address> addresses = customer.getAddresses();

        addresses.forEach(
                (a) -> addressDtos.add(toAddressDto(a))
        );
        return addressDtos;

    }

    public ResponseEntity<String> addNewAddress(String email, AddressDto addressDto) {
        Customer customer = customerRepository.findByEmail(email);
        Address newAddress =EntityDtoMapping.toAddress(addressDto);
        customer.addAddress(newAddress);
        customerRepository.save(customer);
        String message = "Address added successfully";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteAddress(String email, Long id) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        if (!addressOptional.isPresent()) {
            return new ResponseEntity<>("No address found with the given id", HttpStatus.NOT_FOUND);
        }
        Address savedAddress = addressOptional.get();
        if (savedAddress.getUser().getEmail().equals(email)) {
            addressRepository.deleteAddressById(id);
            return new ResponseEntity<>("Address successfully deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid Operation", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> updateCustomerAddress(String username, AddressDto addressDto, Long id) {
        Optional<Address> address = addressRepository.findById(id);
        if (!address.isPresent())
            return new ResponseEntity<>("Address not found",HttpStatus.NOT_FOUND);
        Address savedAddress = address.get();
        User user = userRepository.findByEmail(username);
        if (!savedAddress.getUser().getEmail().equals(username))
            return new ResponseEntity<>("Address not found",HttpStatus.BAD_REQUEST);
        if (addressDto.getCity() != null)
            savedAddress.setCity(addressDto.getCity());
        if (addressDto.getState() != null)
            savedAddress.setState(addressDto.getCity());
        if (addressDto.getCountry() != null)
            savedAddress.setCountry(addressDto.getCountry());
        if (addressDto.getZipCode() != null)
            savedAddress.setZipCode(addressDto.getZipCode());
        if (addressDto.getLabel() != null)
            savedAddress.setLabel(addressDto.getLabel());
        if (addressDto.getAddressLine() != null)
            savedAddress.setLabel(addressDto.getAddressLine());
        addressRepository.save(savedAddress);
         return new ResponseEntity<>("Success",HttpStatus.OK);
    }

}

