import java.util.Arrays;
import java.util.Random;

public class TrafficLight {
    private String id;
    private int[] location;
    private static final String RED = "red";
    private static final String GREEN = "green";
    private static final double CHANGE_GREEN = 0.5;
    private Road roadIncluded;
    private String state;


    public TrafficLight(String id, Road road){
        this.id = "light_" + id;
        this.state = RED;
        this.roadIncluded = road;
        this.location = this.roadIncluded.end_location;
        this.roadIncluded.getLightsOnRoad().add(this);
    }

    public void changState(int seed){

        Random random = new Random(seed);
        double probability = random.nextDouble();
        if (probability > CHANGE_GREEN) {
            this.state = GREEN;
        } else {
            this.state = RED;
        }

    }

    public String getState(){
        return this.state;
    }

    public int[] getLocation(){
        return this.location;
    }

    public String getId(){
        return id;
    }

    public Road getRoadIncluded() {
        return roadIncluded;
    }

    public void printLightStatus() {
        System.out.printf("%s is:%s on %s at position:%s%n", this.getId(), this.getState(), this.getRoadIncluded().getName(), Arrays.toString(this.getLocation()));
    }


}
