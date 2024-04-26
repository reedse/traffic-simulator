//Sean Reed
//7033251
//COSC3P91 Assignment 3

package Traffic_Simulation.Core;

//abstract method VehicleFactory used for creating diff types of vehicles
public interface VehicleFactory {
    Vehicle createVehicle(RoadSegment currentRoad, Lane currentLane, int currentLanePos, int reputation, int dmg);
}