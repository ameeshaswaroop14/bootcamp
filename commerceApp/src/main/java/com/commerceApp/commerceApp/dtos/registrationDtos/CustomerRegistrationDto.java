package com.commerceApp.commerceApp.dtos.registrationDtos;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CustomerRegistrationDto extends UserRegistrationDto {

    @NotNull
    @NotEmpty
    @Size(min = 10, max = 10)
    private String contact;

    public CustomerRegistrationDto(@NotNull @NotEmpty String firstName, String middleName, @NotNull @NotEmpty String lastName, @NotNull @NotEmpty String email, String password, @NotNull @NotEmpty String confirmPassword, @NotNull @NotEmpty @Size(min = 10, max = 10) String contact) {
        super(firstName, middleName, lastName, email, password, confirmPassword);
        this.contact = contact;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
