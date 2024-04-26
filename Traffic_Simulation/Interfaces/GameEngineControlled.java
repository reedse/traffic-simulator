//Sean Reed
//7033251
//COSC3p91 Assignment 1

//GameEngineControlled.java

package Traffic_Simulation.Interfaces;
import Traffic_Simulation.Decision.Decision;
import Traffic_Simulation.Decision.ChallengeResult;

//Interface used for GameEngine vehicles, currently just the bus implements this interface
public interface GameEngineControlled {
    public void computeNextMove();
    public ChallengeResult challenge(Decision challengeDecision);
}
