
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class F{
  public static int i;
  public static int getF()
  {
      int f = 0;
      String s = "";
      try
      {
          FileReader fr = new FileReader("f.txt");
          BufferedReader br = new BufferedReader(fr);
          s = br.readLine();
          if(s == null)
          {
              System.out.println("nix in der Datei");
              System.out.println("überschreibe mit 1");
              SpielVerewiger.SetzeFaufEins();
          }
          else
          {
              f = Integer.parseInt(s);
          }
          br.close();
      }
      catch(IOException ioe)
      {
          System.out.println("kann Datei nicht öffnen");
      }
      catch(NumberFormatException nfe)
      {
          System.out.println(s +" ist keine Zahl");
          System.out.println("überschreibe mit 1");
          SpielVerewiger.SetzeFaufEins();
      }
      return f;
    }

}
