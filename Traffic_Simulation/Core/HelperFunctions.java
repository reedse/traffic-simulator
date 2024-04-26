package Traffic_Simulation.Core;

import java.util.List;

public class HelperFunctions {
    
    //generic method to print items in a list
    public static <T> void printList(List<T> list) {
        for (T item : list) {
            System.out.println(item);
        }
    }

}
