public class SensorCommunication {

    //static methods to get the other tiles in its directions
    public static Tile getNorth(Floor fp, int[] coordinates){
        return findTile(fp, coordinates[0], coordinates[1] + 1);
    }

    public static Tile getSouth(Floor fp, int[] coordinates){
        return findTile(fp, coordinates[0], coordinates[1] - 1);
    }

    public static Tile getWest(Floor fp, int[] coordinates){
        return findTile(fp, coordinates[0] + 1, coordinates[1]);
    }

    public static Tile getEast(Floor fp, int[] coordinates){
        return findTile(fp, coordinates[0] - 1, coordinates[1]);
    }

    //we get the tile that we are trying to look for in one of the directions
    private static Tile findTile(Floor fp, int x, int y){Tile retTile = null;
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
