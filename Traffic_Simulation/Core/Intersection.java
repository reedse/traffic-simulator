//Sean Reed
//7033251
//COSC3p91 Assignment 1

//Intersection.java

package Traffic_Simulation.Core;
import java.util.List;

public class Intersection {

    //identifer for this node
    private String id;

    //list of incoming edges
    private List<RoadSegment> incomingSegments;

    //list of outgoing edges
    private List<RoadSegment> outgoingSegments;

    //Constructor
    public Intersection(String id2) {
        this.id = id2;
    }

    //Methods

    //add road segment to list of incomingSegments
    public void addIncomingSegment(RoadSegment r){
        incomingSegments.add(r);
    }

    //add road segment to list of outgoingSegments
    public void addOutgoingSegment(RoadSegment r){
        outgoingSegments.add(r);
    }

    //getter for incomingSegments
    public List<RoadSegment> getIncomingSegments(){
        return incomingSegments;}

    //getter for outgoingSegments
    public List<RoadSegment> getOutgoingSegments(){
        return outgoingSegments;}

    public String getId() {
        return this.id;
    }

}
