import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Get info needed to start sim:
        Scanner simController = new Scanner(System.in);
//        System.out.println("How many roads?");
//        main.setRoadSpawns(simController.nextInt());
//        System.out.println("How many cars?");
//        main.setCarSpawns(simController.nextInt());
//        System.out.println("How many traffic lights?");
//        main.setLightSpawns(simController.nextInt());

        // set values for user inputs for prototype.
//        int roadSpawns = 2;
//        int car_spawns = 2;
//        int lightSpawns = 2;


        //Create objects:
        System.out.println("Object Creation:\n---------------------");

        // input information
        System.out.println("Number of Roads: ");
        int roadSpawns = simController.nextInt();
        System.out.println("Number of Cars: ");
        int car_spawns = simController.nextInt();
        System.out.println("Number of Lights: ");
        int lightSpawns = simController.nextInt();
        // check if lights is more than roads
        if (lightSpawns > roadSpawns){
            System.out.println("Lights can not more than roads.");
            System.out.println("Number of Lights: ");
            lightSpawns = simController.nextInt();
        }
        System.out.println("Roads:");
        // create roads with information.
        ArrayList<Road> all_roads = new ArrayList<>();
        for (int i = 0; i < roadSpawns; i++) {
            System.out.println("Please input parameters for road_" + i + "...");
            System.out.print("Length:");
            int lengthInput = simController.nextInt();
            System.out.print("Speed limit:");
            int speedLimitInput = simController.nextInt();
            System.out.println("Vector: ");
            String vector = simController.next();
            all_roads.add(new Road(Integer.toString(i), speedLimitInput, lengthInput, new int[]{0, 0}, vector));
        }
        // output roads information
        System.out.println("\nRoads;");
        for (Road road : all_roads
        ) {
            road.printRoadInfo();
        }
        // create car
        System.out.println("\nCars;");
        ArrayList<Car> cars = new ArrayList<>();
        for (int i = 0; i < car_spawns; i++) {
            cars.add(new Car(Integer.toString(i), all_roads.get(0))); // all created cars will begin on road_0.
            cars.get(i).printCarStatus();
        }
        System.out.println();

        // set locations and connections:
        System.out.println("Settings:");
        for (int i = 1; i < roadSpawns; i ++){

            all_roads.get(i).setStartLocation(new int[]{all_roads.get(i-1).getEndLocation()[0], all_roads.get(i-1).getEndLocation()[1]});
            all_roads.get(i).printRoadInfo();
            all_roads.get(i-1).getConnectedRoads().add(all_roads.get(i)); // connect road_0 to road_1
        }

        System.out.println();
        // create lights
        System.out.println("\nTraffic Lights;");
        ArrayList<TrafficLight> all_lights = new ArrayList<>();
        for (int i = 0; i < lightSpawns; i++) {
            all_lights.add(new TrafficLight(Integer.toString(i), all_roads.get(i))); // all created lights will begin on start of roads.
            all_lights.get(i).printLightStatus();
        }
        System.out.println();

        //Simulation loop:
        System.out.println("Simulation:");
        Random random = new Random();
        int time = 0;
        System.out.print("Set time scale in milliseconds:");
        int speedOfSim = simController.nextInt();
        int cars_finished = 0;
        while (cars_finished < cars.size()) {
            for (TrafficLight light : all_lights) {
                light.operate(random.nextInt());
                light.printLightStatus();
            }
            for (Car car : cars) {
                car.drive();
                car.printCarStatus();
                if (car.getCurrentRoad().getConnectedRoads().isEmpty() && (car.getSpeed() == 0)) {
                    cars_finished = cars_finished + 1;
                }
            }
            time = time + 1;
            System.out.println(time + " Seconds have passed.\n");
            System.out.println(cars_finished + "finished.\n");
            try {
                Thread.sleep(speedOfSim); // set speed of simulation.
            } catch (InterruptedException sim) {
                Thread.currentThread().interrupt();
            }
        }


    }
}
