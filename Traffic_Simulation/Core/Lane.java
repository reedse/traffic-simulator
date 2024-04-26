//Sean Reed
//7033251
//COSC3p91 Assignment 1

//Lane.java

package Traffic_Simulation.Core;
import java.util.List;
import Traffic_Simulation.Core.LaneDirection;

public class Lane {

    //indicates lane index within road segment
    private int laneNumber;

    //list of vehicles in this lane
    private List<Vehicle> currentVehicles;

    //integer for max vehicles allowed in this lane
    private int maxVehicles;

    //roadsegment this lane belongs to
    private String currentSegment;

    //what direction this lane is going
    private LaneDirection currentDirection;
    
    //Constructor
    public Lane(int id, String roadSegmentId, int maxVehicles, LaneDirection direction) {
        this.laneNumber = id;
        this.maxVehicles = maxVehicles;
        this.currentSegment = roadSegmentId;
        this.currentDirection = direction;
    }

    //Methods

    //adds a vehicle to this lane
    public void addVehicle(Vehicle v){
        this.currentVehicles.add(v);
    }

    //removes a vehicle from this lane based on player
    public void removeVehicle(Vehicle v){
        this.currentVehicles.remove(v);
    }

    //returns the lane index
    public int getLaneNumber(){
        return laneNumber;}

    //returns list of vehicles on this lane
    public List<Vehicle> getVehicles(){
        return currentVehicles;}

    //returns direction this lane is
    public LaneDirection getDirection(){
        return currentDirection;}

    //method for if this lane is full or not
    public boolean isLaneFull(){
        return currentVehicles.size() == maxVehicles;
    }
}
