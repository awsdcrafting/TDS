


import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;

public class ChatTextArea extends JTextArea
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4538836711452941905L;

	
	protected Integer amountLines;
	/**
	 * Only run after initialising the Window!!
	 * will throw NPE otherwise!
	 */
	public void fill()
	{
		fill("\n");
	}
	
	public void setAmountLines(int lines)
	{
		amountLines = lines;
	}
	
	public void fill(String message)
	{
		String s = "";
		Font f = this.getFont();
		Graphics g = this.getGraphics();
		FontMetrics fm = g.getFontMetrics(f);
		int i = fm.getHeight();
		int lines =(int) Math.ceil(((double)this.getHeight())/((double)i));
		for(int j = 2; j<=lines;j++)
		{
			s = s.concat("\n");
		}
		s = s.concat(message);
		this.setText(s);
	}
	
	@Override
	public void append(String s)
	{
        Document doc = getDocument();
        if (doc != null) 
        {
            try 
            {
            	Element root = doc.getDefaultRootElement();
            	if(amountLines != null)
            	{
            		if(root.getElementCount() > amountLines)
                	{
                    	Element first = root.getElement(0);
                    	doc.remove(0, first.getEndOffset());
                	}
                    doc.insertString(doc.getLength(), s, null);
            	}
            	else
            	{
                	Element first = root.getElement(0);
                	doc.remove(0, first.getEndOffset());
                    doc.insertString(doc.getLength(), s, null);
            	}
            }
            catch (BadLocationException e) 
            {
            	e.printStackTrace();
            }
        }
	}
}