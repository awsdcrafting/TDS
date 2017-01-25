
import java.awt.*;
public class SpielObjekt
{
    public Bild bild;
    protected double x;
    protected double y;
    protected double deltaX; //in pixel/sekunde
    protected double deltaY; //in pixel/sekunde
    protected long letzteZeit; //wann das Spielobjekt zuletzt von der Engine bewegt wurde
    protected int leben;
    protected int geldReward;
    protected int huelle;
    protected int punkte;
    protected int drop =-1;
    protected Container container;
    public boolean entfernt;
    public SpielObjekt(Bild b, Container c)
    {
        bild = b;
        x = (double) bild.getX();
        y = (double) bild.getY();
        container = c;
        container.add(bild);
    }
    
    public void setzeX(double x)
    {
        this.x = x;
        bild.setBounds((int)x, bild.getY(), bild.getWidth(), bild.getHeight());
    }
    
    public double gibX()
    {
        return x;
    }
    
    public int gibIntX()
    {
        return (int)x;
    }
    
    public void setzeY(double y)
    {
        this.y = y;
        bild.setBounds(bild.getX(), (int)y, bild.getWidth(), bild.getHeight());
    }
    
    public double gibY()
    {
        return y;
    }
    
    public int gibIntY()
    {
        return (int)y;
    }
    
    public int getWidth()
    {
        return bild.getWidth();
    }
    
    public int getHeight()
    {
        return bild.getHeight();
    }
    
    public void setDeltaX(double dX)
    {
        deltaX = dX;
    }
    
    public void setDeltaY(double dY)
    {
        deltaY = dY;
    }
    
    public double getDeltaX()
    {
        return deltaX;
    }
    
    public double getDeltaY()
    {
        return deltaY;
    }
    
    public long getLastTime()
    {
        return letzteZeit;
    }
    
    public void setLastTime(long millis)
    {
        letzteZeit = millis;
    }
    
    public boolean kollidiertMit(SpielObjekt objekt)
    {
        //mittelpunkt dieses SpielObjekts
        double mitteX = x + ( ((double) bild.getWidth())/2 );
        double mitteY = y + ( ((double) bild.getHeight())/2 );
        //mittelpunkt des anderen SpielObjekts
        double mitteXr = objekt.gibX() + ( ((double) objekt.getWidth())/2 );
        double mitteYr = objekt.gibY() + ( ((double) objekt.getHeight())/2 );
        double widthDurchschnitt = ((double) (bild.getWidth() + objekt.getWidth()) )/2;
        double heightDurchschnitt = ((double) (bild.getHeight() + objekt.getHeight()) )/2;
        double abstandX = Math.abs(mitteX - mitteXr);
        double abstandY = Math.abs(mitteY - mitteYr);
        return (abstandX < widthDurchschnitt)&&(abstandY < heightDurchschnitt);
    }
    
    public void entferneSelbst()
    {
    	entfernt = true;
        container.remove(bild);
        container.repaint();
    }
    
    public Bild gibBild()
    {
        return bild;
    }
}