package com.commerceApp.commerceApp.Models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer CUSTOMER_USER_ID;
    private Long CONTACT;

    public Integer getCUSTOMER_USER_ID() {
        return CUSTOMER_USER_ID;
    }

    public void setCUSTOMER_USER_ID(Integer CUSTOMER_USER_ID) {
        this.CUSTOMER_USER_ID = CUSTOMER_USER_ID;
    }

    public Long getCONTACT() {
        return CONTACT;
    }

    public void setCONTACT(Long CONTACT) {
        this.CONTACT = CONTACT;
    }
}
