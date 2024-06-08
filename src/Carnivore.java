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
        int rollRange = 10;
        ICreature nearestPray = SearchForNearestPray();
        if (nearestPray!=null)
        {
            System.out.println("Nearest pray pos: "+nearestPray);
        }
        lastMealTime++;
        int roll = rnd.nextInt(rollRange);
        if (deathTimer-1==lastMealTime || deathTimer-2==lastMealTime)
        {
            if(nearestPray!=null)
            {
                EatPray(nearestPray);
            } else {
                Move();
            }
        } else if(roll<10) {
            Move();
        } else if (roll == 10) {

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
    public ICreature SearchForNearestPray() {
        int[] currentPos = map.GetCreaturePos(this);
        int[] posOfNearestPray = {};
        ICreature nearestPray = null;
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
                            nearestPray = pray;
                        } else if (Math.pow(currentPos[0]-positionToSearch[0],2)+Math.pow(currentPos[1]-positionToSearch[1],2) < Math.pow(currentPos[0]-posOfNearestPray[0],2)+Math.pow(currentPos[1]-posOfNearestPray[1],2)) {
                            posOfNearestPray = positionToSearch;
                            nearestPray = pray;
                        }
                    }
                }
            }
        }
        return nearestPray;
    }

    @Override
    public void EatPray(ICreature pray) {
        int[] prayPos = map.GetCreaturePos(pray);
        pray.Die();
        this.Move(prayPos);
        lastMealTime = 0;
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
