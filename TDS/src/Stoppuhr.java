
public class Stoppuhr{
    private static long zeit;
    private static long zeitStart;
    private static long zeitEnde;
    private static boolean ZeitMessungAktiv;
    
    public static void zeitMessenStart(){
        zeitStart = System.currentTimeMillis();
        ZeitMessungAktiv = true;
    }
    
    public static void zeitMessenende(){
        if(ZeitMessungAktiv == true){
            zeitEnde = System.currentTimeMillis();
        }
        else{System.out.println("Bitte erst anfangen zu messen ;)!");}
    }
    
    public static long gibZeit(){
        zeit = zeitEnde - zeitStart;
        System.out.println("");
        return zeit;
    }
    
}
