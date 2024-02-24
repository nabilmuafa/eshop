package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class CarTest {
    Car car_valid;
    Car car_invalid;
    @BeforeEach
    void setUp() {
        this.car_valid = new Car();
        this.car_valid.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.car_valid.setCarName("Tuyulta");
        this.car_valid.setCarQuantity("100");
        this.car_valid.setCarColor("Hijau");

        this.car_invalid = new Car();
        this.car_invalid.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.car_invalid.setCarName("");
        this.car_invalid.setCarQuantity("-100");
    }

    @Test
    void testGetCarId(){
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", this.car_valid.getCarId());
    }

    @Test
    void testGetCarName(){
        assertEquals("Tuyulta", this.car_valid.getCarName());
    }

    @Test
    void testGetCarQuantity(){
        assertEquals("100", this.car_valid.getCarQuantity());
    }

    @Test
    void testGetCarColor() {
        assertEquals("Hijau", this.car_valid.getCarColor());
    }
}
