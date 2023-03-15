package com.perscholas.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;


import java.util.*;
import static jakarta.persistence.GenerationType.IDENTITY;

@Slf4j
@Entity
@Table(name="product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="product_id")
    private Integer id;
    @NonNull
    String name;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH},fetch = FetchType.EAGER )
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @NonNull
    @Min(value = 1L, message = "The value must be positive and cannot be less than 1")
    @DecimalMin(value = "1.0", message = "Please Enter a valid Deposit Amount")
    @Positive(message="Only positive numbers allowed")
    private Double price;
    @NonNull
    private String description;
    @NonNull
    private String image;
    @Min(value = 0, message = "The value must be positive")
    private int discount;
    @Min(value = 0, message = "The value must be positive")
    private Double beforeDiscountPrice;
    @Min(1)
    @Max(3)
    @NonNull
    @NotNull(message="Quantity cannot be null")
    private int quantity;
  @ManyToOne()
    @JoinColumn(name = "user_email")
    private UserRegistration user_email;

    @ToString.Exclude
  @ManyToMany(mappedBy = "products")
  private Set<OrderBasket> orders = new LinkedHashSet<>();

    public Product(Integer id, @NonNull String name, Category category, @NonNull Double price, @NonNull String description, @NonNull String image, int discount, int quantity, UserRegistration user_email) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.image = image;
        this.discount = discount;
        this.quantity = quantity;
        this.user_email = user_email;
    }
    public Product(Integer id, @NonNull String name, Category category, @NonNull Double price, @NonNull String description, @NonNull String image, int discount, Double beforeDiscountPrice, int quantity, UserRegistration user_email) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.image = image;
        this.discount = discount;
        this.beforeDiscountPrice = beforeDiscountPrice;
        this.quantity = quantity;
        this.user_email = user_email;
    }

    public Product(Integer id, @NonNull String name, Category category, @NonNull Double price, @NonNull String description, @NonNull String image, int discount, Double beforeDiscountPrice, int quantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.image = image;
        this.discount = discount;
        this.beforeDiscountPrice = beforeDiscountPrice;
        this.quantity = quantity;

    }



    public void addProductToOrder(OrderBasket o){
        orders.add(o);
      o.getProducts().add(this);
        log.debug("add orders done!");
    }
    public void removeProductFromOrder(OrderBasket o){
        orders.remove(o);
        o.getProducts().remove(this);
        log.debug("remove order done!");
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id)&& Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }


    /*  @ToString.Exclude
          @ManyToMany(mappedBy = "products",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST},
                    fetch = FetchType.EAGER)
            private UserRegistration userRegistration;*/

}
