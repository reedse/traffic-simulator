package Traffic_Simulation.Core;

//Sean Reed
//7033251
//COSC3P91 Assignment 3

//concrete factory classes that implement the VehicleFactory interface
public class TruckFactory implements VehicleFactory {
    @Override
    public Vehicle createVehicle(RoadSegment currentRoad, Lane currentLane, int currentLanePos, int reputation, int dmg) {
        return new Truck(currentRoad, currentLane, currentLanePos, reputation, dmg);
    }
}