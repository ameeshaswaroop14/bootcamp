package com.commerceApp.commerceApp.models.order;

import com.commerceApp.commerceApp.models.product.ProductVariation;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ORDER_PRODUCT")
public class OrderProduct implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "QUANTITY")
    private Integer quantity;
    @Column(name = "PRICE")
    private Double price;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ID")
    private Orders orders;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PRODUCT_VARIATION_ID")
    private ProductVariation productVariation;
    @OneToOne(mappedBy = "orderProduct")
    private OrderStatus orderStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public ProductVariation getProductVariation() {
        return productVariation;
    }

    public void setProductVariation(ProductVariation productVariation) {
        this.productVariation = productVariation;
    }
}



