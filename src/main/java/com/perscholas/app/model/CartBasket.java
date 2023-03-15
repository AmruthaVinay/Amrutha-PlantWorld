package com.perscholas.app.model;

//import com.perscholas.app.global.CartData;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class CartBasket {
    private Product cartProducts;

    private int productQuantity;

    private double productPrice;

    private String productName;

    private String productImage;

    private int totalProductCount;

    private String shippingAddress;

    private double totalProductPrice;

    private String userSessionId;

    public double estimatedTax;

    private double totalPlusTax;

    UserRegistration userProductsOrdered;

}
