package com.commerceApp.commerceApp.dtos;

import com.commerceApp.commerceApp.validators.ValidGST;

import javax.validation.constraints.Size;

public class SellerViewProfileDto extends UserViewDto{

    @ValidGST
    @Size(min = 15, max = 15)
    private String GST;

    private String companyName;

    @Size(min = 10, max = 10)
    private String companyContact;



}
