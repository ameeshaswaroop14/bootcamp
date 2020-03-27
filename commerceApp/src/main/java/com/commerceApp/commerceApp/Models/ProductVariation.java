package com.commerceApp.commerceApp.Models;


import javax.persistence.Entity;

@Entity
public class ProductVariation {
    private Integer PRODUCT_VARIATION_ID;
    private Integer PRODUCT_ID;
    private Integer QUANTITY_AVAILAIBLE;
    private Double PRICE;
    private String PRIMARY_IMAGE_NAME;

    public Integer getPRODUCT_VARIATION_ID() {
        return PRODUCT_VARIATION_ID;
    }

    public void setPRODUCT_VARIATION_ID(Integer PRODUCT_VARIATION_ID) {
        this.PRODUCT_VARIATION_ID = PRODUCT_VARIATION_ID;
    }

    public Integer getPRODUCT_ID() {
        return PRODUCT_ID;
    }

    public void setPRODUCT_ID(Integer PRODUCT_ID) {
        this.PRODUCT_ID = PRODUCT_ID;
    }

    public Integer getQUANTITY_AVAILAIBLE() {
        return QUANTITY_AVAILAIBLE;
    }

    public void setQUANTITY_AVAILAIBLE(Integer QUANTITY_AVAILAIBLE) {
        this.QUANTITY_AVAILAIBLE = QUANTITY_AVAILAIBLE;
    }

    public Double getPRICE() {
        return PRICE;
    }

    public void setPRICE(Double PRICE) {
        this.PRICE = PRICE;
    }

    public String getPRIMARY_IMAGE_NAME() {
        return PRIMARY_IMAGE_NAME;
    }

    public void setPRIMARY_IMAGE_NAME(String PRIMARY_IMAGE_NAME) {
        this.PRIMARY_IMAGE_NAME = PRIMARY_IMAGE_NAME;
    }
}
