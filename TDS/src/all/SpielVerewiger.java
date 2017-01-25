package all;
import java.util.Date;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SpielVerewiger {
  private static int f = 1;
  FileWriter writer;
  File file;
  

   
  public void schreibenStart(){
    // File anlegen
     file = new File("Stats/SpielStats" + f + ".txt");
     try {
       // new FileWriter(file ,true) - falls die Datei bereits existiert
       // werden die Bytes an das Ende der Datei geschrieben
       
       // new FileWriter(file) - falls die Datei bereits existiert
       // wird diese überschrieben
       writer = new FileWriter(file);
       
       // Text wird in den Stream geschrieben
       writer.write("Spiel stats beginn.");
       
       // Platformunabhängiger Zeilenumbruch wird in den Stream geschrieben
       writer.write(System.getProperty("line.separator"));
       
       //Datum wird in den Stream geschrieben
       writer.write("am: " + Datum.Y() + ":" + Datum.M() + ":" + Datum.D());
       
       // Platformunabhängiger Zeilenumbruch wird in den Stream geschrieben
       writer.write(System.getProperty("line.separator"));
       
       //Zeit wird in den Stream geschrieben
       writer.write("um: " + Datum.h() + ":" + Datum.m());
              
       // Platformunabhängiger Zeilenumbruch wird in den Stream geschrieben
       writer.write(System.getProperty("line.separator"));
       
       // Schreibt den Stream in die Datei
       // Sollte immer am Ende ausgeführt werden, sodass der Stream 
       // leer ist und alles in der Datei steht.
       writer.flush();
       
       // Schließt den Stream
       writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
    public void schreibenGE(){
    // File anlegen
     file = new File("Stats/SpielStats" + f + ".txt");
     try {
       // new FileWriter(file ,true) - falls die Datei bereits existiert
       // werden die Bytes an das Ende der Datei geschrieben
       
       // new FileWriter(file) - falls die Datei bereits existiert
       // wird diese überschrieben
       writer = new FileWriter(file, true);
       
       
       // Platformunabhängiger Zeilenumbruch wird in den Stream geschrieben
       writer.write(System.getProperty("line.separator"));
       
       // Schreibt den Stream in die Datei
       // Sollte immer am Ende ausgeführt werden, sodass der Stream 
       // leer ist und alles in der Datei steht.
       writer.flush();
       
       // Schließt den Stream
       writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public void schreibenEnde(){
    // File anlegen
     file = new File("Stats/SpielStats" + f + ".txt");
     try {
       // new FileWriter(file ,true) - falls die Datei bereits existiert
       // werden die Bytes an das Ende der Datei geschrieben
       
       // new FileWriter(file) - falls die Datei bereits existiert
       // wird diese überschrieben
       writer = new FileWriter(file, true);
       
       // Text wird in den Stream geschrieben
       writer.write("Erreichte punkte: " + "");
       
       // Platformunabhängiger Zeilenumbruch wird in den Stream geschrieben
       writer.write(System.getProperty("line.separator"));
       
       //Datum wird in den Stream geschrieben
       writer.write("am: " + Datum.Y() + ":" + Datum.M() + ":" + Datum.D());
       
       // Platformunabhängiger Zeilenumbruch wird in den Stream geschrieben
       writer.write(System.getProperty("line.separator"));
       
       //Zeit wird in den Stream geschrieben
       writer.write("um: " + Datum.h() + ":" + Datum.m());
       
       // Platformunabhängiger Zeilenumbruch wird in den Stream geschrieben
       writer.write(System.getProperty("line.separator"));
       
       // Text wird in den Stream geschrieben
       writer.write("Spiel stats ende.");

       // Platformunabhängiger Zeilenumbruch wird in den Stream geschrieben
       writer.write(System.getProperty("line.separator"));
       
       // Schreibt den Stream in die Datei
       // Sollte immer am Ende ausgeführt werden, sodass der Stream 
       // leer ist und alles in der Datei steht.
       writer.flush();
       
       // Schließt den Stream
       writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }  
  
    public void schreibenF(){
    // File anlegen
     file = new File("f.txt");
     try {
       // new FileWriter(file ,true) - falls die Datei bereits existiert
       // werden die Bytes an das Ende der Datei geschrieben
       
       // new FileWriter(file) - falls die Datei bereits existiert
       // wird diese überschrieben
       writer = new FileWriter(file);
       
       // Text wird in den Stream geschrieben
       writer.write("" + f);
       
       // Schreibt den Stream in die Datei
       // Sollte immer am Ende ausgeführt werden, sodass der Stream 
       // leer ist und alles in der Datei steht.
       writer.flush();
       
       // Schließt den Stream
       writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
   public static void SetzeFaufEins(){
    File file;
    FileWriter writer;
    file = new File("f.txt");
     try {
       // new FileWriter(file ,true) - falls die Datei bereits existiert
       // werden die Bytes an das Ende der Datei geschrieben
       
       // new FileWriter(file) - falls die Datei bereits existiert
       // wird diese überschrieben
       writer = new FileWriter(file);
       
       // Text wird in den Stream geschrieben
       writer.write("" + 1);
       
       // Schreibt den Stream in die Datei
       // Sollte immer am Ende ausgeführt werden, sodass der Stream 
       // leer ist und alles in der Datei steht.
       writer.flush();
       
       // Schließt den Stream
       writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public static void StatsSchreiberStart(){
    f = F.getF();
    SpielVerewiger SpielVerewiger_ = new SpielVerewiger();
    SpielVerewiger_.schreibenStart();

  }
  
  public static void StatsSchreiberGE(){
    SpielVerewiger SpielVerewiger_ = new SpielVerewiger();
    SpielVerewiger_.schreibenGE();
  }
  
  public static void StatsSchreiberEnde() {
    SpielVerewiger SpielVerewiger_ = new SpielVerewiger();
    SpielVerewiger_.schreibenEnde();
    f+=1;
  }
  
  public static void StatsSchreiberF(){
    SpielVerewiger SpielVerewiger_ = new SpielVerewiger();
    SpielVerewiger_.schreibenF();
  }

}