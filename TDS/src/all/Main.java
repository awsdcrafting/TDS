package all;
import javax.swing.SwingUtilities;
import java.io.*;
import java.util.ArrayList;
public class Main
{
    private static GUI gui;
    private static PhysicsEngine physicsEngine;
    public static boolean Spielaktiv;
    private static int punkte;
    private static int geld;
    private static final Main MAIN = new Main();
    public static ArrayList<String> sal;
    public static String version = "0.1 - alpha";
    //DebugLog
    private static String className = "Main";
    

    public static void main(String[] args)
    {
    	
        SwingUtilities.invokeLater(new Runnable(){public void run(){
                    Spielaktiv = true;
                    gui = new GUI();
                    sal = new ArrayList<String>();
                    FileChooser.main();
                    physicsEngine = new PhysicsEngine(gui);
                    physicsEngine.start();

                }});

    }
    public static void veraendereGeld(int p)
    {
        geld += p;
        gui.setzeGeld(geld);
        Debugger.log("veraendereGeld ausgefuehrt!",className,Debugger.gibDebugMain());
        Debugger.log("Neuer Geldstand: " + geld + "!",className,Debugger.gibDebugMain());
    }

    public static int gibGeld()
    {
        return geld;
    }

    public static void veraenderePunkte(int p)
    {
        punkte += p;
        gui.setzePunkte(punkte);
        Debugger.log("veraenderePunkte ausgef�hrt!",className,Debugger.gibDebugMain());
        Debugger.log("Neuer Punktestand: " + punkte + "!",className,Debugger.gibDebugMain());
    }

    public static int gibPunkte()
    {
        return punkte; 
    }

    public static InputStream gibResource(String resource)
    {
        return MAIN.getClass().getResourceAsStream(resource);
    }

    public static PhysicsEngine gibPhysicsEngine()
    {
        return physicsEngine;
    }
    
    public static void fileChooserVisible()
    {
        FileChooser.setvisible(true);
    }
    
    public static ArrayList<String> salSpeichern()
    {
        sal.add(""+punkte);
        sal.add(""+geld);
        sal.add(""+physicsEngine.gibUfoSpieler().leben);
        sal.add(""+physicsEngine.gibUfoSpieler().huelle);
        sal.add(""+gui.gibShop().gibUpgradeAnzahl("u1"));
        sal.add(""+gui.gibShop().gibUpgradeAnzahl("u2"));
        sal.add(""+gui.gibShop().gibUpgradeAnzahl("u3"));
        return sal;
    }
    
    public static void salLaden(ArrayList<String> al)
    {
        sal = al;
        punkte = Integer.parseInt(sal.get(0).trim());
        gui.setzePunkte(punkte);
        geld = Integer.parseInt(sal.get(1).trim());
        gui.setzeGeld(geld);
        physicsEngine.gibUfoSpieler().leben = Integer.parseInt(sal.get(2).trim());
        physicsEngine.gibUfoSpieler().huelle = Integer.parseInt(sal.get(3).trim());
        gui.gibShop().setzeUpgradeAnzahl("u1", Integer.parseInt(sal.get(4).trim()));
        gui.gibShop().setzeUpgradeAnzahl("u2", Integer.parseInt(sal.get(5).trim()));
        gui.gibShop().setzeUpgradeAnzahl("u3", Integer.parseInt(sal.get(6).trim()));
    }
    
}