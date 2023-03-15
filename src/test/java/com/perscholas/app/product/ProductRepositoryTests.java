package com.perscholas.app.product;

import com.perscholas.app.model.Category;
import com.perscholas.app.model.Product;
import com.perscholas.app.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTests {
    @Autowired
    private ProductRepository productRepository;

    @Test
    @Rollback(false)
    public void testCreateProduct() {

        Product savedProduct = productRepository.save( new Product(Integer.valueOf(21),"Mint",new Category(Integer.valueOf(3),"Herbs","Herbs"),
                16.00,
                "Mint is both edible also aromatic plant",
                "images/herb/mint.webp",20,20.0,1));
        assertThat(savedProduct.getId()).isGreaterThan(0);
    }

    @Test
    public void testFindProductByName() {
        Product product = productRepository.findByName("Mint");
        assertThat(product.getName()).isEqualTo("Mint");
    }

    @Test
    public void testFindProductById() {
        Product product = productRepository.findById(19).get();
        assertThat(product.getId()).isEqualTo(19);
    }
    @Test
    public void getAllPoductByCategoryId(){
        List<Product> products = productRepository.findAllByCategory_Id(3);
        assertThat(products.get(0).getCategory().getName().equalsIgnoreCase("Herbs"));

    }

    @Test
    public void getFindAllByName(){
        List<Product> products = productRepository.findAllByName("Mint");
        assertThat(products.get(0).getName().equalsIgnoreCase("Mint"));

    }

}
