package all;

public class Drop
{
    int[] dropWertungen = new int[0];

    
    /**
     * Erstellung der Drop wertungen
     */
    public Drop()
    {
        int reperatur = 5;
        int schild = 5;
        int extraLeben = 5;
        int schadensBoost = 5;
        int geld = 50;

        int[] dropWertungen = new int[5];
        dropWertungen[0] = reperatur;
        dropWertungen[1] = schild;
        dropWertungen[2] = extraLeben;
        dropWertungen[3] = schadensBoost;
        dropWertungen[4] = geld;
    }

}
