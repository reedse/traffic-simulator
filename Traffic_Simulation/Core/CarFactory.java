package Traffic_Simulation.Core;

public class CarFactory implements VehicleFactory {
    @Override
    public Vehicle createVehicle(RoadSegment currentRoad, Lane currentLane, int currentLanePos, int reputation, int dmg) {
        return new Car(currentRoad, currentLane, currentLanePos, reputation, dmg);
    }
}
