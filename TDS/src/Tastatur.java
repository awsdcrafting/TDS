
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.time.LocalTime;
public class Tastatur implements KeyEventDispatcher
{
    private static final boolean debug=false;
    private static String className="Tastatur";

    private static Tastatur singleton;
    
    private ArrayList<Integer> keys;
    private ArrayList<TastenListener> listeners;
    private Tastatur()
    {
        listeners = new ArrayList<TastenListener>();
        keys = new ArrayList<Integer>();
    }

    public static void subscribe(TastenListener t)
    {
        if(singleton == null)
        {
            log("erzeuge den singleton. alle weiteren Tastendrücke KÖNNEN ABGEFANGEN werden!");
            singleton = new Tastatur();
            KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(singleton);
        }
        singleton.add(t);
    }

    public static void unsubscribe(TastenListener t)
    {
        if(singleton == null)
        {
            log("erzeuge den singleton. alle weiteren Tastendrücke KÖNNEN ABGEFANGEN werden!");
            singleton = new Tastatur();
            KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(singleton);
        }
        singleton.remove(t);
    }

    public void add(TastenListener t)
    {
        listeners.add(t);
    }

    public void remove(TastenListener t)
    {
        listeners.remove(t);
    }

    public boolean dispatchKeyEvent(KeyEvent e)
    {
        if(e.getID() == KeyEvent.KEY_PRESSED)
        {
            int taste = e.getExtendedKeyCode();
            if(!keys.contains(taste))
            {
                keys.add(taste);
            }
            log("eine taste wurde gedrückt. entsrechender extendedKeyCode ist: "+taste);
            for(TastenListener l:listeners)
            {
                boolean b = l.tastenDruck(taste);
                if(b)
                {
                    log("ein listener hat den tastendruck abgefangen! nur dieser listener und vorherige wurden benachrichtigt!");
                    return true;
                }
            }
            return false;
        }
        else if(e.getID() == KeyEvent.KEY_RELEASED)
        {
            int taste = e.getExtendedKeyCode();
            Object o = taste;
            keys.remove(o);
            log("eine taste wurde losgelassen.");
            return false;
        }
        else
        {
            return false;
        }
    }
    
    public boolean isPressedS(int taste)
    {
        return keys.contains(taste);
    }
    
    public static boolean isPressed(int taste)
    {
        if(singleton == null)
        {
            log("erzeuge den singleton. alle weiteren Tastendrücke KÖNNEN ABGEFANGEN werden!");
            singleton = new Tastatur();
            KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(singleton);
        }
        return singleton.isPressedS(taste);
    }
    
    private static void log(String message)
    {
        if(debug)
        {
            System.out.println("["+LocalTime.now()+"]["+className+"]: "+message);
        }
    }
}

interface TastenListener
{
    public boolean tastenDruck(int taste);
}
