import java.util.Random;

public abstract class Creature extends ACreature implements IRandomize {
    protected Random rnd;
    protected long seed=0;
    protected int level;
    protected int lastMealTime;

    public Creature(IMap map) {
        super(map);
        rnd = new Random(seed);
        this.level = 1;
        this.lastMealTime = 1;
    }
    //Do dokonczenia funkcje
    @Override
    public void Move(int[] pos) {

    }
    @Override
    public void Die() {

    }

    @Override
    public long GetSeed() {return seed;}
    @Override
    public void SetSeed(long seed) {
        this.seed = seed;
    }
}
