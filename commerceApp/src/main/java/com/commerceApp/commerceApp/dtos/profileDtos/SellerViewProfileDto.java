package com.commerceApp.commerceApp.dtos.profileDtos;


import com.commerceApp.commerceApp.validators.ValidGst;

import javax.validation.constraints.Size;

public class SellerViewProfileDto extends UserViewDto {

    @ValidGst
    @Size(min = 15, max = 15)
    private String GST;

    private String companyName;

    @Size(min = 10, max = 10)
    private String companyContact;

    public SellerViewProfileDto(){}

    public SellerViewProfileDto(@Size(min = 15, max = 15) String GST, String companyName, @Size(min = 10, max = 10) String companyContact) {
        this.GST = GST;
        this.companyName = companyName;
        this.companyContact = companyContact;
    }

    public SellerViewProfileDto(Long id, String firstName, String middleName, String lastName, Boolean isActive, @Size(min = 15, max = 15) String GST, String companyName, @Size(min = 10, max = 10) String companyContact) {
        super(id, firstName, middleName, lastName, isActive);
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
