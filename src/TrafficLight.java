public class TrafficLight {
    private String id;
    private int[] location;
    private static final String RED = "red";
    private static final String GREEN = "green";
    private static final int red_time = 2;
    private static final int green_time = 3;
    private Road roadIncluded;
    private String state;


    public TrafficLight(String id, Road road){
        this.id = "light_" + id;
        this.state = RED;
        this.roadIncluded = road;
        this.location = this.roadIncluded.end_location;
        this.roadIncluded.getLightsOnRoad().add(this);
    }

    public void changState(){
        int time = 0;

        if (this.state.equals(RED)){
            while (time < red_time){
                time = time + 1;
            }if( time == red_time){
                this.state = GREEN;

            }
        }else {
            while (time < green_time){
                time ++;
            }if(time == green_time){
                this.state = RED;

            }

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


}
