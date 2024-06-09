public class Herbivore extends Creature implements IHerbivore
{
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
        int rollRange = 10;
        IObject nearestFood = SearchForNearestFood();
        lastMealTime++;
        int roll = rnd.nextInt(rollRange);
        if (deathTimer-1==lastMealTime || deathTimer-2==lastMealTime)
        {
            if(nearestFood!=null)
            {
                EatFood(nearestFood);
            } else {
                Move();
            }
        } else if(roll<10) {
            Move();
        } else if (roll == 10) {

        }
    }

    @Override
    public IObject SearchForNearestFood() {
        int[] currentPos = map.GetCreaturePos(this);
        int[] posofNearestFood = {};
        IObject nearestFood = null;
        for (int i = -mobility; i<mobility;i++)
        {
            for (int j = -mobility; j<mobility;j++)
            {
                if(i!=0 && j!=0)
                {
                    int[] positionToSearch = {currentPos[0]+i, currentPos[1]+j};
                    IObject food = map.GetObjectByPos(positionToSearch);
                    if(food != null)
                    {
                        if(posofNearestFood.length != 2)
                        {
                            posofNearestFood = positionToSearch;
                            nearestFood = food;
                        } else if (Math.pow(currentPos[0]-positionToSearch[0],2)+Math.pow(currentPos[1]-positionToSearch[1],2) < Math.pow(currentPos[0]-posofNearestFood[0],2)+Math.pow(currentPos[1]-posofNearestFood[1],2)) {
                            posofNearestFood = positionToSearch;
                            nearestFood = food;
                        }
                    }
                }
            }
        }
        return nearestFood;
    }

    @Override
    public void EatFood(IObject food)
    {
        int[] foodPos = map.GetObjectPos(food);
        food.getEaten(food);
        this.Move(foodPos);
        lastMealTime = 0;
    }
    @Override
    public boolean IsSameType(ICreature creature) {
        return false;
    }

    @Override
    public ICreature NearestAlly() {
        return null;
    };

    @Override
    public void Copulate(ICreature ally)
    {

    }
}
