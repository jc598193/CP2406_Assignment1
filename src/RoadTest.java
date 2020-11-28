import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoadTest {
    Road road = new Road("001",20,4,new int[]{0,1}, "horizontal");
    Car car1 = new Car("001", road, road.start_location);
    TrafficLight light = new TrafficLight("001",road);

    @Test
    public void testGetName(){
        assertEquals("road_001", road.getName());
    }

    @Test
    public void testGetLength(){
        assertEquals(20, road.getLength());
    }

    @Test
    public void testGetSpeed(){
        assertEquals(4, road.getSpeedLimit());
    }

    @Test
    public void testGetVector(){
        assertEquals("horizontal", road.getVector());
    }

    @Test
    public void testCars(){
        ArrayList<Car> cars = new ArrayList<>();
        cars.add(car1);
        assertEquals(cars, road.getCarsOnRoad());
    }

    @Test
    public void testLights(){
        ArrayList<TrafficLight> lights = new ArrayList<>();
        lights.add(light);
        assertEquals(lights, road.getLightsOnRoad());
    }

    @Test
    public void testStart_location(){
        int[] test = {0,1};
        assertArrayEquals(test, road.start_location);
    }
}
