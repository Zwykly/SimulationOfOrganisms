public class Omnivore extends Creature implements IHerbivore, ICarnivore{
    public Omnivore(IMap map, int mobility, int deathTimer)
    {
        super(map, mobility, deathTimer);
    }

    @Override
    public void DecideAction()
    {
        if(deathTimer==lastMealTime)
        {
            Die();
            return;
        }
        ICreature nearestPray = SearchForNearestPray();
        ICreature nearestAlly = NearestAlly();
        IObject nearestFood = SearchForNearestFood();
        lastMealTime++;
        if (deathTimer-1==lastMealTime || deathTimer-2==lastMealTime)
        {
            if(nearestFood!=null)
            {
                EatFood(nearestFood);
            } else if (deathTimer-1==lastMealTime && nearestPray!=null) {
                EatPray(nearestPray);
            } else if (deathTimer-1==lastMealTime && nearestAlly!=null) {
                EatPray(nearestAlly);
            } else {
                Move();
            }
        } else {
            Move();
        }
    }
    @Override
    public ICreature SearchForNearestPray()
    {

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
                    if(!(pray instanceof Omnivore) && pray != null)
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
    }

    @Override
    public void EatFood(IObject food)
    {
        int[] foodPos = map.GetObjectPos(food).clone();
        food.getEaten();
        if(food instanceof OneUseFood)
        {
            this.Move(foodPos);
            map.DeleteObject(food, foodPos);
        } else {
            if(foodPos[0]-1>0)
            {
                int[] possiblePos = foodPos.clone();
                possiblePos[0]-=1;
                if(map.GetCreatureByPos(possiblePos)==null&&map.GetObjectByPos(possiblePos)==null)
                {
                    this.Move(possiblePos);
                    boolean ate = food.getEaten();
                    lastMealTime = 0;
                    level++;
                    return;
                }
            }
            if(foodPos[0]+1< map.GetSize())
            {
                int[] possiblePos = foodPos.clone();
                possiblePos[0]+=1;
                if(map.GetCreatureByPos(possiblePos)==null&&map.GetObjectByPos(possiblePos)==null)
                {
                    this.Move(possiblePos);
                    boolean ate = food.getEaten();
                    lastMealTime = 0;
                    level++;
                    return;
                }
            }
            if(foodPos[1]-1>0)
            {
                int[] possiblePos = foodPos.clone();
                possiblePos[1]-=1;
                if(map.GetCreatureByPos(possiblePos)==null&&map.GetObjectByPos(possiblePos)==null)
                {
                    this.Move(possiblePos);
                    boolean ate = food.getEaten();
                    lastMealTime = 0;
                    level++;
                    return;
                }
            }
            if(foodPos[1]-1< map.GetSize())
            {
                int[] possiblePos = foodPos.clone();
                possiblePos[1]+=1;
                if(map.GetCreatureByPos(possiblePos)==null&&map.GetObjectByPos(possiblePos)==null)
                {
                    this.Move(possiblePos);
                    boolean ate = food.getEaten();
                    lastMealTime = 0;
                    level++;
                    return;
                }
            }
        }
        lastMealTime = 0;
        level++;
    }

    @Override
    public IObject SearchForNearestFood() {
        int[] currentPos = map.GetCreaturePos(this).clone();
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
                    if(food != null && !(food instanceof Blankspace))
                    {
                        if(food instanceof OneUseFood)
                        {
                            if((posofNearestFood.length != 2)||(Math.pow(currentPos[0]-positionToSearch[0],2)+Math.pow(currentPos[1]-positionToSearch[1],2) < Math.pow(currentPos[0]-posofNearestFood[0],2)+Math.pow(currentPos[1]-posofNearestFood[1],2)))
                            {
                                posofNearestFood = positionToSearch;
                                nearestFood = food;
                            }
                        } else if(food.IsEdible()) {
                            if((posofNearestFood.length != 2)||(Math.pow(currentPos[0]-positionToSearch[0],2)+Math.pow(currentPos[1]-positionToSearch[1],2) < Math.pow(currentPos[0]-posofNearestFood[0],2)+Math.pow(currentPos[1]-posofNearestFood[1],2)))
                            {
                                int[] positionToLeft = positionToSearch.clone();
                                if(positionToLeft[0]-1>0)
                                {
                                    positionToLeft[0]-=1;
                                }
                                int[] positionToRight = positionToSearch.clone();
                                if(positionToRight[0]+1<map.GetSize())
                                {
                                    positionToRight[0]+=1;
                                }
                                int[] positionToBottom = positionToSearch.clone();
                                if(positionToBottom[1]-1>0)
                                {
                                    positionToBottom[1]-=1;
                                }
                                int[] positionToTop = positionToSearch.clone();
                                if(positionToTop[1]+1<map.GetSize())
                                {
                                    positionToTop[1]+=1;
                                }
                                if((map.GetCreatureByPos(positionToLeft)==null && map.GetObjectByPos(positionToLeft)==null)||(map.GetCreatureByPos(positionToRight)==null && map.GetObjectByPos(positionToRight)==null)||(map.GetCreatureByPos(positionToTop)==null && map.GetObjectByPos(positionToTop)==null)||(map.GetCreatureByPos(positionToBottom)==null && map.GetObjectByPos(positionToBottom)==null))
                                {
                                    posofNearestFood = positionToSearch;
                                    nearestFood = food;
                                }
                            }

                        }

                    }
                }
            }
        }
        return nearestFood;
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
