//Sean Reed
//7033251
//COSC3p91 Assignment 1

//UserControlled.java

package Traffic_Simulation.Interfaces;
import Traffic_Simulation.Decision.Decision;
import Traffic_Simulation.Core.PlayerSurroundInfo;
import Traffic_Simulation.Decision.ChallengeResult;

//Interface used for PlayerControlled vehicles, car and truck implement this interface
public interface UserControlled {
    void recieveUserInput(String command);
    PlayerSurroundInfo lookAround();
    ChallengeResult challenge(Decision challengeDecision);
}
