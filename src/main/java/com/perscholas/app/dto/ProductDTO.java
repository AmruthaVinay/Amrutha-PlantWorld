package com.perscholas.app.dto;

import com.perscholas.app.model.Category;
import com.perscholas.app.model.UserRegistration;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

@Data
public class ProductDTO {

    private Long id;

    String name;
    private int categoryId;

    private double price;

    private String description;

    private String image;

    private String displayedTypeOfPlants;

    private String userEmail;

    private String userName;

}
