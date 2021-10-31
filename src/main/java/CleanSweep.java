import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CleanSweep {

    private Floor fp;
    private Tile curTile;
    private Tile startTile;
    private Tile lastTile;
    private float battery;
    private short dirtBag;

    public CleanSweep(Floor f){
        fp = f;
    }

    public void run() throws InterruptedException, IOException {

        Logger log = Logger.getInstance();
        startTile = TileLocator.findStartingTile(fp); //gets the tile to start at
        curTile = startTile;
        lastTile = startTile;
        boolean run = true;
        battery = 250; //Assumes a full charge at start
        dirtBag = 0; //Maxes at 50

        String startMessage = "Starting at Tile: " + curTile.toString();
        System.out.println(startMessage);
        log.write(startMessage);

        while(run){

            Tile North = DemoDecider.Select(fp, getCurrentTile(), lastTile); //calls on a decider to determine next tile to traverse

            //If the decision shows that there are no other valid tiles to go to we end at an emergency shutdown
            if(North == null){
                run = false;
                String deadEnd = "DEAD END INITIATING EMERGENCY SHUTDOWN!";
                System.out.println(deadEnd);
                log.write(deadEnd);
            }
            else {
                //Track Power Consumption
                float powerUse1, powerUse2, powerUseAv;

                //Set powerUse1
                powerUse1 = TileToPower.convert(curTile.getType());

                //Error Handling
                if (powerUse1 == 0) {
                    System.out.println("Invalid Tile Type for Current Tile");
                    log.write("Invalid Tile Type for Current Tile");
                    break;
                }

                //Set powerUse2
                powerUse2 = TileToPower.convert(North.getType());

                //Error Handling
                if (powerUse2 == 0) {
                    System.out.println("Invalid Tile Type for New Tile");
                    log.write("Invalid Tile Type for New Tile");
                    break;
                }

                //Drain Battery
                powerUseAv = (powerUse1 + powerUse2) / 2;
                battery -= powerUseAv;
                if (battery <= 0){
                    battery = 0;
                    run = false; //at 0 we end the clean sweep
                }


                lastTile = curTile; //change the previous tile
                curTile = North;

                String message = "Now on Tile: " + curTile.toString() + " battery: " + battery + "/250.0";
                if(battery <= 40) message += "\n LOW BATTERY!";
                System.out.println(message);
                log.write(message);

                //Clean Dirt
                while (curTile.getDirt() > 0) {
                    curTile.removeDirt();
                    dirtBag++;
                    battery -= powerUse2;

                    message = "Cleaning Tile... battery: " + battery + "/250.0";
                    if(battery <= 40) message += "\n LOW BATTERY!";
                    System.out.println(message);
                    log.write(message);

                    //Dirt Bag is Full
                    if (dirtBag == 50) {
                        //Return to Station

                        run = false; //Cannot Clean if Full

                        message = "Dirt Capacity Reached. Empty Me.";
                        System.out.println(message);
                        log.write(message);
                        break;
                    }
                }

                TimeUnit.SECONDS.sleep(1);
            }

        }

    }

    public int[] getCurrentTile() {
        /* Only returns coordinates of current tile as int array - no dirt information */
        return new int[]{curTile.getTileCoordinate().getX(), curTile.getTileCoordinate().getY()};
    }

}
