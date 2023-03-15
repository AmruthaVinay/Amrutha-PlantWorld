package com.perscholas.app.repository;

import com.perscholas.app.controller.ProductController;
import com.perscholas.app.model.Product;
import com.perscholas.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findAllByCategory_Id(int id);
    @Query(value = "select * from product p where p.name like %:keyword% or p.description like %:keyword%" , nativeQuery = true)
    List<Product> findAllByName(String keyword);

    Product findByName(String s);
}
