//Sean Reed
//7033251
//COSC3P91 Assignment 4

package Traffic_Simulation.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import Traffic_Simulation.Core.BusFactory;
import Traffic_Simulation.Core.CarFactory;
import Traffic_Simulation.Core.DecisionType;
import Traffic_Simulation.Core.TruckFactory;
import Traffic_Simulation.Core.Vehicle;
import Traffic_Simulation.Core.VehicleFactory;
import Traffic_Simulation.Decision.Decision;

//Server that listens for client connections using TCP protocol
//Handles creating traffic simulator, running simulation and sending data back to client
public class Traffic_Server {
    public static void main(String[] args) {
        try {

            //create server socket
            ServerSocket serverSocket = new ServerSocket(12345); 
            System.out.println("Server started, waiting for client...");

            //wait for client connection
            Socket clientSocket = serverSocket.accept(); 
            System.out.println("Client connected: " + clientSocket.getInetAddress());

            //create io streams
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            //initialize simulation

            //create a map instance
            Map map = new Map();
            try {
                //load the map from xml parser method
                map.loadGraph("traffic_map.xml");

                //create a simulation instance if map is loaded
                Simulation sim = new Simulation(map, null);

                //create vehicle factory instances
                VehicleFactory truckFactory = new TruckFactory();
                VehicleFactory carFactory = new CarFactory();
                VehicleFactory busFactory = new BusFactory();

                //ask client what vehicle they want to create
                out.println("What vehicle do you want to create? (car, truck, bus)");
                String vehicleType = in.readLine();

                //create player vehicle based on input from client
                Vehicle pVehicle = null;
                if (vehicleType.equals("car")) {
                    pVehicle = carFactory.createVehicle(null, null, 0, 0, 0);
                } else if (vehicleType.equals("truck")) {
                    pVehicle = truckFactory.createVehicle(null, null, 0, 0, 0);
                } else if (vehicleType.equals("bus")) {
                    pVehicle = busFactory.createVehicle(null, null, 0, 0, 0);
                }

                //set the player vehicle to a random road and lane on the map
                pVehicle.setCurrentRoad(map.getRoads().get(0));
                pVehicle.setCurrentLane(pVehicle.getCurrentRoad().getLanes().get(0), 0);

                sim.start();

                //create simulation view class that will display the simulation state to client
                SimulationView sim_view = new SimulationView();

                //continue to ask for client input
                String client_message;
                while ((client_message = in.readLine()) != null) {
                    //send the client the current state of the simulation using sim view class
                    String sim_info = sim_view.displaySimulationInfo(sim);
                    out.println("Current state of simulation: " + sim_info);
                    
                    //get client input for next move
                    out.println("What is your next move? (0: pause sim, 2: go straight, 3: lane change left, 4: lane change right)");

                    Decision goStraight = new Decision(DecisionType.KEEP_STRAIGHT, "straight");
                    Decision laneChangeL = new Decision(DecisionType.LANE_CHANGE_LEFT, "lchange_left");
                    Decision laneChangeR = new Decision(DecisionType.LANE_CHANGE_RIGHT, "lchange_right");
                    switch (client_message) {
                        case "0":
                            sim.pause(); //pause simulation
                            break;
                        case "2":
                            pVehicle.setDecision(goStraight); //update player vehicle's next decision
                            break;
                        case "3":
                            pVehicle.setDecision(laneChangeL); 
                            break;
                        case "4":
                            pVehicle.setDecision(laneChangeR); 
                            break;
                        default: //handle invalid input
                            out.println("Invalid input, please try again"); //send error message to client
                            break;
                    }

                }

            } catch (Exception e) { //exception if map isn't loaded
                System.out.println("Error loading map");
            }

            // Close resources
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
