

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Bild extends JPanel 
{
    private BufferedImage img;
    private boolean drawAll;
    private boolean isValid;
    
    public Bild() 
    {
        img = null;
        drawAll = true;
        isValid = false;
    }
    
    public Bild(Image image)
    {
        this(image, true);
    }
    
    public Bild(Image image, boolean drawAll) 
    {
        this.drawAll = drawAll;
        if(image == null)
        {
            img = null;
            isValid = false;
        }
        else if(image instanceof BufferedImage)
        {
            img = (BufferedImage) image;
            isValid = true;
        }
        else
        {
            BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

            // Draw the image on to the buffered image
            Graphics2D bGr = bimage.createGraphics();
            bGr.drawImage(img, 0, 0, new Color(0, 0, 0, 0), null);
            bGr.dispose();
            img = bimage;
            isValid = true;
        }
    }
    
    public Bild(String bild)
    {
        this(bild,true);
    }
    
    public Bild(String bild, boolean drawAll)
    {
        try
        {
            File f = new File(bild);
            if(f.exists())
            {
                img = ImageIO.read(f);
            }
            else
            {
                InputStream is = Main.gibResource(bild);
                img = ImageIO.read(is);
            }
            isValid = true;
        }
        catch(Exception e)
        {
            System.out.println(e);
            isValid = false;
        }
        this.drawAll = drawAll;
    }
    
    public boolean isValid()
    {
        return isValid;
    }
    
    public void setImage(Image image)
    {
        if(image == null)
        {
            img = null;
            isValid = false;
        }
        else if(image instanceof BufferedImage)
        {
            img = (BufferedImage) image;
            isValid = true;
        }
        else
        {
            BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

            // Draw the image on to the buffered image
            Graphics2D bGr = bimage.createGraphics();
            bGr.drawImage(img, 0, 0, new Color(0, 0, 0, 0), null);
            bGr.dispose();
            img = bimage;
            isValid = true;
        }
    }
    
    @Override
    public void paint(Graphics g)
    {
        if(!isValid) return;
        Graphics2D g2d = (Graphics2D) g;
        int bx =this.getWidth();
        int by =this.getHeight();
        int width;
        int height;
        if(drawAll)
        {
            width = img.getWidth();
            height = img.getHeight();
        }
        else
        {
            width = this.getWidth() <= img.getWidth() ? this.getWidth() : img.getWidth();
            height = this.getHeight() <= img.getHeight() ? this.getHeight() : img.getHeight();
        }
        g2d.drawImage(img, 0, 0, bx, by, 0, 0, width, height, new Color(0, 0, 0, 0), null);
        super.paint(g);
    }
    
}
