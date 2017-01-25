
import java.time.LocalTime;

public class Debugger{
    private static String className = "Debugger";
    private static boolean debug = false;
    private static String debugStart = (String)(""+LocalTime.now());
    
    private static boolean debugMain = false;
    private static boolean debugGUI = false;
    private static boolean debugPhysicsEngine = false;
    
    
    
    public static void log(String message,String className,boolean debug)
    {
        if(debug)
        {
            System.out.println("["+LocalTime.now()+"]["+className+"]: "+message);
            DebuggerFileWriter.writeDebugLog("["+LocalTime.now()+"]["+className+"]: "+message);
        }
    }
    
    public static void log(String message,String className)
    {
        if(debug)
        {
            System.out.println("["+LocalTime.now()+"]["+className+"]: "+message);
            DebuggerFileWriter.writeDebugLog("["+LocalTime.now()+"]["+className+"]: "+message);
        }
    }

    
    public static void toggleDebugMode(){
        debugMain = !debugMain;
        debugGUI = !debugGUI;
        debugPhysicsEngine = !debugPhysicsEngine;
        debug = !debug;
        log("Debug Mode toggled!",className);
    }
    
    public static boolean gibDebugMain()
    {
        return debugMain;
    }
    public static boolean gibDebugGUI()
    {
        return debugGUI;
    }
    public static boolean gibDebugPhysicsEngine()
    {
        return debugPhysicsEngine;
    }
}