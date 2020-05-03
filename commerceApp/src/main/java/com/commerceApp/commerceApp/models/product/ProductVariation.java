package com.commerceApp.commerceApp.models.product;



import com.commerceApp.commerceApp.util.HashMapConverter;
import com.commerceApp.commerceApp.models.order.OrderProduct;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@Entity


public class ProductVariation  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer quantityAvailable;
    private Double price;
   // private String primaryImageName;

    @Convert(converter = HashMapConverter.class)
    private Map<String, String> productAttributes;

    private boolean isDeleted = false;
    private boolean isActive = true;

    public ProductVariation(){}

    public ProductVariation(Long id, Integer quantityAvailable, Double price, Map<String, String> productAttributes) {
        this.id = id;
        this.quantityAvailable = quantityAvailable;
        this.price = price;
        this.productAttributes = productAttributes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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



    public Map<String, String> getProductAttributes() {
        return productAttributes;
    }

    public void setProductAttributes(Map<String, String> productAttributes) {
        this.productAttributes = productAttributes;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Set<OrderProduct> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(Set<OrderProduct> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;


    @OneToMany(mappedBy = "productVariation", fetch = FetchType.EAGER)
    private Set<OrderProduct> orderedProducts;


    public ProductVariation(Integer quantityAvailable, Double price) {
        this.quantityAvailable = quantityAvailable;
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductVariation{" +
                "id=" + id +
                ", quantityAvailable=" + quantityAvailable +
                ", price=" + price +
                ", productAttributes=" + productAttributes +
                ", isDeleted=" + isDeleted +
                ", isActive=" + isActive +
                ", product=" + product +
                ", orderedProducts=" + orderedProducts +
                '}';
    }

    public void addOrderProduct(OrderProduct orderProduct){
        if(orderProduct != null){
            if(orderedProducts == null)
                orderedProducts = new LinkedHashSet<>();
            orderedProducts.add(orderProduct);
        }
    }
}


