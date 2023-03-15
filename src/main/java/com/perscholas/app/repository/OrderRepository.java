package com.perscholas.app.repository;

import com.perscholas.app.model.OrderBasket;
import com.perscholas.app.model.Product;
import com.perscholas.app.model.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderBasket, Integer> {

    //OrderBasket findByUserSessionId(String userSessionId);

    List<OrderBasket> findAllByUserEmail(UserRegistration userEmail);
    @Query(value = "SELECT distinct(PROD.image), order_id FROM ORDER_PRODUCTS AS OP JOIN PRODUCT AS PROD ON OP.products_id = product_id order by order_id" , nativeQuery = true)
    Optional<Object[]> findAllProductsInOrderByUserEmail();


}
