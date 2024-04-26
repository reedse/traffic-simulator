//Sean Reed
//7033251
//COSC3P91 Assignment 4

//Simulation.java

package Traffic_Simulation.Game;
import java.util.List;
import java.util.stream.Collectors;

import Traffic_Simulation.Core.Player;
import Traffic_Simulation.Core.PlayerSurroundInfo;
import Traffic_Simulation.Core.Vehicle;

//Simulation class that will start the simulation, update states, and prompt players for decisions
public class Simulation {

    //the Map the simulation is using
    private Map trafficMap;

    //list of players currently in the simulation
    private List<Player> players;

    private List<Thread> vehicleThreads;

    //if the simulation is running or paused
    private boolean running;

    //number of steps the simulation has went through
    private int currentTimeStep;

    //Constructor
    public Simulation(Map trafficMap, List<Player> players) {
        this.trafficMap = trafficMap;
        this.players = players;
        this.running = false;
        this.currentTimeStep = 0;
    }

    //Methods

    //adds a player into the simulation
    public void addPlayer(Player p){
        players.add(p);
    }

    //start the simulation
    public void start(){
        running = true;

        //create vehicle threads for each vehicle in the simulation
        for (Vehicle v : trafficMap.getVehicles()){
            Thread vThread = new Thread(new VehicleThread(v, this));
            vThread.start();
            this.vehicleThreads.add(vThread);
        }

        while (running){
            updateState();
        }
    }

    //pause the simulation
    public void pause(){
        running = false;
    }

    //updates and increment the simulations time steps, will advance vehicles based on their decision and prompt players for decisions
    private void updateState(){
       
        //update vehicle movement using vehiclesThreads
        for (Thread vThread : vehicleThreads){
            //resume vehicle threads
            vThread.resume();
        }
        
        //if any 2 vehicles are in the same spot, then there is a collision
        for (Vehicle v : trafficMap.getVehicles()){
            //use playerSurroundInfo to get nearby vehicles
            PlayerSurroundInfo p_info = new PlayerSurroundInfo(v.getCurrentLane().getVehicles());
            //compare the vehicles to see if they are in the same spot
            for (Vehicle v2 : p_info.getNearbyVehicles()){
                if (v.getRoadPos() == v2.getRoadPos()){
                    //if they are in the same spot, then there is a collision
                    System.out.println("Collision between vehicle " + v.getId() + " and vehicle " + v2.getId());
                    
                    //calculate damage for both vehicles
                    v.calculateDamage();
                    v2.calculateDamage();
                }
            }
        }

        //Use streams to print out damaged vehicles
        System.out.println("List of damaged vehicles: ");
        List<Vehicle> filteredVehicles = trafficMap.getVehicles().stream()
        .filter(v -> v.getDamage() > 0)
        .collect(Collectors.toList());

        //use method reference to print out damaged vehicles
        filteredVehicles.forEach(System.out::println);

        //use lambda expression to sort damaged vehicles by most damaged
        filteredVehicles.sort((v1, v2) -> v2.getDamage() - v1.getDamage());

        currentTimeStep++;

    }

    public Map getTrafficMap() {
        return this.trafficMap;
    }

}