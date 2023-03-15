package com.perscholas.app.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.*;
@AllArgsConstructor
@Entity
@Table(name = "customers")
@Slf4j
@NoArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRegistration {

    @Id
    @NonNull @Email(message = "Provide a valid email")
    String email;

    @NonNull @NotBlank( message = "Username can't be blank")
    String userName;
   @Setter(AccessLevel.NONE)
    @NonNull @NotBlank(message = "Password can't be blank")
    String password;
   public String setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
        return this.password;
    }
    @NonNull @NotBlank( message = "Address can't be blank")
    String address;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userEmail",cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH} )
    private List<OrderBasket> orders = new ArrayList<>();

    @ToString.Exclude
    @OneToOne(mappedBy = "email", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    UserImage userImage;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
    private Product products;


    public void addOrders(OrderBasket order){
        orders.add(order);
        order.getUserEmail().addOrders(order);
        log.debug("add address executed");
    }

    public void removeOrders(OrderBasket order){
        orders.remove(order);
        order.getUserEmail().removeOrders(order);
        log.debug("remove address executed");
    }

    public UserRegistration(@NonNull String email, @NonNull String userName, @NonNull String password, String address) {
        this.email = email;
        this.userName = userName;
       this.password = setPassword(password);
/*        this.password =password;*/
                this.address = address;
    }

    public UserRegistration(@NonNull String email, @NonNull String userName, @NonNull String password, String address, UserImage userImage) {
        this.email = email;
        this.userName = userName;
       this.password = setPassword(password);
  /*      this.password =password;*/
        this.address = address;
        this.userImage = userImage;
    }

    public UserRegistration(@NonNull String email, @NonNull String userName) {
        this.email = email;
        this.userName = userName;

    }

    public UserRegistration(@NonNull String email, @NonNull String userName, @NonNull String password) {
        this.email = email;
        this.userName = userName;
/*        this.password =password;*/
        this.password = setPassword(password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRegistration that = (UserRegistration) o;
        return email.equals(that.email) && userName.equals(that.userName) && password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, userName, password);
    }

       /* @ToString.Exclude
    @ManyToMany(mappedBy = "user_email",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
    @JoinTable(
            joinColumns = @JoinColumn(name="user_email"),
            inverseJoinColumns = @JoinColumn(name="order_id"))
    private List<OrderBasket> orders = new ArrayList<>();*/

 /*   @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
    @JoinTable(name="user_products",
            joinColumns = @JoinColumn(name="user_email"),
            inverseJoinColumns = @JoinColumn(name="product_id"))
    private List<Product> products = new ArrayList<>();*/
}
