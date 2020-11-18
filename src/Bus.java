import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bus extends Car {
    Road currentRoad;
    public Bus(String id, Road road){
        this.id = ("bus_" + id);
        length = super.getLength()*3;
        this.currentRoad = road;
        this.currentRoad.getCarsOnRoad().add(this);
    }

}
