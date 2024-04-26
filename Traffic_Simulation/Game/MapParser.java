package Traffic_Simulation.Game;

//Sean Reed
//7033251
//COSC3P91 Assignment 3

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Traffic_Simulation.Core.Intersection;
import Traffic_Simulation.Core.Lane;
import Traffic_Simulation.Core.LaneDirection;
import Traffic_Simulation.Core.RoadSegment;

import java.io.File;
import java.io.IOException;

//MapParser class that handles parsing the xml file using DOM
public class MapParser {

    public void loadGraph(Map map, String xmlFilePath) {
        //use exception handling for xml file
        try {
            File inputFile = new File(xmlFilePath);

            //use docbuilderfactory for schema validation
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

            //use dom parser to parse the xml file
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            org.w3c.dom.Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            
            //intersections represented as: Intersection, id
            //road segments represented as: RoadSegment, id, startIntersectionId, endIntersectionId
            //lanes represented as: Lane, id, roadSegmentId, maxVehicles, direction

            //parse intersections
            NodeList intersectionList = doc.getElementsByTagName("Intersection");
            for (int i = 0; i < intersectionList.getLength(); i++) {
                Node nNode = intersectionList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String id = eElement.getAttribute("id");
                    //for each node, if it is an intersection, add it to the map
                    map.addIntersection(new Intersection(id));
                }
            }

            //parse road segments
            NodeList roadSegmentList = doc.getElementsByTagName("RoadSegment");
            for (int i = 0; i < roadSegmentList.getLength(); i++) {
                Node nNode = roadSegmentList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    //create new roadsegment with parsed ids
                    String id = eElement.getAttribute("id");
                    String startId = eElement.getAttribute("startIntersectionId");
                    String endId = eElement.getAttribute("endIntersectionId");
                    RoadSegment r = new RoadSegment(Integer.parseInt(id), map.findIntersectionById(startId), map.findIntersectionById(endId), null, null);
                    map.addRoadSegment(r);

                }
            }

            //parse lanes
            NodeList laneList = doc.getElementsByTagName("Lane");
            for (int i = 0; i < laneList.getLength(); i++) {
                Node nNode = laneList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String id = eElement.getAttribute("id");
                    String roadSegmentId = eElement.getAttribute("roadSegmentId");
                    int maxVehicles = Integer.parseInt(eElement.getAttribute("maxVehicles"));
                    LaneDirection direction = LaneDirection.valueOf(eElement.getAttribute("direction"));
                    map.addLane(new Lane(Integer.parseInt(id), roadSegmentId, maxVehicles, direction));
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
