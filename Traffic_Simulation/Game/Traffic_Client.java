//Sean Reed
//7033251
//COSC3P91 Assignment 4

package Traffic_Simulation.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

//Client that connects to the traffic server using TCP protocol
public class Traffic_Client {
    
    public static void main(String[] args) {
        try {

            //create socket on 12345 port
            Socket socket = new Socket("localhost", 12345); 
            System.out.println("Connected to traffic server");

            //create io streams
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            //read initial message from server
            String init_servermessage = in.readLine();
            
            //print server message to console
            System.out.println(init_servermessage);

            //initial message from server will be asking what vehicle to create
            //get input from client
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String vehicleType = reader.readLine();

            //send vehicletype back to server
            out.println(vehicleType);

            //continue to send and receive messages from server
            String servermessage;
            while ((servermessage = in.readLine()) != null) {
                //print server message to console
                System.out.println(servermessage);
                
                //get client input for next move and send to server
                String next_move = reader.readLine();
                out.println(next_move);
            }

            //close resources
            in.close();
            out.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
