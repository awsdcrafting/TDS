package all;
import java.util.Random;
public class RNDM {
    private static Random random = new Random();

    /**
     * Fuer die X-Position des Ufos (in PhysicsEngine)
     */
    public static int rndmX()
    {
        return gibZufallszahl(50,550);
    }

    /**
     * Wie viele Ufos erzeugt werden (in PhysicsEngine)
     */
    public static int rndmAnzahl()
    {
        return gibZufallszahl(2,4);
    }

    public static int rndmDrops(int[] dropWertungen)
    {
        if(random.nextInt(100)<5)
        {
            int gesamtWertungen = 0;
            for(int i = 0; i < dropWertungen.length; i++)
            {
                gesamtWertungen += dropWertungen[i];
            }
            int wertungGeld = gesamtWertungen;
            gesamtWertungen *= 2;
            int[] dropMaxWertungen = new int[dropWertungen.length + 1];
            dropMaxWertungen[0] = dropWertungen[0];
            for(int i = 1; i < dropWertungen.length; i++)
            {
                dropMaxWertungen[i] = dropWertungen[i] + dropMaxWertungen[i - 1];
            }
            dropMaxWertungen[dropWertungen.length] = gesamtWertungen;
            int drop = gibZufallszahl(1, gesamtWertungen);
            for(int i = 0; i < dropMaxWertungen.length; i++)
            {
                if(drop <= dropMaxWertungen[i])
                {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int gibZufallszahl()
    {
        return random.nextInt();
    }

    public static int gibZufallszahl(int max)
    {
        return random.nextInt(max);
    }

    public static int gibZufallszahl(int min, int max)
    {
        return random.nextInt(max - min) + min;
    }
}