
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class SpielStandLeser
{
  public static int i;
  
  public static ArrayList<String> leseDatei(File file)
  {
    ArrayList<String> al = new ArrayList();
    try
    {
      FileReader fr = new FileReader(file);
      BufferedReader br = new BufferedReader(fr);
      String s = br.readLine();
      while (s != null)
      {
        al.add(s);
        s = br.readLine();
      }
      br.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return al;
  }
}
