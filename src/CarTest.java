import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CarTest {
    Road road = new Road("0", 1, 5, new int[]{0, 0}, "horizontal");
    Car car = new Car("0", road);

    @Test
    void testMove() {
        car.drive();
        assertEquals(2, car.getXpos());
    }

    @Test
    void getLength() {
        assertEquals(1, car.getLength());
    }

    @Test
    void getBreadth() {
        assertEquals(0.5, car.getBreadth());
    }

    @Test
    void getSpeed() {
        assertEquals(0, car.getSpeed());
    }

    @Test
    void getPosition() {
        assertEquals(0, car.getXpos());
    }

    @Test
    void getRoad() {
        assertEquals(road, car.getCurrentRoad());
    }

    @Test
    void getId() {
        assertEquals("car_0", car.getId());
    }

}