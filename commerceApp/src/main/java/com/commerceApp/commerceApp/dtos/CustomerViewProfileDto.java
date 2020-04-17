package com.commerceApp.commerceApp.dtos;

import javax.validation.constraints.Size;

public class CustomerViewProfileDto extends UserViewDto {
    @Size(min = 10, max = 10, message = "Contact number invalid")
    private String contact;

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }


    public CustomerViewProfileDto(){}

    public CustomerViewProfileDto(@Size(min = 10, max = 10, message = "Contact number invalid") String contact) {
        this.contact = contact;
    }
}
