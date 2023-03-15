package com.perscholas.app.service;

import com.perscholas.app.model.OrderBasket;
import com.perscholas.app.model.Product;
import com.perscholas.app.model.UserRegistration;
import com.perscholas.app.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderService {

    OrderRepository orderRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void saveOrder(OrderBasket orderBasket) {
        orderRepository.save(orderBasket);
    }


    public List<OrderBasket> findAllByUserEmail(UserRegistration userEmail) {

        List<OrderBasket> orders = orderRepository.findAllByUserEmail(userEmail);
        return orders;

    }

    public List<Object[]> findAllProductsInOrderByUserEmail() {

        log.warn("findAllProductsById in orderservice"+orderRepository.findAllProductsInOrderByUserEmail().stream().toList());
        List<Object[]> orderedProducts = orderRepository.findAllProductsInOrderByUserEmail().stream().toList();
         return orderedProducts;
    }

    public void findAllByProductsId(Integer id) {
    }
}
