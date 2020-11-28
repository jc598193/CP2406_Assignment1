import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LightTest {
    Road road = new Road("001",20,4,new int[]{0,1}, "horizontal");
    Car car1 = new Car("001", road, road.start_location);
    TrafficLight light = new TrafficLight("001",road);

    @Test
    void testId(){
        assertEquals("light_001", light.getId());
    }

    @Test
    void testState(){
        assertEquals("red", light.getState());
    }

    @Test
    void testLocation(){
        int[] location = {20,1};
        assertArrayEquals(location, light.getLocation());
    }

}
