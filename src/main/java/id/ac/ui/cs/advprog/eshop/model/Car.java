package id.ac.ui.cs.advprog.eshop.model;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Car {
    private String carId;
    private String carName;
    private String carColor;
    private String carQuantity;

    public void update(Car car){
        this.setCarName(car.getCarName());
        this.setCarColor(car.getCarColor());
        this.setCarQuantity(car.getCarQuantity());
    }
}
