//Sean Reed
//7033251
//COSC3p91 Assignment 1

//Vehicle.java

package Traffic_Simulation.Core;

import Traffic_Simulation.Decision.Decision;

//Vehicle leaf class
public abstract class Vehicle implements VehicleComponent {

    //identifer for the vehicle
    protected String id;

    //current RoadSegment the vehicle is on
    protected RoadSegment currentRoad;

    //current lane the vehicle is in
    protected Lane currentLane;

    //the index of the lane the vehicle is currently at
    protected int currentLanePos;

    //the vehicles reputation
    protected int reputation;

    protected Decision nextDecision;

    //how much damage the vehicle has sustained
    protected int dmg;

    //roadPos : position of the vehicle on its current road,
    //updated with each state change, starting from 0-3
    //once it is 3, vehicle will be at an intersection
    //and will be forced to an intersection decision
    protected int roadPos;

    //Constructor
    public Vehicle(RoadSegment currentRoad, Lane currentLane, int currentLanePos, int reputation, int dmg) {
        this.currentRoad = currentRoad;
        this.currentLane = currentLane;
        this.currentLanePos = currentLanePos;
        this.reputation = reputation;
        this.dmg = dmg;
    }

    //Methods

    //the decision the vehicle will make
    public abstract void makeDecision();

    public void setDecision(Decision d){
        this.nextDecision = d;
    }

    public Decision getDecision(){
        return nextDecision;
    }

    //method where the vehicle will peform a lane change
    public void move_changeLane(int laneNumber){
        //to make a lane change, remove this vehicle from current lane
        //then add it to new lane based on lane number
        this.currentLane.removeVehicle(this);
        this.currentLane = this.currentRoad.getLanes().get(laneNumber);
    }

    //method where the vehicle will turn in a direction based on its decision
    public void move_turn(RoadDirection dir){
        //when vehicle is at an intersection, turn onto new road segment
        //based on the direction it wants to go
        
        //find end intersection
        Intersection end = this.currentRoad.getEnd();

        //find the road segment that goes in the direction the vehicle wants to go
        for (RoadSegment r : end.getOutgoingSegments()){
            if (r.getDirection() == dir){
                //remove vehicle from current road segment
                this.currentRoad.removeVehicle(this);
                //add vehicle to new road segment
                r.addVehicle(this, this.currentLane.getLaneNumber(), this.currentLanePos);
            }
        }

    }

    //method where vehicle will continue straight
    public void move_straight(){
        //remove vehicle from current road segment
        this.currentRoad.removeVehicle(this);
        //add vehicle to new road segment
        this.currentRoad.getEnd().getOutgoingSegments().get(this.currentLanePos).addVehicle(this, this.currentLane.getLaneNumber(), this.currentLanePos);
    }

    //method where vehicle's reputation is updated
    public void updateReputation(int x){
        this.reputation += x;
    }

    //method where if a collision occurs, calculates damage this vehicle recieves
    public void calculateDamage(){
        //if a collision occurs, the vehicle will take damage
        //the amount of damage is based on the vehicles reputation
        //if the vehicle has a high reputation, it will take less damage
        //if the vehicle has a low reputation, it will take more damage
        if(this.reputation > 50){
            this.dmg += 1;
        }
        else if(this.reputation < 50){
            this.dmg += 2;
        }
    }

    public int getDamage(){
        return this.dmg;
    }

    public String getId() {
        return this.id;
    }

    public Lane getCurrentLane() {
        return this.currentLane;
    }

    public void setCurrentRoad(RoadSegment road) {
        this.currentRoad = road;
    }

    public RoadSegment getCurrentRoad() {
        return this.currentRoad;
    }

    public int getRoadPos() {
        return this.roadPos;
    }

    public void setCurrentLane(Lane lane, int laneNumber) {
        this.currentLane = lane;
        this.currentLanePos = laneNumber;
    }

    //implement the displayVehicleInfo method from component interface
    @Override
    public void displayVehicleInfo() {}

}

//composite class for vehicle
public class VehicleGroup implements VehicleComponent {
    private List<VehicleComponent> vehicles = new ArrayList<>();

    //add vehicles to composite class
    public void addVehicle(VehicleComponent vehicle) {
        vehicles.add(vehicle);
    }

    //remove vehicles from composite class
    public void removeVehicle(VehicleComponent vehicle) {
        vehicles.remove(vehicle);
    }

    //getter method for vehicle component
    public VehicleComponent getVehicle(int index) {
        return vehicles.get(index);
    }

    //display vehicle info for all vehicles in the composite class
    @Override
    public void displayVehicleInfo() {
        for (VehicleComponent vehicle : vehicles) {
            vehicle.displayVehicleInfo();
        }
    }
}