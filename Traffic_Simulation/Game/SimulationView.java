//Sean Reed
//7033251
//COSC3P91 Assignment 3

//Class for displaying traffic network to console and later GUI
package Traffic_Simulation.Game;
import java.util.List;

import Traffic_Simulation.Core.Vehicle;

public class SimulationView {
    
    //constructor
    public SimulationView(){
        
    }

    //display sim info to console
    public String displaySimulationInfo(Simulation sim){
        return "";
    }

    //display just the map to console
    public void displayMap(){}

    //getter for car count used for simulation info
    public int getCarCount() {
        return 0;
    }

    //getter for vehicle count in a road used for simulation info
    public List<Vehicle> getVehiclesInRoad(){
        return null;
    }

    //getter for vehicle count in a lane used for simulation info
    public List<Vehicle> getVehiclesInLane(){
        return null;
    }


}
