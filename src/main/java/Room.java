import java.util.LinkedList;
import java.util.List;

public class Room {
    //Room name used for location of CleanSweep later
    private String roomName;

    //LinkedList for tiles of a room
    private List<Tile> tileList =  new LinkedList<Tile>();

    public Room(String rN, List<Tile> tl){
        roomName = rN;
        tileList = tl;
    }

    public String getRoomName() {
        return roomName;
    }

    public List<Tile> getTileList() {
        return tileList;
    }

    //this sends a string consisting of the room name and all its tiles
    @Override
    public String toString(){
        StringBuilder rb = new StringBuilder();
        for(Tile t: tileList){
            //System.out.println(t);
            rb.append(t.toString()+"\n");
        }
        return roomName +"\n"+ rb.toString();
    }

}
