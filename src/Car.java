import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Car {
    String id;
    static float length;
    private int speed;
    private int[] position;
    private Road currentRoad;
    private static final int[] DEFAULT_POSITION = new int[]{0,1};
    private static final int  STOP = 0;
    private static float breadth;


    public Car(String id, Road road) {
        this.id = "car_" + id;
        length = 1f;
        breadth = length * 0.5f;
        this.position = DEFAULT_POSITION;
        this.currentRoad = road;
        this.currentRoad.getCarsOnRoad().add(this);
    }

    public Car(){
        id = "000";
        length = 1f;
        breadth = length * 0.5f;
        speed = 0;
        position = DEFAULT_POSITION;
    }

    public String getId() {
        return id;
    }

    public float getLength() {
        return length;
    }

    public void updatePosition(int[] position){
        this.position = position;
    }

    public int[] getPosition() {
        return position;
    }

    public void stop(){
        this.speed = STOP;
    }

    public int getSpeed(){
        return this.speed;
    }

    public void drive(){

        this.speed = this.currentRoad.getSpeedLimit();

        if (this.getPosition() == this.currentRoad.getEnd_location()){
            if (!this.currentRoad.getLightsOnRoad().isEmpty()){
                if (this.currentRoad.getLightsOnRoad().get(0).getState().equals("red")){
                    this.stop();
                }else if (!this.currentRoad.getConnectedRoad().isEmpty()){
                    this.currentRoad.getCarsOnRoad().remove(this);
                    this.currentRoad = this.currentRoad.getConnectedRoad().get(0);
                    this.currentRoad.getCarsOnRoad().add(this);
                    this.position = this.currentRoad.start_location;
                    this.speed = this.currentRoad.getSpeedLimit();
                }else{
                    this.stop();
                    this.currentRoad.getCarsOnRoad().remove(this);
                }
            }
        }else if (this.getPosition()[0] < this.currentRoad.getEnd_location()[0]){
            this.position[0] = this.position[0] + this.speed;
        }else if (this.getPosition()[1] < this.currentRoad.getEnd_location()[1]){
            this.position[1] = this.position[1] + this.speed;
        }else {
            this.stop();
        }
    }

    private void setSpeed(int speed) {
        this.speed = speed;
    }

}
