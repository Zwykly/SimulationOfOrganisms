import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Klasa orgaznimu z której następnie dziedziczą inne organizmy.
 */
public abstract class Creature extends ACreature implements IRandomize
{
    protected Random rnd;
    protected static long seed = 0;
    protected int level;
    protected int lastMealTime;
    protected int mobility;
    protected int deathTimer;
    protected Map<String, int[]>  nearestThings;

    /**
     * Konstruktor obiektu organizmu.
     * @param map mapa do której przypisany jest organizm.
     * @param mobility zakres ruchu organizmu.
     * @param deathTimer długość życia ogranizmu
     */
    public Creature(IMap map, int mobility, int deathTimer)
    {
        super(map);
        rnd = new Random(seed);
        this.seed++;
        this.level = 1;
        this.lastMealTime = 1;
        this.mobility = mobility;
        this.deathTimer = deathTimer;
        this.nearestThings = new HashMap<>();
    }

    /**
     * Metoda ta losuje miejsce w zakresie od -mobility do mobility, i postawia tam organizm.
     */
    @Override
    public void Move() {
        int[] currentPos = map.GetCreaturePos(this).clone();
        int tries = 0;
        int[] checkPos;
        do {
            tries++;
            if (tries >= mobility) {
                return;
            }
            checkPos = currentPos.clone();
            checkPos[0] += rnd.nextInt(mobility * 2) - mobility;
            if (checkPos[0] < 0) {
                checkPos[0] = 0;
            } else if (checkPos[0] > map.GetSize() - 1) {
                checkPos[0] = map.GetSize() - 1;
            }

            checkPos[1] += rnd.nextInt(mobility * 2) - mobility;
            if (checkPos[1] < 0) {
                checkPos[1] = 0;
            } else if (checkPos[1] > map.GetSize() - 1) {
                checkPos[1] = map.GetSize() - 1;
            }
        } while (!map.SettleCreature(this, checkPos));
    }

    /**
     * Metoda która porusza organizm na dane miejsce.
     * @param pos miejsce na które ma się poruszyć organizm
     * @return boolean - informujacy czy dane miejsce jest wolne.
     */
    @Override
    public boolean Move(int[] pos) {
        return map.SettleCreature(this, pos);
    }

    /**
     * Metoda która usuwa organizm z HashMapy organizmów i ich pozycji.
     */
    @Override
    public void Die() {
        map.KillCreature(this);
    }

    /**
     * Getter który zwraca seed zapisany w organizmie.
     * @return seed - zmienna od której zależy zachowanie organizmu.
     */
    @Override
    public long GetSeed() {return seed;}

    /**
     * Setter który nadpisuje seed zapisany w organizmie.
     * @param seed
     */
    @Override
    public void SetSeed(long seed) { this.seed = seed; }
}
