import java.util.ArrayList;

public class Road {
    private String name;
    private int length;
    private String vector;
    private int speedLimit;
    public int[] start_location;
    public int[] end_location;
    private ArrayList<Car> carsOnRoad = new ArrayList<>();
    private ArrayList<Road> connected_roads = new ArrayList<>();
    private ArrayList<TrafficLight> lightsOnRoad = new ArrayList<>();

    public Road(String name, int length, int speedLimit, int[] start_location, String vector){
        this.name = "road_" + name;
        this.length = length;
        this.speedLimit = speedLimit;
        this.start_location = start_location;
        this.vector = vector;
        if (vector.equals("vertical")){
            this.end_location = new int[]{this.start_location[0], this.start_location[1] + this.length};

        }if(vector.equals("horizontal")){
            this.end_location = new int[]{this.start_location[0] + this.length, this.start_location[1]};

        }

    }


    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(int speedLimit){
        this.speedLimit = speedLimit;
    }

    public int getLength(){
        return length;
    }

    public void setLength(int length){
        this.length = length;
    }

    public ArrayList<Car> getCarsOnRoad(){
        return carsOnRoad;
    }
    public void setCarsOnRoad(ArrayList<Car> carsOnRoad){
        this.carsOnRoad = carsOnRoad;
    }

    public ArrayList<TrafficLight> getLightsOnRoad(){
        return lightsOnRoad;
    }
    public void setLightsOnRoad(ArrayList<TrafficLight> lightsOnRoad){
        this.lightsOnRoad = lightsOnRoad;
    }

    public void setVector(String vector) {
        this.vector = vector;
    }

    public String getVector() {
        return vector;
    }

    public void setStart_location(int[] location){
        this.start_location = location;
    }

    public  int[] getEnd_location(){
        return this.end_location;
    }

    public void setConnected_roads(ArrayList<Road> connected_roads) {
        this.connected_roads = connected_roads;
    }

    public ArrayList<Road> getConnectedRoad(){
        return this.connected_roads;
    }
}
