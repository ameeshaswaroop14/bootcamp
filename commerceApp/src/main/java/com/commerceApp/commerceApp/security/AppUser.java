package com.commerceApp.commerceApp.security;


import com.commerceApp.commerceApp.models.Address;
import com.commerceApp.commerceApp.models.Role;
import com.commerceApp.commerceApp.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class AppUser implements UserDetails {

        private Long id;

        private String username;
        private String firstName;
        private String middleName;
        private String lastName;
        private String password;

        private boolean isDeleted;
        private boolean isActive;
        private boolean isExpired;
        private boolean isLocked;

        private Set<Role> roles;

        private Set<Address> addresses;

        public AppUser() {
        }

        public AppUser(User user){
                this.id = user.getId();
                this.username = user.getEmail();
                this.firstName = user.getFirstName();
                this.middleName = user.getMiddleName();
                this.lastName = user.getLastName();
                this.password = user.getPassword();
                this.isActive = user.isActive();
                this.isDeleted = user.isDeleted();
                this.isExpired = user.isExpired();
                this.isLocked = user.isLocked();

                this.roles = new HashSet<>(user.getRoles());

                this.addresses = new HashSet<Address>(user.getAddresses());
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
                return roles;
        }

        @Override
        public String getPassword() {
                return password;
        }

        @Override
        public String getUsername() {
                return username;
        }

        @Override
        public boolean isAccountNonExpired() {
                return !isExpired;
        }

        @Override
        public boolean isAccountNonLocked() {
                return !isLocked;
        }

        @Override
        public boolean isCredentialsNonExpired() {
                return true;
        }

        @Override
        public boolean isEnabled() {
                return true;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getFirstName() {
                return firstName;
        }

        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }

        public String getMiddleName() {
                return middleName;
        }

        public void setMiddleName(String middleName) {
                this.middleName = middleName;
        }

        public String getLastName() {
                return lastName;
        }

        public void setLastName(String lastName) {
                this.lastName = lastName;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public boolean isDeleted() {
                return isDeleted;
        }

        public void setDeleted(boolean deleted) {
                isDeleted = deleted;
        }

        public boolean isActive() {
                return isActive;
        }

        public void setActive(boolean active) {
                isActive = active;
        }

        public boolean isExpired() {
                return isExpired;
        }

        public void setExpired(boolean expired) {
                isExpired = expired;
        }

        public boolean isLocked() {
                return isLocked;
        }

        public void setLocked(boolean locked) {
                isLocked = locked;
        }

        public Set<Role> getRoles() {
                return roles;
        }

        public void setRoles(Set<Role> roles) {
                this.roles = roles;
        }

        public Set<Address> getAddresses() {
                return addresses;
        }

        public void setAddresses(Set<Address> addresses) {
                this.addresses = addresses;
        }
}