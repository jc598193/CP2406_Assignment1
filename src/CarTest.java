import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarTest {
    Road road = new Road("001",20,4,new int[]{0,1}, "horizontal");
    Car car = new Car("001", road, road.start_location);

//    @Test
//    void testIntList(){
//        assertEquals(new int[]{0,1}, new int[]{0,1});
//    }

    @Test
    void testGetLength(){
        assertEquals(1, car.getLength());
    }

    @Test
    void testGetId(){
        assertEquals("car_001", car.getId());
    }

    @Test
    void testGetSpeed(){
        assertEquals(4, car.getSpeed());
    }

    @Test
    void testGetPosition(){
        int[] position = {0,1};
        assertArrayEquals(position, car.getPosition());
    }

//    @Test
//    void testUpdatePosition(){
//        car.updatePosition(new int[]{0,5});
//        int[] expected = {0,5};
//        assertArrayEquals(expected, car.getPosition());
//    }



}
