public class Carnivore extends Creature implements ICarnivore {
    public Carnivore(IMap map, int mobility, int deathTimer)
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
        int[] nearestPray = NearestPrayPos();
        if (nearestPray.length == 2)
        {
            System.out.println("Nearest pray pos: "+nearestPray);
        }
        lastMealTime++;
        int roll = rnd.nextInt(2);
        if(roll==1)
        {
            Move();
        }
    }

    @Override
    public boolean IsNearPray() {
        int[] currentPos = map.GetCreaturePos(this);
        for (int i = -mobility; i>mobility;i++)
        {
            for (int j = -mobility; j>mobility;j++)
            {
                if(i==0 && j==0)
                {
                } else {
                    int[] positionToSearch = {currentPos[0]+i, currentPos[1]+j};
                    if(map.GetCreatureByPos(positionToSearch) instanceof Carnivore)
                    {
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public int[] NearestPrayPos() {
        int[] currentPos = map.GetCreaturePos(this);
        int[] posOfNearestPray = {};
        System.out.println("Finding pray");
        for (int i = -mobility; i<mobility;i++)
        {
            for (int j = -mobility; j<mobility;j++)
            {
                if(i!=0 && j!=0)
                {
                    int[] positionToSearch = {currentPos[0]+i, currentPos[1]+j};
                    ICreature pray = map.GetCreatureByPos(positionToSearch);
                    if(!(pray instanceof Carnivore) && pray != null)
                    {
                        System.out.println("Found pray");
                        if(posOfNearestPray.length != 2)
                        {
                            posOfNearestPray = positionToSearch;
                        } else if (Math.pow(currentPos[0]-positionToSearch[0],2)+Math.pow(currentPos[1]-positionToSearch[1],2) < Math.pow(currentPos[0]-posOfNearestPray[0],2)+Math.pow(currentPos[1]-posOfNearestPray[1],2)) {
                            posOfNearestPray = positionToSearch;
                        }
                    }
                }
            }
        }
        return posOfNearestPray;
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
