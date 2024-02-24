package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarRepositoryTest {

    @InjectMocks
    CarRepository carRepository;
    @BeforeEach
    void setUp(){
    }
    @Test
    void testCreateAndFind() {
        Car car = new Car();
        car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car.setCarName("Tuyulta");
        car.setCarQuantity("100");
        car.setCarColor("Green");
        carRepository.create(car);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        Car savedCar = carIterator.next();
        assertEquals(car.getCarId(), savedCar.getCarId());
        assertEquals(car.getCarName(), savedCar.getCarName());
        assertEquals(car.getCarQuantity(), savedCar.getCarQuantity());
        assertEquals(car.getCarColor(), savedCar.getCarColor());
    }
    @Test
    void testFindAllIfEmpty() {
        Iterator<Car> carIterator = carRepository.findAll();
        assertFalse(carIterator.hasNext());
    }
    @Test
    void testFindAllIfMoreThanOneCar(){
        Car car1 = new Car();
        car1.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car1.setCarName("Tuyulta");
        car1.setCarQuantity("100");
        car1.setCarColor("Green");
        carRepository.create(car1);

        Car car2 = new Car();
        car2.setCarId("aof9de46-90b1-437d-a0bf-d0821dde9096");
        car2.setCarName("Hendi");
        car2.setCarQuantity("50");
        car2.setCarColor("Blue");
        carRepository.create(car2);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        Car savedCar = carIterator.next();
        assertEquals(car1.getCarId(), savedCar.getCarId());
        savedCar = carIterator.next();
        assertEquals(car2.getCarId(), savedCar.getCarId());
        assertFalse(carIterator.hasNext());
    }

    @Test
    void testCreateAndDelete() {
        Car car = new Car();
        car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car.setCarName("Tuyulta");
        car.setCarQuantity("100");
        car.setCarColor("Green");
        carRepository.create(car);

        carRepository.delete(car.getCarId());
        Iterator<Car> carIterator = carRepository.findAll();
        assertFalse(carIterator.hasNext());
    }

    @Test
    void testCreateDeleteThenGet() {
        Car car = new Car();
        car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car.setCarName("Tuyulta");
        car.setCarQuantity("100");
        car.setCarColor("Green");
        carRepository.create(car);

        carRepository.delete(car.getCarId());
        assertNull(carRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6"));
    }

    @Test
    void testCreateThenEdit() {
        Car car = new Car();
        car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car.setCarName("Tuyulta");
        car.setCarQuantity("100");
        car.setCarColor("Green");
        carRepository.create(car);

        Car updatedCar = new Car();
        updatedCar.setCarId("eb558e9f-1c39-46de-8260-deadbeefcafe");
        updatedCar.setCarName("Hendi");
        updatedCar.setCarQuantity("50");
        updatedCar.setCarColor("Blue");

        String carId = car.getCarId();
        carRepository.update(carId, updatedCar);
        car = carRepository.findById(carId);
        assertEquals("Hendi", car.getCarName());
        assertEquals("50", car.getCarQuantity());
        assertEquals("Blue", car.getCarColor());
    }

    @Test
    void testEditWhenEmpty() {
        Car updatedCar = new Car();
        updatedCar.setCarId("eb558e9f-1c39-46de-8260-deadbeefcafe");
        updatedCar.setCarName("Hendi");
        updatedCar.setCarQuantity("50");
        updatedCar.setCarColor("Blue");

        String fakeCarId = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        Car editStatus = carRepository.update(fakeCarId, updatedCar);
        Car car = carRepository.findById(fakeCarId);
        assertNull(editStatus);
        assertNull(car);
    }

    @Test
    void testCreateThenEditNonExistentItem() {
        Car car = new Car();
        car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car.setCarName("Tuyulta");
        car.setCarQuantity("100");
        carRepository.create(car);

        String carId = car.getCarId();
        Car res = carRepository.update("bbd91076-f842-4d76-a430-f42adb27889f", new Car());
        assertNull(res);
    }
}
