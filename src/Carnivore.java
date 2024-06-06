public class Carnivore extends Creature implements ICarnivore
{
    public Carnivore(IMap map)
    {
        super(map);
    }

    @Override
    public void DecideAction() {
        int roll = rnd.nextInt(2);
        if(roll==1)
        {
            Move();
        }
    }

    @Override
    public boolean IsNearPray() {
        return false;
    }

    @Override
    public int[] NearestPrayPos() {
        return new int[2];
    }

    @Override
    public void EatPray(int[] pos) {

    }
    @Override
    public boolean IsSameType(ICreature creature) {
        return false;
    }
    public boolean IsNearAnotherOne() {
        return false;
    }

    @Override
    public void Copulate(int[] pos) {

    }
}
