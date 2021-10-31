//Class revolving around finding tiles
public class TileLocator {

    //determines the tile the clean sweep should start on
    public static Tile findStartingTile(Floor f){
        Tile start = null;
        Room startR = null;

        System.out.println(f.getRoomList().size());
        for(Room r : f.getRoomList()){
            if(r.getRoomName().equals("Starting room")){
                startR = r;
            }
        }

        for(Tile t : startR.getTileList()){
            if(t.getType().equals("station")){
                start = t;
            }
        }
        return start;
    }

    //I was thinking of changing this into its class
    //This method mostly going to be used to detect the tile on the north, south, west and east
    //I was thinking of making this into a sensor class
    public static Tile findTile(Floor fp, int x, int y){
        Tile retTile = null;
        for(Room r: fp.getRoomList()){
            for(Tile t: r.getTileList()){
                if((t.getTileCoordinate().getX() == x) && (t.getTileCoordinate().getY() == y)){
                    retTile = t;
                    break;
                }
            }
        }
        return retTile;
    }

}
