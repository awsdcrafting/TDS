
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.JLabel;
import java.awt.*;
import java.time.LocalTime;
import java.awt.event.*;
public class GUI
{
    public static final int SIZE_X = 600;
    public static final int SIZE_Y = 500;
    
    private Fenster fenster;
    private Bild hintergrund;
    private JLabel punkte;
    private JLabel geld;
    private JLabel leben;
    private JLabel huelleText;
    private Bild huelle;
    private Bild laser;
    private JLabel start;
    private JLabel startPause;
    private JLabel startShop;
    private Shop shop;
    
    //pause
    private JLabel pauseText;
    private JLabel shopText;

    
    
    //DebugLog
    private static String className = "GUI";
    
    public GUI()
    { 
        fenster = new Fenster("SpaceTDS" + Main.version, SIZE_X, SIZE_Y);
        
        hintergrund = JElementHelper.baueBild("Bilder/moonsurface.jpg", 0, 0, SIZE_X, SIZE_Y);
        punkte = JElementHelper.baueLabel("Punkte: 0", SIZE_Y, 20, 100, 20); 
        punkte.setFont(new Font("SansSerif",Font.BOLD,14));
        punkte.setForeground(new Color(0,255,0));
        hintergrund.add(punkte);
        geld = JElementHelper.baueLabel("Geld: 0", SIZE_Y, 35, 100, 20); 
        geld.setFont(new Font("SansSerif",Font.BOLD,14));
        geld.setForeground(new Color(0,255,0));
        hintergrund.add(geld);
        leben = JElementHelper.baueLabel("Leben: 3", SIZE_Y, 5, 100, 20); 
        leben.setFont(new Font("SansSerif",Font.BOLD,14));
        leben.setForeground(new Color(0,255,0));
        hintergrund.add(leben);
        huelle = JElementHelper.baueBild("Bilder/cyan.png", 262,425+40, 75, 10); 
        hintergrund.add(huelle);
        // huelleText = JElementHelper.baueLabel("Huelle: ", SIZE_Y, 59, 100, 20); 
        // huelleText.setFont(new Font("SansSerif",Font.BOLD,14));
        // huelleText.setForeground(new Color(0,255,0));
        // hintergrund.add(huelleText);
        laser = JElementHelper.baueBild("Bilder/red.png", 262,435+40,75,10);
        //hintergrund.add(laser);
        
        
        
        shop = new Shop();
        shop.setVisible(false);
        
        start = JElementHelper.baueLabel("Press Enter to start!", 200, 200,200,50); 
        start.setFont(new Font("SansSerif",Font.BOLD,20));
        start.setForeground(new Color(255,0,0));
        hintergrund.add(start);
        startPause = JElementHelper.baueLabel("Press P in the game to Pause!", 150, 250,300,50); 
        startPause.setFont(new Font("SansSerif",Font.BOLD,20));
        startPause.setForeground(new Color(255,0,0));
        hintergrund.add(startPause);
        startShop = JElementHelper.baueLabel("Press k in the pause menu to enter the shop!", 75, 300,450,50); 
        startShop.setFont(new Font("SansSerif",Font.BOLD,20));
        startShop.setForeground(new Color(255,0,0));
        hintergrund.add(startShop);
        
        
        fenster.addToContentPane(shop);
        fenster.addToContentPane(hintergrund);
        fenster.setResizable(false);
        fenster.setDefaultCloseOperation(fenster.EXIT_ON_CLOSE);
        fenster.initialize();
    }
    
    public Bild gibHintergrund()
    {
        Debugger.log("gibHintergrund ausgef�hrt!",className,Debugger.gibDebugMain());
        return hintergrund;
    }
    
    public void wartenAufStart()
    {
        while(Tastatur.isPressed(KeyEvent.VK_ENTER)==false)
        {
            try
            {
                Thread.sleep(1);    
            }
            catch(InterruptedException IE)
            {
                System.out.println("error: " + IE);
            }
        }
        hintergrund.remove(start);
        hintergrund.remove(startPause);
        hintergrund.remove(startShop);
        hintergrund.repaint();
    }
    
    public void pause()
    {
        pauseText = JElementHelper.baueLabel("Game paused press P to resume!", 150,50,400,50); 
        pauseText.setFont(new Font("SansSerif",Font.BOLD,20));
        pauseText.setForeground(new Color(255,0,0));
        shopText = JElementHelper.baueLabel("press K to enter the shop!", 150,100,400,50); 
        shopText.setFont(new Font("SansSerif",Font.BOLD,20));
        shopText.setForeground(new Color(0,255,0));
        hintergrund.add(pauseText);
        hintergrund.add(shopText);
        hintergrund.repaint();
        try
        {
            Thread.sleep(200);    
        }
        catch(InterruptedException IE)
        {
            System.out.println("error: " + IE);
        }
        while(Tastatur.isPressed(KeyEvent.VK_P)==false)
        {
            if(Tastatur.isPressed(KeyEvent.VK_K))
            {
                shop();
                Debugger.log("Shop ausgef�hrt!",className,Debugger.gibDebugGUI());
            }
            try
            {
                Thread.sleep(1);    
            }
            catch(InterruptedException IE)
            {
                System.out.println("error: " + IE);
            }
        }
        hintergrund.remove(pauseText);
        hintergrund.remove(shopText);
        hintergrund.repaint();
    }
    
    //der shop
    public void shop()
    {
        
        shop.add(geld);
        hintergrund.remove(geld);
        shop.setVisible(true);
        hintergrund.setVisible(false);

        shop.repaint();
        try
        {
            Thread.sleep(100);    
        }
        catch(InterruptedException IE)
        {
            System.out.println("error: " + IE);
        }
        
        while(Tastatur.isPressed(KeyEvent.VK_ESCAPE)==false)
        {
            try
            {
                Thread.sleep(1);    
                shop.repaint();
            }
            catch(InterruptedException IE)
            {
                System.out.println("error: " + IE);
            }
        }
        hintergrund.add(geld);
        shop.remove(geld);
        hintergrund.setVisible(true);
        shop.setVisible(false);
    }
    
    public Shop gibShop()
    {
        return shop;
    }
    
    public void setzeGeld(int i)
    {
        geld.setText("Geld: " + i);
        Debugger.log("setzePunkte ausgef�hrt!",className,Debugger.gibDebugMain());
    }
    
    public void setzeLeben(int i)
    {
        leben.setText("Leben: " + i);
        Debugger.log("setzeLeben ausgef�hrt!",className,Debugger.gibDebugMain());
    }
    
    public void setzeHuelle(double i)
    {
        int huellenX = (int) (i*75);
        huelle.setBounds((int) (Main.gibPhysicsEngine().gibUfoSpielerX()-(huellenX-75)/2),(int) (Main.gibPhysicsEngine().gibUfoSpielerY()+40), (huellenX), 10); 
        Debugger.log("setzeHuelle ausgef�hrt!",className,Debugger.gibDebugMain());
    }
    
    public void setzeLaser(double i)
    {
    	int laserX = (int) (i*75);
        laser.setBounds((int) (Main.gibPhysicsEngine().gibUfoSpielerX()-(laserX-75)/2),(int) (Main.gibPhysicsEngine().gibUfoSpielerY()+40+10), (laserX), 10); 
        Debugger.log("setzeHuelle ausgef�hrt!",className,Debugger.gibDebugMain());
    }
    
    public void setzePunkte(int i)
    {
        punkte.setText("Punkte: " + i);
        if(i<0)
        {
            punkte.setForeground(new Color(255,0,0));
        }
        else
        {
            punkte.setForeground(new Color(0,255,0));
        }
        Debugger.log("setzePunkte ausgef�hrt!",className,Debugger.gibDebugMain());
    }
}