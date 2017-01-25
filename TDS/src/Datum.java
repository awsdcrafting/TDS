import java.util.Calendar;


public class Datum{
    
    
   public static void DatumUhrzeit(){
        
        
        
        Calendar cal = Calendar.getInstance ();
    
      // Die Monate werden mit 0 (= Januar) beginnend gezaehlt!
      // (Die Tage im Monat beginnen dagegen mit 1)

      System.out.println( "Datum: " + cal.get( Calendar.DAY_OF_MONTH ) +
                      "." + (cal.get( Calendar.MONTH ) + 1 ) +
                      "." + cal.get( Calendar.YEAR ) );

      System.out.println( "Uhrzeit: " + cal.get( Calendar.HOUR_OF_DAY ) + ":" +
                      cal.get( Calendar.MINUTE ) + ":" +
                      cal.get( Calendar.SECOND ) + ":" +
                      cal.get( Calendar.MILLISECOND ) );
                      
                      
   }
    
    
    
   public static int D(){
       Calendar cal = Calendar.getInstance ();
       int D = cal.get(Calendar.DAY_OF_MONTH);
       return D;
    }
    
   public static int M(){
       Calendar cal = Calendar.getInstance ();
       int M = cal.get(Calendar.MONTH)+1;
       return M;
    }    
    
   public static int Y(){
       Calendar cal = Calendar.getInstance ();
       int Y = cal.get(Calendar.YEAR);
       return Y;
    }     
    
   public static int h(){
       Calendar cal = Calendar.getInstance ();
       int h = cal.get(Calendar.HOUR_OF_DAY);
       return h;
    }
    
   public static int m(){
       Calendar cal = Calendar.getInstance ();
       int m = cal.get(Calendar.MINUTE);
       return m;
    }    
    
   public static int s(){
       Calendar cal = Calendar.getInstance ();
       int s = cal.get(Calendar.SECOND);
       return s;
    }
    
}