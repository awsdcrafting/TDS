package all;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class JElementHelper 
{
	public static JButton baueKnopf(String text, int x, int y, ActionListener listener)
	{
		return baueKnopf(text, x, y, 140, 35, listener);
	}
	
	public static JButton baueKnopf(String text, int x, int y, int xSize, int ySize, ActionListener listener)
	{
		JButton knopf = baueKnopf(text, x, y, xSize, ySize);
        knopf.addActionListener(listener);
        return knopf;
	}
	
	public static JButton baueKnopf(String text, int x, int y)
    {
		return baueKnopf(text, x, y, 140, 35);
    }
	
	public static JButton baueKnopf(String text, int x, int y, int xSize, int ySize)
    {
        JButton knopf = new JButton();
        knopf.setBounds(x,y,xSize,ySize);
        knopf.setBackground(new Color(214,217,223));
        knopf.setForeground(new Color(0,0,0));
        knopf.setEnabled(true);
        knopf.setFont(new Font("SansSerif",0,12));
        knopf.setText(text);
        knopf.setVisible(true);
        return knopf;
    }
	
	public static JLabel baueLabel(String text, int x, int y)
	{
		return baueLabel(text, x, y, 140, 35);
	}

	public static JLabel baueLabel(String text, int x, int y, int xSize, int ySize)
    {
        JLabel label = new JLabel();
        label.setBounds(x,y,xSize,ySize);
        label.setBackground(new Color(214,217,223));
        label.setForeground(new Color(0,0,0));
        label.setEnabled(true);
        label.setFont(new Font("SansSerif",0,12));
        label.setText(text);
        label.setVisible(true);
        return label;
    }
    
	public static JTextArea baueTextArea(String text, int x, int y)
    {
		return baueTextArea(text, x, y, 140, 35);
    }
	
    public static JTextArea baueTextArea(String text, int x, int y, int xSize, int ySize)
    {
        JTextArea eingabe = new JTextArea();
        eingabe.setBounds(x,y,xSize,ySize);
        eingabe.setBackground(new Color(255,255,255));
        eingabe.setForeground(new Color(0,0,0));
        eingabe.setEnabled(true);
        eingabe.setFont(new Font("SansSerif",0,12));
        eingabe.setText(text);
        eingabe.setBorder(BorderFactory.createBevelBorder(1));
        eingabe.setVisible(true);
        return eingabe;
    }
    
    public static ChatTextArea baueChatStyleTextArea(String text, int x, int y)
    {
    	return baueChatStyleTextArea(text, x, y, 140, 35);
    }
    
    public static ChatTextArea baueChatStyleTextArea(String text, int x, int y, int lines)
    {
    	return baueChatStyleTextArea(text, x, y, 140, 35, lines);
    }
    
    public static ChatTextArea baueChatStyleTextArea(String text, int x, int y, int xSize, int ySize, int lines)
    {
    	ChatTextArea chat = baueChatStyleTextArea(text, x, y, xSize, ySize);
    	chat.setAmountLines(lines);
    	return chat;
    }
    public static ChatTextArea baueChatStyleTextArea(String text, int x, int y, int xSize, int ySize)
    {
        ChatTextArea ausgabe = new ChatTextArea();
        ausgabe.setBounds(x,y,xSize,ySize);
        ausgabe.setBackground(new Color(255,255,255));
        ausgabe.setForeground(new Color(0,0,0));
        ausgabe.setEnabled(true);
        ausgabe.setFont(new Font("SansSerif",0,12));
        ausgabe.setText(text);
        ausgabe.setBorder(BorderFactory.createBevelBorder(1));
        ausgabe.setEditable(false);
        ausgabe.setVisible(true);
        return ausgabe;
    }
    
    public static JScrollPane baueScrollPane(JComponent component, int x, int y)
    {
    	return baueScrollPane(component, x, y, 140, 35);
    }
    
    public static JScrollPane baueScrollPane(JComponent component, int x, int y, int xSize, int ySize)
    {
    	component.setBorder(BorderFactory.createEmptyBorder());
        JScrollPane scrollPane = new JScrollPane(component);
        scrollPane.setBounds(x,y,xSize,ySize);
        scrollPane.setBackground(new Color(255,255,255));
        scrollPane.setForeground(new Color(0,0,0));
        scrollPane.setEnabled(true);
        scrollPane.setBorder(BorderFactory.createBevelBorder(1));
        scrollPane.setVisible(true);
        return scrollPane;
    }
    
    public static JTextField baueTextField(String text, int x, int y)
    {
    	return baueTextField(text, x, y, 140, 35);
    }
    
    public static JTextField baueTextField(String text, int x, int y, ActionListener listener)
    {
    	JTextField feld = baueTextField(text, x, y);
    	feld.addActionListener(listener);
    	return feld;
    }
    
    public static JTextField baueTextField(String text, int x, int y, int xSize, int ySize, ActionListener listener)
    {
    	JTextField feld = baueTextField(text, x, y, xSize, ySize);
    	feld.addActionListener(listener);
    	return feld;
    }
    public static JTextField baueTextField(String text, int x, int y, int xSize, int ySize)
    {
    	JTextField feld = new JTextField();
        feld.setBounds(x,y,xSize,ySize);
        feld.setBackground(new Color(255,255,255));
        feld.setForeground(new Color(0,0,0));
        feld.setEnabled(true);
        feld.setFont(new Font("SansSerif",0,12));
        feld.setText(text);
        feld.setBorder(BorderFactory.createBevelBorder(1));
        feld.setVisible(true);
        return feld;
    }
    
    public static <T> JComboBox<T> baueComboBox(int x, int y)
    {
    	return baueComboBox(x, y, 140, 35);
    }
    
    public static <T> JComboBox<T> baueComboBox(int x, int y, int xSize, int ySize)
    {
        JComboBox<T> comboBox = new JComboBox<T>();
        comboBox.setBounds(x, y, xSize, ySize);
        comboBox.setBackground(new Color(214, 217, 223));
        comboBox.setForeground(new Color(0, 0, 0));
        comboBox.setEnabled(true);
        comboBox.setFont(new Font("sansserif", 0, 12));
        comboBox.setVisible(true);
        return comboBox;
    }
    
    public static <T> JList<T> baueList(int x, int y)
    {
    	return baueList(x, y, 140, 35);
    }
    
    public static <T> JList<T> baueList(int x, int y, int xSize, int ySize)
    {
        JList<T> list = new JList<T>();
        list.setBounds(x, y, xSize, ySize);
        list.setBackground(new Color(214, 217, 223));
        list.setForeground(new Color(0, 0, 0));
        list.setEnabled(true);
        list.setFont(new Font("sansserif", 0, 12));
        list.setVisible(true);
        return list;
    }
    
    public static Bild baueBild(String bildName, int x, int y)
    {
        return baueBild(bildName, x, y, 140, 35);
    }
    
    public static Bild baueBild(String bildName, int x, int y, int xSize, int ySize)
    {
        Bild bild = new Bild(bildName);
        bild.setBounds(x,y,xSize,ySize);
        bild.setBackground(new Color(0,0,0,0));
        bild.setForeground(new Color(0,0,0,0));
        bild.setEnabled(true);
        bild.setBorder(BorderFactory.createEmptyBorder());
        bild.setVisible(true);
        return bild;
    }
    
    public static Bild baueBild(Image image, int x, int y)
    {
        return baueBild(image, x, y, 140, 35);
    }
    
    public static Bild baueBild(Image image, int x, int y, int xSize, int ySize)
    {
        Bild bild = new Bild(image);
        bild.setBounds(x,y,xSize,ySize);
        bild.setBackground(new Color(0,0,0,0));
        bild.setForeground(new Color(0,0,0,0));
        bild.setEnabled(true);
        bild.setBorder(BorderFactory.createEmptyBorder());
        bild.setVisible(true);
        return bild;
    }
}
