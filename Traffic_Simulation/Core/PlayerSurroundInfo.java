//Sean Reed
//7033251
//COSC3p91 Assignment 1

//PlayerSurroundInfo.java

package Traffic_Simulation.Core;
import java.util.List;

public class PlayerSurroundInfo {
    private List<Vehicle> nearbyVehicles; //list of nearby vehicles

    //constructor
    public PlayerSurroundInfo(List<Vehicle> nearbyVehicles) {
        this.nearbyVehicles = nearbyVehicles;
    }

    public List<Vehicle> getNearbyVehicles() {
        return this.nearbyVehicles;
    }

    //method to add a vehicle to the list of nearby vehicles
    public void addVehicle(Vehicle v){
        this.nearbyVehicles.add(v);
    }

    //find vehicles in nearby lanes on the same road segment
    public void addNearbyVehicles(Vehicle v){
        
        //get the current lane of the player
        Lane currentLane = v.getCurrentLane();

        //search adjacent lanes for vehicles
        for (Lane l : v.getCurrentRoad().getLanes()){
            if (l.getLaneNumber() == currentLane.getLaneNumber() - 1 || l.getLaneNumber() == currentLane.getLaneNumber() + 1){
                if (l.getVehicles().size() > 0){
                    //add the vehicles to the list of nearby vehicles
                    //for each vehicle in the lane
                    for (Vehicle vehicle : l.getVehicles()){
                        this.nearbyVehicles.add(vehicle);
                    }
                }
            }
        }
    }
    
}