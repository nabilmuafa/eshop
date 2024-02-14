package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class ProductTest {
    Product product_valid;
    Product product_invalid;
    @BeforeEach
    void setUp() {
        this.product_valid = new Product();
        this.product_valid.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.product_valid.setProductName("Sampo Cap Bambang");
        this.product_valid.setProductQuantity(100);

        this.product_invalid = new Product();
        this.product_invalid.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.product_invalid.setProductName("");
        this.product_invalid.setProductQuantity(-100);
    }

    @Test
    void testGetProductId(){
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", this.product_valid.getProductId());
    }

    @Test
    void testGetProductName(){
        assertEquals("Sampo Cap Bambang", this.product_valid.getProductName());
    }

    @Test
    void testGetProductQuantity(){
        assertEquals(100, this.product_valid.getProductQuantity());
    }
}
