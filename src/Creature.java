import java.util.Random;

public abstract class Creature extends ACreature implements IRandomize
{
    protected Random rnd;
    protected static long seed = 0;
    protected int level;
    protected int lastMealTime;

    public Creature(IMap map)
    {
        super(map);
        rnd = new Random(seed);
        this.seed++;
        this.level = 1;
        this.lastMealTime = 1;
    }
    //Do dokonczenia funkcje
    @Override
    public void Move()
    {
        int[] checkPos = map.GetCreaturePos(this);
        do
        {

            checkPos[0] += rnd.nextInt(10)-5;
            if(checkPos[0]<0)
            {checkPos[0] = 0;}
            else if(checkPos[0]> map.GetSize()-1)
            {checkPos[0] = map.GetSize()-1;}

            checkPos[1] += rnd.nextInt(10)-5;
            if(checkPos[1]<0)
            {checkPos[1] = 0;}
            else if(checkPos[1]> map.GetSize()-1)
            {checkPos[1] = map.GetSize()-1;}
            System.out.println(checkPos[0] + " " + checkPos[1]);
        } while (map.SettleCreature(this, checkPos));
    }

    @Override
    public void Move(int[] pos)
    {

    }

    @Override
    public void Die()
    {

    }

    @Override
    public long GetSeed() {return seed;}
    @Override
    public void SetSeed(long seed) {
        this.seed = seed;
    }
}
