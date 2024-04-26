package Traffic_Simulation.Core;

import java.util.ArrayList;
import java.util.List;

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
