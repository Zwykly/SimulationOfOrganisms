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
        ICreature nearestAlly = NearestAlly();
        lastMealTime++;
        int roll = rnd.nextInt(rollRange);
        if (deathTimer-1==lastMealTime || deathTimer-2==lastMealTime)
        {
            if(nearestPray!=null)
            {
                EatPray(nearestPray);
            } else if (deathTimer-1==lastMealTime && nearestAlly != null) {
                EatPray(nearestAlly);
            } else {
                Move();
            }
        } else {
            Move();
        }

    }

    @Override
    public ICreature SearchForNearestPray() {
        int[] currentPos = map.GetCreaturePos(this).clone();
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
        int[] prayPos = map.GetCreaturePos(pray).clone();
        pray.Die();
        this.Move(prayPos);
        lastMealTime = 0;
        level++;
    }
    @Override
    public ICreature NearestAlly() {
        int[] currentPos = map.GetCreaturePos(this).clone();
        int[] posOfNearestAlly = {};
        ICreature nearestAlly = null;
        for (int i = -mobility; i<mobility;i++)
        {
            for (int j = -mobility; j<mobility;j++)
            {
                if(i!=0 && j!=0)
                {
                    int[] positionToSearch = {currentPos[0]+i, currentPos[1]+j};
                    ICreature ally = map.GetCreatureByPos(positionToSearch);
                    if(ally instanceof Carnivore)
                    {
                        if(posOfNearestAlly.length != 2)
                        {
                            posOfNearestAlly = positionToSearch;
                            nearestAlly = ally;
                        } else if (Math.pow(currentPos[0]-positionToSearch[0],2)+Math.pow(currentPos[1]-positionToSearch[1],2) < Math.pow(currentPos[0]-posOfNearestAlly[0],2)+Math.pow(currentPos[1]-posOfNearestAlly[1],2)) {
                            posOfNearestAlly = positionToSearch;
                            nearestAlly = ally;
                        }
                    }
                }
            }
        }
        return nearestAlly;
    }
}
