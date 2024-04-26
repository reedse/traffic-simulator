//Sean Reed
//7033251
//COSC3p91 Assignment 4

//Map.java

package Traffic_Simulation.Game;
import java.util.*;
import java.io.*;


import Traffic_Simulation.Core.Intersection;
import Traffic_Simulation.Core.Lane;
import Traffic_Simulation.Core.LaneDirection;
import Traffic_Simulation.Core.RoadSegment;
import Traffic_Simulation.Core.Vehicle;
import Traffic_Simulation.Exceptions.VehicleNotFoundException;

//Traffic Map, graph data structure
public class Map {

    //contains a list of nodes
    private List<Intersection> intersections;

    //list of edges
    private List<RoadSegment> roads;

    //list of vehicles currently in the map
    private List<Vehicle> vehicles;

    //constructor
    public Map() {
        intersections = new ArrayList<>();
        roads = new ArrayList<>();
        vehicles = new ArrayList<>();
    }

    //Methods

    //load the map from xml parsed file
    public void loadGraph(String xml_path) {
        //create instance of traffic map parser
        new MapParser().loadGraph(this, xml_path);
    }

    //getter for intersections
    public Intersection findIntersectionById(String id) {
        for (Intersection intersection : intersections) {
            if (intersection.getId() == id) {
                return intersection;
            }
        }
        return null;
    }

    //getter for roads
    public RoadSegment findRoadSegmentById(int id) {
        for (RoadSegment roadSegment : roads) {
            if (roadSegment.getId() == id) {
                return roadSegment;
            }
        }
        return null;
    }

    //adds a vehicle randomly onto a road segment, in a random lane
    public void addVehicle(Vehicle v) {
        Random random = new Random();
        int roadIndex = random.nextInt(roads.size());
        RoadSegment roadSegment = roads.get(roadIndex);
        int laneIndex = random.nextInt(roadSegment.getLanes().size());
        Lane lane = roadSegment.getLanes().get(laneIndex);
        lane.addVehicle(v);
        vehicles.add(v);
    }

    //removes a vehicle from the map (based on id)
    public void removeVehicle(String id) throws VehicleNotFoundException {
        Vehicle v = getVehicleById(id);

        //if there was no vehicle found, throw an exception
        if (v == null) {
            throw new VehicleNotFoundException("Vehicle with ID " + id + " not found.");
        } else {
            //find the lane the vehicle is in
            for (RoadSegment roadSegment : roads) {
                for (Lane lane : roadSegment.getLanes()) {
                    if (lane.getVehicles().contains(v)) {
                        lane.removeVehicle(v);
                        break;
                    }
                }
            }

            //find road segment the vehicle is on
            for (RoadSegment roadSegment : roads) {
                if (roadSegment.getVehicles().contains(v)) {
                    roadSegment.removeVehicle(v);
                    break;
                }
            }
        }

    }

    public Vehicle getVehicleById(String id) throws VehicleNotFoundException {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId() == id) {
                return vehicle;
            }
        }
        throw new VehicleNotFoundException("Vehicle with ID " + id + " not found.");
    }   

    public List<Vehicle> getVehicles() {
        return this.vehicles;
    }

    public List<RoadSegment> getRoads() {
        return this.roads;
    }

    //makes all vehicles in the map will move based on their decisions
    public void moveVehicles(){}

    public void addIntersection(Intersection intersection) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addIntersection'");
    }

    public void addRoadSegment(RoadSegment roadSegment) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addRoadSegment'");
    }

    public void addLane(Lane lane) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addLane'");
    }
}
