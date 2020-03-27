package com.commerceApp.commerceApp.Models;

import javax.persistence.*;
import java.util.List;

@Entity
public  class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer USER_ID;
    private String EMAIL;
    private String FIRST_NAME;
    private String MIDDLE_NAME;
    private String LAST_NAME;
    private String PASSWORD;
    private boolean IS_DELETED;
    private boolean IS_ACTIVE;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Role> roleList;

    public Integer getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(Integer USER_ID) {
        this.USER_ID = USER_ID;
    }

    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "User",referencedColumnName = "USER_ID"),
            inverseJoinColumns =@JoinColumn(name = "Role",referencedColumnName = "ROLE_ID"))




    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getFIRST_NAME() {
        return FIRST_NAME;
    }

    public void setFIRST_NAME(String FIRST_NAME) {
        this.FIRST_NAME = FIRST_NAME;
    }

    public String getMIDDLE_NAME() {
        return MIDDLE_NAME;
    }

    public void setMIDDLE_NAME(String MIDDLE_NAME) {
        this.MIDDLE_NAME = MIDDLE_NAME;
    }

    public String getLAST_NAME() {
        return LAST_NAME;
    }

    public void setLAST_NAME(String LAST_NAME) {
        this.LAST_NAME = LAST_NAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public boolean isIS_DELETED() {
        return IS_DELETED;
    }

    public void setIS_DELETED(boolean IS_DELETED) {
        this.IS_DELETED = IS_DELETED;
    }

    public boolean isIS_ACTIVE() {
        return IS_ACTIVE;
    }

    public void setIS_ACTIVE(boolean IS_ACTIVE) {
        this.IS_ACTIVE = IS_ACTIVE;
    }

}
