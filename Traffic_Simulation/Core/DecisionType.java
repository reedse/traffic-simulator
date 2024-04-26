//Sean Reed
//7033251
//COSC3p91 Assignment 1

//DecisionType.java

package Traffic_Simulation.Core;
public enum DecisionType {
    //decision to lane change
    LANE_CHANGE_LEFT,

    LANE_CHANGE_RIGHT,

    //decision to challenge another vehicle
    CHALLENGE,

    //decision to continue moving straight at an intersection.
    KEEP_STRAIGHT,
    
    //decision to turn left at an intersection.
    TURN_LEFT,
    
    //decision to turn right at an intersection.
    TURN_RIGHT,
}
