package com.commerceApp.commerceApp.dtos.registrationDtos;


import com.commerceApp.commerceApp.validators.ValidGST;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SellerRegistrationDto extends UserRegistrationDto {
    @ValidGST
    private String GST;
    @NotNull
    @NotEmpty
    private String companyName;
    @Size(min = 10, max = 10)
    private String companyContact;

    public SellerRegistrationDto(@NotNull @NotEmpty String firstName, String middleName, @NotNull @NotEmpty String lastName, @NotNull @NotEmpty String email, String password, @NotNull @NotEmpty String confirmPassword, String GST, @NotNull @NotEmpty String companyName, @Size(min = 10, max = 10) String companyContact) {
        super(firstName, middleName, lastName, email, password, confirmPassword);
        this.GST = GST;
        this.companyName = companyName;
        this.companyContact = companyContact;
    }

    public String getGST() {
        return GST;
    }

    public void setGST(String GST) {
        this.GST = GST;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyContact() {
        return companyContact;
    }

    public void setCompanyContact(String companyContact) {
        this.companyContact = companyContact;
    }

}
