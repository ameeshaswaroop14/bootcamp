package com.commerceApp.commerceApp.Models;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer PRODUCT_ID;
    private String PRODUCT_NAME;
    private String PRODUCT_DESCRIPTION;
    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    private boolean IS_CANCELLABLE;
    private boolean IS_RETURNABLE;
    private String BRAND;
    private boolean IS_ACTIVE;



    public Integer getPRODUCT_ID() {
        return PRODUCT_ID;
    }

    public void setPRODUCT_ID(Integer PRODUCT_ID) {
        this.PRODUCT_ID = PRODUCT_ID;
    }

    public String getPRODUCT_NAME() {
        return PRODUCT_NAME;
    }

    public void setPRODUCT_NAME(String PRODUCT_NAME) {
        this.PRODUCT_NAME = PRODUCT_NAME;
    }

    public String getPRODUCT_DESCRIPTION() {
        return PRODUCT_DESCRIPTION;
    }

    public void setPRODUCT_DESCRIPTION(String PRODUCT_DESCRIPTION) {
        this.PRODUCT_DESCRIPTION = PRODUCT_DESCRIPTION;
    }



    public boolean isIS_CANCELLABLE() {
        return IS_CANCELLABLE;
    }

    public void setIS_CANCELLABLE(boolean IS_CANCELLABLE) {
        this.IS_CANCELLABLE = IS_CANCELLABLE;
    }

    public boolean isIS_RETURNABLE() {
        return IS_RETURNABLE;
    }

    public void setIS_RETURNABLE(boolean IS_RETURNABLE) {
        this.IS_RETURNABLE = IS_RETURNABLE;
    }

    public String getBRAND() {
        return BRAND;
    }

    public void setBRAND(String BRAND) {
        this.BRAND = BRAND;
    }

    public boolean isIS_ACTIVE() {
        return IS_ACTIVE;
    }

    public void setIS_ACTIVE(boolean IS_ACTIVE) {
        this.IS_ACTIVE = IS_ACTIVE;
    }

}
