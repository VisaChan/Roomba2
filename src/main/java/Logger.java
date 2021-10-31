import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    private static Logger logger = null;

    SimpleDateFormat formatter= new SimpleDateFormat("'Log' yyyy-MM-dd 'H'HH'M'mm'S'ss ");
    Date date = new Date(System.currentTimeMillis());


    private FileWriter fw = new FileWriter(formatter.format(date).toString());
    public BufferedWriter bw = new BufferedWriter(fw);

    public Logger() throws IOException {
    }


    public static Logger getInstance() throws IOException {
        if(logger == null){
            logger = new Logger();
        }
        return logger;
    }

    public void write(String s) throws IOException{
        bw.write(s);
        bw.newLine();
    }
    public void createLog() throws IOException {
        bw.close();
    }

}
