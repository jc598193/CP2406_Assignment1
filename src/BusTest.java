import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BusTest {
    Road road = new Road("001",20,4,new int[]{0,1}, "horizontal");
    Bus bus1 = new Bus("001", road);
    TrafficLight light = new TrafficLight("001",road);

    @Test
    void testGetId(){
        assertEquals("bus_001", bus1.getId());
    }

    @Test
    void testGetLength(){
        assertEquals(3, bus1.getLength());
    }

    @Test
    void testGetSpeed(){
        assertEquals(4, bus1.getSpeed());
    }

    @Test
    void testGetPosition(){
        int[] position = {0,1};
        assertArrayEquals(position, bus1.getPosition());
    }
}
