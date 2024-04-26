//Sean Reed
//7033251
//COSC3p91 Assignment 1

//Decision.java

package Traffic_Simulation.Decision;

import Traffic_Simulation.Core.DecisionType;

//Decision class that holds a string of what decision, and the enum decisiontype 
public class Decision {
    private DecisionType type;
    private String decision;

    //constructor
    public Decision(DecisionType type, String decision) {
        this.type = type;
        this.decision = decision;
    }

    //getter for type
    public DecisionType getType() {
        return type;
    }
}
