
import java.util.Date;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DebuggerFileWriter {
    private static File file;
    private static FileWriter writer;
    private static File dir;
    
    public static void writeDebugLog(String M){
        // File anlegen
        if(writer == null)
        {
            String D = Datum.D() + "-" + Datum.M() + "-" + Datum.Y() + "--" + Datum.h() + "h-" + Datum.m() + "m-" + Datum.s() + "s";
            dir = new File("DebugLog");
            if(!dir.exists())
            {
                dir.mkdir();
            }
            file = new File("DebugLog/DebugLog" + D + ".txt");
            try
            {
                writer = new FileWriter(file, true);
                Runtime.getRuntime().addShutdownHook(new Thread(){public void run(){try{
                    writer.close();}catch(IOException e){e.printStackTrace();}
                }});
            }
            catch (IOException IOE)
            {
                System.out.println(IOE);
                IOE.printStackTrace();
            }
        }
        try {
            writer.write(M);

            writer.write(System.getProperty("line.separator"));
            // Schreibt den Stream in die Datei
            // Sollte immer am Ende ausgeführt werden, sodass der Stream 
            // leer ist und alles in der Datei steht.
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}