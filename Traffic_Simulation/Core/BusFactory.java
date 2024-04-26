package Traffic_Simulation.Core;

public class BusFactory implements VehicleFactory {
    @Override
    public Vehicle createVehicle(RoadSegment currentRoad, Lane currentLane, int currentLanePos, int reputation, int dmg) {
        return new Bus(currentRoad, currentLane, currentLanePos, reputation, dmg);
    }
}
