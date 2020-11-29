import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class Main {

    public static void main(String[] args) {
        ArrayList<Road> roads = new ArrayList<>();
        // Add default road "000"
        Road default_road = new Road("000", 5, 1, new int[]{0,0}, "vertical");
        roads.add(default_road);
        // user input information
        Scanner controller = new Scanner((System.in));
        // Add roads
        System.out.println("Number of roads: ");
        int number_road = controller.nextInt();
        for(int i = 1; i < number_road; i++){
            System.out.println("Road" + i+":");
            System.out.println("length:");
            int length = controller.nextInt();
            System.out.println("SpeedLimit:");
            int speedLimit = controller.nextInt();

            System.out.println("Vector: ");
            String vector = controller.next();
            String id = "00"+i;
            Road road = new Road(id, length, speedLimit, new int[]{0,0}, vector);
            roads.add(road);
        }

        create_roads(roads);

        // input number of cars
        System.out.println("Number of cars:");
        int number_car = controller.nextInt();
        // input number of lights
        System.out.println("Number of lights: ");
        int number_light = controller.nextInt();
        if (number_light > number_road){
            System.out.println("Lights can not more than roads");
            System.out.println("Number of lights: ");
            number_light = controller.nextInt();
        }
        // input time
        System.out.println("Time: ");
        int time = controller.nextInt();

        System.out.println("Number of Road: "+ number_road);
        for (Road road: roads){
            System.out.println("Id: "+ road.getName() + " Start: "+ Arrays.toString(road.start_location) + " End: " + Arrays.toString(road.end_location));
        }
        System.out.println("Number of car: " + number_car);
        System.out.println("Number of light: " + number_light);
        System.out.println(time);
        ArrayList<Car> cars = setCarLocation(roads, number_car);
        for (Car car: cars){
            System.out.println("Id: " +car.getId() + " Position: "+ Arrays.toString(car.getPosition()));
        }
        ArrayList<TrafficLight> lights = (ArrayList<TrafficLight>) setLight(roads, number_light);
        run_(cars, lights, time);
    }

    public static void create_roads(ArrayList<Road> roads){
        for (int i = 1; i< roads.size(); i++){
            Road current_road = roads.get(i);
            Road pass_road = roads.get(i-1);
            current_road.start_location = new int[]{pass_road.end_location[0] + 1, pass_road.getEnd_location()[1]};
            if (current_road.getVector().equals("vertical")){
                current_road.end_location = new int[]{current_road.start_location[0], current_road.start_location[1] + current_road.getLength()};
            }else if (current_road.getVector().equals("horizontal")){
                current_road.end_location = new int[]{current_road.start_location[0] + current_road.getLength(), current_road.start_location[1]};
            }
            pass_road.getConnectedRoad().add(current_road);
        }
    }

    public static void run_(ArrayList<Car> cars, ArrayList<TrafficLight> lights, int speedOfSim){
        //Simulation loop:
        System.out.println("Simulation:");
        Random random = new Random();
        int time = 0;
//        System.out.print("Set time scale in milliseconds:");
//        int speedOfSim = simController.nextInt();
        int cars_finished = 0;
        while (cars_finished < cars.size()) {
            for (TrafficLight light : lights) {
//                light.operate(random.nextInt());
                light.changState(random.nextInt());
                light.printLightStatus();
            }
            for (Car car : cars) {
                car.move();
                car.printCarStatus();
                System.out.println("Start location: "+ Arrays.toString(car.getCurrentRoad().start_location));
                if (car.getCurrentRoad().getConnectedRoad().isEmpty() && (car.getSpeed() == 0)) {
                    cars_finished = cars_finished + 1;

                }
            }
            time = time + 1;
            System.out.println(time + " Seconds have passed.\n");
            System.out.println("Cars finished: " + cars_finished);
            try {
                Thread.sleep(speedOfSim); // set speed of simulation.
            } catch (InterruptedException sim) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static List<TrafficLight> setLight(ArrayList<Road> roads, int number_light){
        ArrayList<TrafficLight> lights = new ArrayList<>();
        for (int i = 0; i< number_light; i++){
            TrafficLight light = new TrafficLight("00"+ i, roads.get(i));
            lights.add(light);
        }
        return lights;
    }


    public static ArrayList<Car> setCarLocation(ArrayList<Road> roads, int number_car){
        ArrayList<Car> cars = new ArrayList<>();
        for (int i = 0; i< number_car; i++){
            Road road = roads.get(0);
            cars.add(new Car("00"+i,road));
        }
//        for (Road road: roads){
//            if (road.getCarsOnRoad().size()> 1){
//                for (int i = 1; i < road.getCarsOnRoad().size(); i++){
//                    if (road.getVector().equals("horizontal")){
//                        road.getCarsOnRoad().get(i).updatePosition(new int[] {road.start_location[0]+ i, road.start_location[1]});
//                    }else{
//                        road.getCarsOnRoad().get(i).updatePosition(new int[] {road.start_location[0], road.start_location[1]+i});
//                    }
//
//                }
//            }
//        }
        return cars;
    }
}
