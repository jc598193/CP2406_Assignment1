import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Road> roads = new ArrayList<>();
        Scanner controller = new Scanner((System.in));
        System.out.println("Number of roads: ");
        int number_road = controller.nextInt();
        for(int i = 1; i <= number_road; i++){
            System.out.println("Road" + i+":");
            System.out.println("length:");
            int length = controller.nextInt();
            System.out.println("SpeedLimit:");
            int speedLimit = controller.nextInt();
            System.out.println("Start location:");
            int[] start_location = new int[10];
            for (int j=0; j<2; j++){
                start_location[j] = controller.nextInt();
            }

            System.out.println("Vector: ");
            String vector = controller.next();
            String id = "00"+i;
            Road road = new Road(id, length, speedLimit, start_location, vector);
            roads.add(road);
        }

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


}
