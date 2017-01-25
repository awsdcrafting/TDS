
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SpielStandSchreiber
{
  private static int f = 1;
  FileWriter writer;
  File file;
  
  public void schreibeSpielStand(File file)
  {
    try
    {
      this.writer = new FileWriter(file);
        for (int i = 0; i < Main.sal.size(); i++)
        {
          this.writer.write(Main.sal.get(i));
          this.writer.write(System.getProperty("line.separator"));
        }
      this.writer.flush();
      
      this.writer.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
