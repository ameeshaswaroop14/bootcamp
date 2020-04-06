package com.commerceApp.commerceApp.Models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "AMOUNT_PAID")
    private Integer amount_paid;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_CREATED")
    private Date date_created;
    @Column(name ="PAYMENT_METHOD" )
    private String payment_method;
    @Column(name = "CUSTOMER_ADDRESS_CITY")
    private String customer_address_city;
    @Column(name = "CUSTOMER_ADDRESS_STATE")
    private String customer_address_state;
    @Column(name = "CUSTOMER_ADDRESS_COUNTRY")
    private String customer_address_country;
    @Column(name = "CUSTOMER_ADDRESS_ADDRESS_LINE")
    private String customer_address_address_line;
    @Column(name = "CUSTOMER_ADDRESS_ZIP_CODE")
    private Integer customer_address_zip_code;
    @Column(name = "CUSTOMER_ADDRESS_LABEL")
    private String customer_address_label;


    @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL)
    private List<OrderProduct> orderProductsList;
    @ManyToOne
    @JoinColumn(name = "CUSTOMER_USER_ID",nullable = false)
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAmount_paid() {
        return amount_paid;
    }

    public void setAmount_paid(Integer amount_paid) {
        this.amount_paid = amount_paid;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getCustomer_address_city() {
        return customer_address_city;
    }

    public void setCustomer_address_city(String customer_address_city) {
        this.customer_address_city = customer_address_city;
    }

    public String getCustomer_address_state() {
        return customer_address_state;
    }

    public void setCustomer_address_state(String customer_address_state) {
        this.customer_address_state = customer_address_state;
    }

    public String getCustomer_address_country() {
        return customer_address_country;
    }

    public void setCustomer_address_country(String customer_address_country) {
        this.customer_address_country = customer_address_country;
    }

    public String getCustomer_address_address_line() {
        return customer_address_address_line;
    }

    public void setCustomer_address_address_line(String customer_address_address_line) {
        this.customer_address_address_line = customer_address_address_line;
    }

    public Integer getCustomer_address_zip_code() {
        return customer_address_zip_code;
    }

    public void setCustomer_address_zip_code(Integer customer_address_zip_code) {
        this.customer_address_zip_code = customer_address_zip_code;
    }

    public String getCustomer_address_label() {
        return customer_address_label;
    }

    public void setCustomer_address_label(String customer_address_label) {
        this.customer_address_label = customer_address_label;
    }

    public List<OrderProduct> getOrderProductsList() {
        return orderProductsList;
    }

    public void setOrderProductsList(List<OrderProduct> orderProductsList) {
        this.orderProductsList = orderProductsList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}