package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.dtos.profileDtos.SellerViewProfileDto;
import com.commerceApp.commerceApp.models.Seller;
import com.commerceApp.commerceApp.dtos.*;
import com.commerceApp.commerceApp.repositories.SellerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.commerceApp.commerceApp.util.EntityDtoMapping.toadminSellerDto;

@Service
public class SellerService {
    @Autowired
    SellerRepository sellerRepository;

       @Autowired
       ModelMapper modelMapper;
       @Autowired
       MailService mailService;

    public List<AdminSellerDto> getAllSeller(String offset,String size,String field){

        Integer pageNo=Integer.parseInt(offset);
        Integer pageSize=Integer.parseInt(size);
        Pageable pageable=PageRequest.of(pageNo,pageSize,Sort.by(field).ascending());
        List<Seller> sellers=sellerRepository.findAll(pageable);
        List<AdminSellerDto> adminSellerDtos=new ArrayList<>();
        sellers.forEach((seller -> adminSellerDtos.add(toadminSellerDto(seller))));
        return adminSellerDtos;

    }
    public AdminSellerDto getSellerByEmail(String email){
        Seller seller=sellerRepository.findByEmail(email);
        AdminSellerDto adminSellerDto=toadminSellerDto(seller);
        return adminSellerDto;
    }


    public AdminSellerDto getSellerByEmaiId(String email){
        Seller seller=sellerRepository.findByEmail(email);
        if(email==null){
            return null;
        }
        AdminSellerDto adminSellerDto=toadminSellerDto(seller);
        return adminSellerDto;
    }
    public SellerViewProfileDto tosellerProfileDto(Seller seller) {
        SellerViewProfileDto sellerViewProfileDto= modelMapper.map(seller, SellerViewProfileDto.class);
        return sellerViewProfileDto;
    }

    public SellerViewProfileDto getUserProfile(String email) {
        Seller seller = sellerRepository.findByEmail(email);
        SellerViewProfileDto sellerViewProfileDto = tosellerProfileDto(seller);
        return sellerViewProfileDto;
    }


    public ResponseEntity<String> updateUserProfile(String email, SellerViewProfileDto profileDto) {
        Seller savedSeller = sellerRepository.findByEmail(email);

        if(profileDto.getFirstName() != null)
            savedSeller.setFirstName(profileDto.getFirstName());

        if(profileDto.getLastName() != null)
            savedSeller.setLastName(profileDto.getLastName());

       // if(profileDto.getImage() != null)
         //   savedSeller.setImage(profileDto.getImage());

        if(profileDto.getActive() != null && !profileDto.getActive())
            savedSeller.setActive(profileDto.getActive());

        if(profileDto.getGST() != null)
            savedSeller.setGST(profileDto.getGST());

        if(profileDto.getCompanyContact() != null)
            savedSeller.setCompanyContact(profileDto.getCompanyContact());

        if(profileDto.getCompanyName() != null)
            savedSeller.setCompanyName(profileDto.getCompanyName());

        sellerRepository.save(savedSeller);

        return new ResponseEntity("Your profile has been updated", HttpStatus.OK);
    }




}