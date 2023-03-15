/*
package com.perscholas.app.service;

import com.perscholas.app.model.CartBasket;
import com.perscholas.app.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
  private  CartRepository cartRepository;

    public CartBasket saveCart(CartBasket cartBasket) {
        System.out.println("-----------cartBasket------------"+cartBasket);
    return cartRepository.save(cartBasket);
    }

    public void deleteCartById(Long id) {
        cartRepository.deleteById(id);
    }

    public List<CartBasket> getAllProductsInCart() {
        return cartRepository.findAll();
    }
}
*/
