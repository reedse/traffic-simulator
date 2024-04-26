//Sean Reed
//7033251
//COSC3p91 Assignment 1

//Car.java

package Traffic_Simulation.Core;
import Traffic_Simulation.Decision.ChallengeResult;
import Traffic_Simulation.Decision.Decision;
import Traffic_Simulation.Interfaces.*;

//represents car in simulation, extends vehicle class implementing usercontrol interface
//this car receieves input from user and makes decisions from it
public class Car extends Vehicle implements UserControlled {

    //constructor
    public Car(RoadSegment currentRoad, Lane currentLane, int currentLanePos, int reputation, int dmg) {
        super(currentRoad, currentLane, currentLanePos, reputation, dmg);
    }

    @Override
    public void recieveUserInput(String command) {}

    //method where player can recieve info from surrounding lanes
    @Override
    public PlayerSurroundInfo lookAround() {
        return null;
    }

    //initates a challenge based on decision
    @Override
    public ChallengeResult challenge(Decision challengeDecision) {
        return null;
    }

    //defines how the car makes decisions during the simulation, to be implemented with logic for making decisions
    @Override
    public void makeDecision(){
        //based on decision type, car will move, change lanes
        if(this.nextDecision.getType() == DecisionType.LANE_CHANGE_LEFT){
            move_changeLane(this.currentLane.getLaneNumber()-1);
        }
        else if(this.nextDecision.getType() == DecisionType.LANE_CHANGE_RIGHT){
            move_changeLane(this.currentLane.getLaneNumber()+1);
        }
        else if(this.nextDecision.getType() == DecisionType.KEEP_STRAIGHT){
            move_straight();
        }
    }

}
