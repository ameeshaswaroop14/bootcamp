package com.commerceApp.commerceApp.dtos;

import com.commerceApp.commerceApp.validators.PasswordMatches;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@PasswordMatches
    public class ForgotPassword implements Serializable {
    private static final long serialVersionUID=1L;

        @NotNull
        @NotEmpty
        String password;

        @NotNull
        @NotEmpty
        String confirmPassword;

        public ForgotPassword() {
        }

        public ForgotPassword(@NotNull @NotEmpty String password, @NotNull @NotEmpty String confirmPassword) {
            this.password = password;
            this.confirmPassword = confirmPassword;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getConfirmPassword() {
            return confirmPassword;
        }

        public void setConfirmPassword(String confirmPassword) {
            this.confirmPassword = confirmPassword;
        }
    }
