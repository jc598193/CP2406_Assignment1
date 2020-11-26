import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


public class Main {
    public void main(String[] args) {
        ArrayList<Road> roads = new ArrayList<>();
        Road default_road = new Road("000", 5, 2, new int[]{0,0}, "vertical");
        roads.add(default_road);
        Scanner controller = new Scanner((System.in));
        System.out.println("Number of roads: ");
        int number_road = controller.nextInt();
        for(int i = 1; i < number_road; i++){
            System.out.println("Road" + i+":");
            System.out.println("length:");
            int length = controller.nextInt();
            System.out.println("SpeedLimit:");
            int speedLimit = controller.nextInt();
//            System.out.println("Start location:");
//            int[] start_location = new int[10];
//            for (int j=0; j<2; j++){
//                start_location[j] = controller.nextInt();
//            }

            System.out.println("Vector: ");
            String vector = controller.next();
            String id = "00"+i;
            Road road = new Road(id, length, speedLimit, new int[]{0,0}, vector);
            roads.add(road);
        }

        this.create_roads(roads);

        System.out.println("Number of cars:");
        int number_car = controller.nextInt();
        System.out.println("Number of lights: ");
        int number_light = controller.nextInt();
        if (number_light > number_road){
            System.out.println("Lights can not more than roads");
            System.out.println("Number of lights: ");
            number_light = controller.nextInt();
        }
        System.out.println(number_road);
        System.out.println(number_car);
        System.out.println(number_light);
    }

    public void create_roads(ArrayList<Road> roads){
        for (int i = 1; i< roads.size(); i++){
            Road current_road = roads.get(i);
            Road pass_road = roads.get(i-1);
            current_road.start_location = new int[]{pass_road.end_location[0] + 1, pass_road.getEnd_location()[1]};
            if (current_road.getVector().equals("vertical")){
                current_road.end_location = new int[]{current_road.start_location[0], current_road.start_location[1] + current_road.getLength()};
            }else if (current_road.getVector().equals("horizontal")){
                current_road.end_location = new int[]{current_road.start_location[0] + current_road.getLength(), current_road.start_location[1]};
            }
        }
    }

    public void run_(){

    }

    public List<TrafficLight> setLight(ArrayList<Road> roads, int number_light){
        ArrayList<TrafficLight> lights = new ArrayList<>();
        for (int i = 0; i< number_light; i++){
            TrafficLight light = new TrafficLight("00"+ i, roads.get(i));
            lights.add(light);
        }
        return lights;
    }


    public List<Car> setCarLocation(ArrayList<Road> roads, int number_car){
        ArrayList<Car> cars = new ArrayList<>();
        for (int i = 0; i< number_car; i++){
            Road road = roads.get(ThreadLocalRandom.current().nextInt(0, roads.size()+1));
            Car car = new Car("00"+i,road);
            cars.add(car);
        }
        return cars;
    }
}
