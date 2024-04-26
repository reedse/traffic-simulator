//Sean Reed
//7033251
//COSC3p91 Assignment 1

//Bus.java

package Traffic_Simulation.Core;
import Traffic_Simulation.Decision.ChallengeResult;
import Traffic_Simulation.Decision.Decision;
import Traffic_Simulation.Interfaces.*;

public class Bus extends Vehicle implements GameEngineControlled {

    //constructor
    public Bus(RoadSegment currentRoad, Lane currentLane, int currentLanePos, int reputation, int dmg) {
        super(currentRoad, currentLane, currentLanePos, reputation, dmg);
    }

    @Override
    public ChallengeResult challenge(Decision challengeDecision) {
        return null;
    }

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

    //method to compute the next move according to the game engine's rules
    @Override
    public void computeNextMove() {}

}
