public class Car {
    private static final int STOPPED = 0; //car speed is 0m/s
    private static final int NEXT_ROAD_INDEX = 0;
    private static final int START_POSITION = 1;
    String id; // unique identifier
    static float length; // number of segments occupied, 1 for ease of prototype.
    private static float breadth;
    private int speed; //segments moved per turn
    private int xpos; // position on current road
    private int ypos;
    private Road currentRoad; // current Road object


    public Car(String id, Road currentRoad) {
        this.id = "car_" + id;
        this.currentRoad = currentRoad;
        length = 1f; // cars made 1m long for prototype.
        breadth = length * 0.5f;
        speed = 0;
        xpos = 0;
        ypos = 0;
        this.currentRoad.getCarsOnRoad().add(this); //add this car to the road its on.
    }

    public Car() {
        id = "000";
        length = 1f;
        breadth = length * 0.5f;
        speed = 0;
        xpos=0;
        ypos = 0;
    }
    public void drive(){
        // horizontal road
        if (this.currentRoad.getVector().equals("horizontal")) {
            this.speed = this.currentRoad.getSpeedLimit();
            // car is not in the end:
            if (this.xpos + this.getSpeed() < this.currentRoad.getEndLocation()[0]) {
                this.xpos = this.xpos + this.speed;
            }
            // car in the end
            else if (this.xpos + this.speed == this.currentRoad.getEndLocation()[0]) {
                this.xpos = this.xpos + this.speed;
                // road still has light and it is red
                if (!this.currentRoad.getLightsOnRoad().isEmpty() && this.currentRoad.getLightsOnRoad().get(0).getPosition()[0] == this.xpos && this.currentRoad.getLightsOnRoad().get(0).getState().equals("red")) {
                    this.speed = STOPPED;
                    // road still has light and it is green
                    // or road does not have light
                    // or light is passed
                }
                else {
                    if (this.currentRoad.getConnectedRoads().isEmpty()){
                        this.speed = STOPPED;
                        this.currentRoad.getCarsOnRoad().remove(this);
                    }else{
                        this.currentRoad.getCarsOnRoad().remove(this);
                        this.currentRoad = this.currentRoad.getConnectedRoads().get(0);
                        this.currentRoad.getCarsOnRoad().add(this);
                        this.xpos = this.currentRoad.getStartLocation()[0];
                        this.ypos = this.currentRoad.getStartLocation()[1];
                        this.speed = this.currentRoad.getSpeedLimit();
                    }
                }
            // car pass the end
            } else {
                // road has light and it is red
                if (!this.currentRoad.getLightsOnRoad().isEmpty() && this.currentRoad.getLightsOnRoad().get(0).getPosition()[0] >= this.xpos && this.currentRoad.getLightsOnRoad().get(0).getState().equals("red")) {
                    this.xpos = this.currentRoad.getEndLocation()[0];
                    this.speed = STOPPED;
                    // road still has light and it is green
                    // or road does not have light
                    // or light is passed
                } else {
                    if (!this.currentRoad.getConnectedRoads().isEmpty()){
                        int pass_distance = this.currentRoad.getEndLocation()[0] - this.xpos;
                        float current_time = ((float) this.speed - pass_distance) / this.speed;
                        this.currentRoad.getCarsOnRoad().remove(this);
                        this.currentRoad = this.currentRoad.getConnectedRoads().get(0);
                        this.currentRoad.getCarsOnRoad().add(this);
                        this.speed = this.currentRoad.getSpeedLimit();
                        if (this.currentRoad.getVector().equals("horizontal")) {
                            this.xpos = this.currentRoad.getStartLocation()[0] + ((int) current_time * this.speed);
                            this.ypos = this.currentRoad.getStartLocation()[1];
                        } else {
                            this.xpos = this.currentRoad.getStartLocation()[0];
                            this.ypos = this.currentRoad.getStartLocation()[1] + ((int) current_time * this.speed);
                        }
                    }else{
                        this.speed = STOPPED;
                        this.currentRoad.getCarsOnRoad().remove(this);
                    }
                }
            }
        }else if (this.currentRoad.getVector().equals("vertical")) {
            this.speed = this.currentRoad.getSpeedLimit();
            // car is not in the end:
            if (this.ypos + this.speed < this.currentRoad.getEndLocation()[1]) {
                this.ypos = this.ypos + this.speed;
            }
            // car in the end
            else if (this.ypos + this.speed == this.currentRoad.getEndLocation()[1]) {
                this.ypos = this.ypos + this.speed;
                // road still has light and it is red
                if (!this.currentRoad.getLightsOnRoad().isEmpty() && this.currentRoad.getLightsOnRoad().get(0).getPosition()[1] == this.ypos && this.currentRoad.getLightsOnRoad().get(0).getState().equals("red")) {
                    this.speed = STOPPED;
                    // road still has light and it is green
                    // or road does not have light
                    // or light is passed
                } else {
                    if (this.currentRoad.getConnectedRoads().isEmpty()){
                        this.speed = STOPPED;
                        this.currentRoad.getCarsOnRoad().remove(this);
                    }else{
                        this.currentRoad.getCarsOnRoad().remove(this);
                        this.currentRoad = this.currentRoad.getConnectedRoads().get(0);
                        this.currentRoad.getCarsOnRoad().add(this);
                        this.xpos = this.currentRoad.getStartLocation()[0];
                        this.ypos = this.currentRoad.getStartLocation()[1];
                        this.speed = this.currentRoad.getSpeedLimit();
                    }
                }
            } else {
                // road has light and it is red
                if (!this.currentRoad.getLightsOnRoad().isEmpty() && this.currentRoad.getLightsOnRoad().get(0).getPosition()[1] >= this.ypos && this.currentRoad.getLightsOnRoad().get(0).getState().equals("red")) {
                    this.ypos = this.currentRoad.getEndLocation()[1];
                    this.speed = STOPPED;
                    // road still has light and it is green
                    // or road does not have light
                    // or light is passed
                } else {
                    if (!this.currentRoad.getConnectedRoads().isEmpty()){
                        int pass_distance = this.currentRoad.getEndLocation()[1] - this.ypos;
                        float current_time = ((float) this.speed - pass_distance) / this.speed;
                        this.currentRoad.getCarsOnRoad().remove(this);
                        this.currentRoad = this.currentRoad.getConnectedRoads().get(0);
                        this.currentRoad.getCarsOnRoad().add(this);
                        this.speed = this.currentRoad.getSpeedLimit();
                        if (this.currentRoad.getVector().equals("horizontal")) {
                            this.xpos = this.currentRoad.getStartLocation()[0] + ((int) current_time * this.speed);
                            this.ypos = this.currentRoad.getStartLocation()[1];
                        } else {
                            this.xpos = this.currentRoad.getStartLocation()[0];
                            this.ypos = this.currentRoad.getStartLocation()[1] + ((int) current_time * this.speed);

                        }
                    }else{
                        this.speed = STOPPED;
                        this.currentRoad.getCarsOnRoad().remove(this);
                    }

                }
            }

        }
    }

    public void printCarStatus() {
        System.out.printf("%s going:%dm/s on %s at position: [%s,%s]%n", this.getId(), this.getSpeed(), this.getCurrentRoad().
                getId(), this.getXpos(), this.getYpos());
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        Car.length = length;
    }

    public float getBreadth() {
        return breadth;
    }

    public void setBreadth(float breadth) {
        Car.breadth = breadth;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getXpos() {
        return xpos;
    }

    public void setXpos(int xpos) {
        this.xpos = xpos;
    }

    public int getYpos() {
        return ypos;
    }

    public void setYpos(int ypos) {
        this.ypos = ypos;
    }

    public Road getCurrentRoad() {
        return currentRoad;
    }

    public void setCurrentRoad(Road currentRoad) {
        this.currentRoad = currentRoad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}

