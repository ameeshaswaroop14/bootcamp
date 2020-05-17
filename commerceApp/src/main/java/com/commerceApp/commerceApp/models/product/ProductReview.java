package com.commerceApp.commerceApp.models.product;

import com.commerceApp.commerceApp.models.Customer;
import com.commerceApp.commerceApp.models.product.Product;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
@Audited
@Entity
public class ProductReview implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String review;
    private Double rating;


    @ManyToOne
    @JoinColumn(name = "customer_user_id")
    private Customer author;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


    public ProductReview() {
    }

    public ProductReview(String review, Double rating) {
        this.review = review;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Customer getAuthor() {
        return author;
    }

    public void setAuthor(Customer author) {
        this.author = author;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductReview{" +
                "review='" + review + '\'' +
                ", rating=" + rating +
                ", author=" + author +
                ", product=" + product +
                '}';
    }
}
