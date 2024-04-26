//Sean Reed
//7033251
//COSC3p91 Assignment 1

//RoadSegment.java

package Traffic_Simulation.Core;
import java.util.List;

//RoadSegment class, represents edge in traffic network (map class)
public class RoadSegment {
    private Intersection start; //node that edge starts at
    private Intersection end; //node that edge ends at
    private List<Lane> lanes; //list of lanes in this road segment
    private List<Vehicle> currentVehicles; //list of current vehicles on this roadsegment
    private int id; //id of this roadsegment
    private RoadDirection dir; //direction of traffic on this roadsegment

    //Constructor
    public RoadSegment(int id, Intersection start, Intersection end, List<Lane> lanes, List<Vehicle> currentVehicles) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.lanes = lanes;
        this.currentVehicles = currentVehicles;
    }

    //Methods

    //adds vehicles to the roadsegment
    public void addVehicle(Vehicle v, int laneNumber, int lanePos){
        this.currentVehicles.add(v);
        this.lanes.get(laneNumber).addVehicle(v);
        v.setCurrentLane(this.lanes.get(laneNumber), lanePos);
    } 

    //removes vehicles this roadsegment
    public void removeVehicle(Vehicle v){
        v.getCurrentLane().removeVehicle(v);
        this.currentVehicles.remove(v);
    } 

    public void addLane(Lane l) {
        this.lanes.add(l);
    }

    public int getId() {
        return this.id;
    }

    public List<Lane> getLanes() {
        return this.lanes;
    }

    public List<Vehicle> getVehicles() {
        return this.currentVehicles;
        
    }

    public RoadDirection getDirection() {
        return this.dir;
    }

    public Intersection getEnd() {
        return this.end;
    }
}
