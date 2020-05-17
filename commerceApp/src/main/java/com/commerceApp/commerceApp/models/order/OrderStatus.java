package com.commerceApp.commerceApp.models.order;


import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

enum FROM_STATUS
{
    ORDER_PLACED,
    CANCELLED,
    ORDER_REJECTED,
    ORDER_CONFIRMED,
    ORDER_SHIPPED,
    DELIVERED,
    RETURN_REQUESTED,
    RETURN_REJECTED,
    RETURN_APPROVED,
    PICK_UP_INITIATED,
    PICK_UP_COMPLETED,
    REFUND_INITIATED,
    REFUND_COMPLETED
}
enum TO_STATUS
{
    CANCELLED,
    ORDER_CONFIRMED,
    ORDER_REJECTED,
    REFUND_INITIATED,
    CLOSED,
    ORDER_SHIPPED,
    DELIVERED,
    RETURN_REQUESTED,
    RETURN_REJECTED,
    RETURN_APPROVE,
    PICK_UP_INITIATED,
    PICK_UP_COMPLETED,

    REFUND_COMPLETED,

}
@Audited
@Entity
@Table(name = "ORDER_STATUS")
public class OrderStatus implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "TRANSITION_NOTES_COMMENTS")
    private String transition_notes_comments;
    @OneToOne
    @JoinColumn(name = "ORDER_PRODUCT_ID")
    private OrderProduct orderProduct;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderProduct getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(OrderProduct orderProduct) {
        this.orderProduct = orderProduct;
    }

    public String getTransition_notes_comments() {
        return transition_notes_comments;
    }

    public void setTransition_notes_comments(String transition_notes_comments) {
        this.transition_notes_comments = transition_notes_comments;
    }
}
