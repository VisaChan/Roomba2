import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

//Class that is just an example of traversing
//Allowed to delete/change if needed
public class DemoDecider {

    static HashMap <String, String> valid = new HashMap<>();


    //create an array communicating with sensor
    public static Tile Select(Floor fp, int[] current, Tile last){

        Tile[] d = new Tile[4];
        d[0] = SensorCommunication.getNorth(fp, current);
        d[1] = SensorCommunication.getSouth(fp, current);
        d[2] = SensorCommunication.getWest(fp, current);
        d[3] = SensorCommunication.getEast(fp, current);

        //from the data from the sensor we call a function to decide the best action
        //if the decider returns null it means there are no directions that the clean sweep can go
        //meaning we will call a shutdown in the CleanSweep class.
        return decider(d, last);

    }

    //currently, best action is to try to go to the location that is the most dirty
    //without trying to go to the previous tile unless needed.
    private static Tile decider(Tile[] d, Tile last){

        Tile ret = null;

        //see if any direction's tile type is a wall
        List<Tile> choices = new LinkedList<>();
        for(Tile t: d) {
            if (t.getType().equals("wall") == false || t.getType().equals("stairs")) {
                choices.add(t);
            }
        }

        //we try to find the best remaining tile
        int remaining = choices.size();
        if(remaining > 0){
            if(remaining == 1 && choices.get(0).equals(last)){
                ret = last;
            }
            else {
                int maxDirt = 0;
                ret = choices.get(0);
                for (int i = 0; i < remaining; i++) {
                    Tile cur = choices.get(i);
                    if ((cur.getDirt() > maxDirt) && (cur.equals(last) == false)) {
                        ret = cur;
                        maxDirt = cur.getDirt();
                    }
                }
            }
        }
        return ret;

    }

}
