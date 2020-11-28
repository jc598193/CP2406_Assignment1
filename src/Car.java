import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Car {
    String id;
    static float length;
    private int speed;
    private int[] position;
    private Road currentRoad;
    private static final int[] DEFAULT_POSITION = new int[]{0,0};
    private static final int  STOP = 0;
    private static float breadth;


    public Car(String id, Road road, int[] position) {
        this.id = "car_" + id;
        length = 1f;
        breadth = length * 0.5f;
        this.position = position;
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
        // horizontal road
        if (this.currentRoad.getVector().equals("horizontal")) {
            // car is not in the end:
            if (this.getPosition()[0] + this.getSpeed() < this.currentRoad.getEnd_location()[0]) {
                this.position[0] = this.position[0] + this.speed;
            }
            // car in the end
            else if (this.getPosition()[0] + this.getSpeed() == this.currentRoad.getEnd_location()[0]) {
                this.position[0] = this.position[0] + this.speed;
                // road still has light and it is red
                if (!this.currentRoad.getLightsOnRoad().isEmpty() && this.currentRoad.getLightsOnRoad().get(0).getLocation()[0] > this.getPosition()[0] && this.currentRoad.getLightsOnRoad().get(0).getState().equals("red")) {
                    this.stop();
                    // road still has light and it is green
                    // or road does not have light
                    // or light is passed
                }
//                else {
//                    if (this.currentRoad.getConnectedRoad().isEmpty()){
//                        this.stop();
//                        this.currentRoad.getCarsOnRoad().remove(this);
//                    }else{
//                        this.currentRoad.getCarsOnRoad().remove(this);
//                        this.currentRoad = this.currentRoad.getConnectedRoad().get(0);
//                        this.currentRoad.getCarsOnRoad().add(this);
//                        this.position = this.currentRoad.start_location;
//                        this.speed = this.currentRoad.getSpeedLimit();
//                    }
//                }
            // car pass the end
            } else {
                // road has light and it is red
                if (!this.currentRoad.getLightsOnRoad().isEmpty() && this.currentRoad.getLightsOnRoad().get(0).getLocation()[0] > this.getPosition()[0] && this.currentRoad.getLightsOnRoad().get(0).getState().equals("red")) {
                    this.position = this.currentRoad.end_location;
                    this.stop();
                    // road still has light and it is green
                    // or road does not have light
                    // or light is passed
                } else {
                    if (!this.currentRoad.getConnectedRoad().isEmpty()){
                        int pass_distance = this.currentRoad.end_location[0] - this.getPosition()[0];
                        float current_time = ((float) this.speed - pass_distance) / this.speed;
                        this.currentRoad.getCarsOnRoad().remove(this);
                        this.currentRoad = this.currentRoad.getConnectedRoad().get(0);
                        this.currentRoad.getCarsOnRoad().add(this);
                        this.speed = this.currentRoad.getSpeedLimit();
                        if (this.currentRoad.getVector().equals("horizontal")) {
                            this.position = new int[]{this.currentRoad.start_location[0] + ((int) current_time * this.speed), this.currentRoad.start_location[1]};
                        } else {
                            this.position = new int[]{this.currentRoad.start_location[0], this.currentRoad.start_location[1] + ((int) current_time * this.speed)};
                        }
                    }else{
                        this.stop();
                        this.currentRoad.getCarsOnRoad().remove(this);
                    }
                }
            }
        }else if (this.currentRoad.getVector().equals("vertical")) {
            // car is not in the end:
            if (this.getPosition()[1] + this.getSpeed() < this.currentRoad.getEnd_location()[1]) {
                this.position[1] = this.position[1] + this.speed;
            }
            // car in the end
            else if (this.getPosition()[1] + this.getSpeed() == this.currentRoad.getEnd_location()[1]) {
                this.position[1] = this.position[1] + this.speed;
                // road still has light and it is red
                if (!this.currentRoad.getLightsOnRoad().isEmpty() && this.currentRoad.getLightsOnRoad().get(0).getLocation()[1] > this.getPosition()[1] && this.currentRoad.getLightsOnRoad().get(0).getState().equals("red")) {
                    this.stop();
                    // road still has light and it is green
                    // or road does not have light
                    // or light is passed
                } else {
                    if (this.currentRoad.getConnectedRoad().isEmpty()){
                        this.stop();
                        this.currentRoad.getCarsOnRoad().remove(this);
                    }else{
                        this.currentRoad.getCarsOnRoad().remove(this);
                        this.currentRoad = this.currentRoad.getConnectedRoad().get(0);
                        this.currentRoad.getCarsOnRoad().add(this);
                        this.position = this.currentRoad.start_location;
                        this.speed = this.currentRoad.getSpeedLimit();
                    }
                }
            } else {
                // road has light and it is red
                if (!this.currentRoad.getLightsOnRoad().isEmpty() && this.currentRoad.getLightsOnRoad().get(0).getLocation()[1] > this.getPosition()[1] && this.currentRoad.getLightsOnRoad().get(0).getState().equals("red")) {
                    this.position = this.currentRoad.end_location;
                    this.stop();
                    // road still has light and it is green
                    // or road does not have light
                    // or light is passed
                } else {
                    if (!this.currentRoad.getConnectedRoad().isEmpty()){
                        int pass_distance = this.currentRoad.end_location[0] - this.getPosition()[0];
                        float current_time = ((float) this.speed - pass_distance) / this.speed;
                        this.currentRoad.getCarsOnRoad().remove(this);
                        this.currentRoad = this.currentRoad.getConnectedRoad().get(0);
                        this.currentRoad.getCarsOnRoad().add(this);
                        this.speed = this.currentRoad.getSpeedLimit();
                        if (this.currentRoad.getVector().equals("horizontal")) {
                            this.position = new int[]{this.currentRoad.start_location[0] + ((int) current_time * this.speed), this.currentRoad.start_location[1]};
                        } else {
                            this.position = new int[]{this.currentRoad.start_location[0], this.currentRoad.start_location[1] + ((int) current_time * this.speed)};

                        }
                    }else{
                        this.stop();
                        this.currentRoad.getCarsOnRoad().remove(this);
                    }

                }
            }

        }
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void printCarStatus() {
        System.out.printf("%s going:%dm/s on %s at position:%s%n", this.getId(), this.getSpeed(), this.getCurrentRoad().
                getName(), Arrays.toString(this.getPosition()));
    }

    public Road getCurrentRoad() {
        return this.currentRoad;
    }
}
