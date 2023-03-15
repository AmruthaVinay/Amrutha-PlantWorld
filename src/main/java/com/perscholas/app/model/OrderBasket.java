package com.perscholas.app.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;

import static jakarta.persistence.GenerationType.IDENTITY;
@Entity
@Table(name = "order_basket")
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderBasket {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="order_id")
    private Integer id;
    @NonNull
    @Column(name="total_ordered_price")
    private Double totalOrderPrice;
    @NonNull
    @Column(name="shipping_address")
    private String shippingAddress;
    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="order_date")
    private Date ordered_date;


/*    @ToString.Exclude
    @ManyToMany()
    @JoinTable(name = "ordered_products", joinColumns = @JoinColumn(name = "order_id"),inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();*/

    @ToString.Exclude
    @ManyToMany()
    @JoinTable(name = "order_products",joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "products_id"))
    private List<Product> products = new ArrayList<Product>();


/*    @ManyToOne()
    private UserRegistration user_email;*/

    @ManyToOne
    @JoinColumn(name = "userEmail")
    UserRegistration userEmail;


    private String userSessionId;


    public void addProducts(Product p){
        products.add(p);
        p.getOrders().add(this);
        log.debug("add product done");
    }


    public void removeProduct(Product p){
        products.remove(p);
        p.getOrders().remove(this);
        log.debug("remove products done");
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderBasket that = (OrderBasket) o;
        return id.equals(that.id) && ordered_date.equals(that.ordered_date) && products.equals(that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ordered_date, products);
    }
}
