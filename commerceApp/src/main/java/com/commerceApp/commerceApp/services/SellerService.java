package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.Models.Customer;
import com.commerceApp.commerceApp.Models.Seller;
import com.commerceApp.commerceApp.dtos.CustomerRegistrationDto;
import com.commerceApp.commerceApp.dtos.SellerRegistrationDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

        private ModelMapper modelMapper;
        public Seller toseller(SellerRegistrationDto sellerRegistrationDto){
            Seller seller=modelMapper.map(sellerRegistrationDto,Seller.class);
            return seller;
        }
    }