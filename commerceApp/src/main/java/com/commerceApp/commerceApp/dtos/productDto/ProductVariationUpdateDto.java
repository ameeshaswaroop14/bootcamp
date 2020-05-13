package com.commerceApp.commerceApp.dtos.productDto;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductVariationUpdateDto implements Serializable {
    private static final long serialVersionUID=1L;
    private Integer quantityAvailable;
    private Double price;
    private Boolean isActive;

    @NotNull
    private Map<String, String> attributes = new LinkedHashMap<>();


    public Integer getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(Integer quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }
}
