public class Herbivore extends Creature implements IHerbivore{
    public Herbivore(IMap map, int mobility, int deathTimer)
    {
        super(map, mobility, deathTimer);
    }

    @Override
    public void DecideAction() {
        if(deathTimer==lastMealTime)
        {
            Die();
            return;
        }
        lastMealTime++;
        int roll = rnd.nextInt(2);
        if(roll==1)
        {
            Move();
        }
    }

    @Override
    public boolean IsNearFood() {
        return false;
    }

    @Override
    public int[] NearestFoodPos() {
        return new int[2];
    }

    @Override
    public void EatFood(int[] pos) {

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
