import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {

        //Floor f = new Floor("Demo10-10/trap.json");

        //Floor f = new Floor("Demo10-10/longjohn.json");
        //Floor f = new Floor("Demo10-10/floor1.json");
        Floor f = new Floor("src/main/java/stairDemo.json");
        CleanSweep c = new CleanSweep(f);
        Logger log = Logger.getInstance();
        //activate the clean sweeps
        c.run();

        System.out.println("Ending tile of CleanSweep run: " + Arrays.toString(c.getCurrentTile()));
        log.write("Ending tile of CleanSweep run: " + Arrays.toString(c.getCurrentTile()));


        log.createLog();
    }

}
