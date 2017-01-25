package all;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintStream;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Document;

public class FileChooser
  extends JPanel
  implements ActionListener
{
  private static final String newline = "\n";
  JButton openButton;
  JButton saveButton;
  JTextArea log;
  JFileChooser fc;
  private File file;
  static JFrame frame = new JFrame("FileChooser");
  
  public FileChooser()
  {
    super(new BorderLayout());
    
    this.log = new JTextArea(5, 20);
    this.log.setMargin(new Insets(5, 5, 5, 5));
    this.log.setEditable(false);
    JScrollPane logScrollPane = new JScrollPane(this.log);
    
    this.fc = new JFileChooser();
    
    this.fc.setFileSelectionMode(2);
    this.fc.setAcceptAllFileFilterUsed(false);
    FileNameExtensionFilter filter = new FileNameExtensionFilter("SaveGames - .TDSsg", new String[] { "TDSsg" });
    
    this.fc.setFileFilter(filter);
    
    this.openButton = new JButton("Savegame Datei oeffnen", createImageIcon("Bilder/Open16.gif"));
    this.openButton.addActionListener(this);
    
    this.saveButton = new JButton("Savegame Datei speichern", createImageIcon("Bilder/Save16.gif"));
    this.saveButton.addActionListener(this);
    
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(this.openButton);
    buttonPanel.add(this.saveButton);
    
    add(buttonPanel, "First");
    add(logScrollPane, "Center");
  }
  
  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource() == this.openButton)
    {
      int returnVal = this.fc.showOpenDialog(this);
      if (returnVal == 0)
      {
        this.file = this.fc.getSelectedFile();
        
        SpielStandLeser spielstandleser = new SpielStandLeser();
        Main.salLaden(spielstandleser.leseDatei(this.file));
        frame.setVisible(false);
      }
      else
      {
        this.log.append("Open command cancelled by user.\n");
      }
      this.log.setCaretPosition(this.log.getDocument().getLength());
    }
    else if (e.getSource() == this.saveButton)
    {
      int returnVal = this.fc.showSaveDialog(this);
      if (returnVal == 0)
      {
        this.file = this.fc.getSelectedFile();
        if (!this.file.getPath().endsWith(".TDSsg"))
        {
          String fileEnd = ".TDSsg";
          String filePath = this.file.getPath();
          String fileFullPath = filePath + fileEnd;
          this.file = new File(fileFullPath);
        }
        Main.salSpeichern();
        SpielStandSchreiber spielstandschreiber = new SpielStandSchreiber();
        spielstandschreiber.schreibeSpielStand(this.file);
        frame.setVisible(false);
      }
      else
      {
        this.log.append("Save command cancelled by user.\n");
      }
      this.log.setCaretPosition(this.log.getDocument().getLength());
    }
  }
  
  protected static ImageIcon createImageIcon(String path)
  {
    URL imgURL = FileChooser.class.getResource(path);
    if (imgURL != null) {
      return new ImageIcon(imgURL);
    }
    System.err.println("Couldn't find file: " + path);
    return null;
  }
  
  private static void createAndShowGUI()
  {
    frame.add(new FileChooser());
    
    frame.pack();
    frame.setVisible(false);
  }
  
  public static void main()
  {
    SwingUtilities.invokeLater(new Runnable()
    {
      public void run()
      {
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        FileChooser.createAndShowGUI();
      }
    });
    frame.setVisible(false);
  }
  
  public static void setvisible(boolean b)
  {
    frame.setVisible(b);
  }
}
