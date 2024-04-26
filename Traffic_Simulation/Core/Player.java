//Sean Reed
//7033251
//COSC3p91 Assignment 1

//Player.java

package Traffic_Simulation.Core;
import java.util.List;

import Traffic_Simulation.Decision.Decision;
import Traffic_Simulation.Decision.ChallengeResult;
import java.util.List;
import Traffic_Simulation.Decision.Decision;
import Traffic_Simulation.Decision.ChallengeResult;

public class Player {
    private String name; //player name as string
    private Vehicle v; //vehicle associated with this player
    private int reputation; //reputation score, is updated based on events in game
    private Decision nextDecision; //holds decision for player that is made in next game state

    //Constructor
    public Player(String name, Vehicle v, int reputation) {
        this.name = name;
        this.v = v;
        this.reputation = reputation;
    }

    //Methods
    public void updateReputation(int x){
        reputation += x; //updates players reputation based on game event
    }

    //calculates players decision and updates nextDecision
    public void makeDecision(Decision d){
        nextDecision = d; //sets the players next decision
        //update player's vehicle's decision
        v.setDecision(d);
    } 
    
    public Decision getDecision(){
        return nextDecision; //returns the players next decision
    }

    //when player executes a challenge, takes in a decision they're trying to do
    public ChallengeResult executeChallenge(Decision challengeDecision){ 
        return null;
    }
    
    public Vehicle getVehicle() { //returns players current vehicle
        return v;
    }

    public void setVehicle(Vehicle v){
        this.v = v; //sets a players vehicle
    } 
    
    public int getReputation() { //returns players reputation
        return reputation;
    }
}
