/**
 * Klasa dla obiektu organizmu wszystkożernego.
 */
public class Omnivore extends Creature implements IHerbivore, ICarnivore{
    /**
     * Konstruktor tworzący obiekt organizmu wszystkożernego.
     * @param map
     * @param mobility
     * @param deathTimer
     */
    public Omnivore(IMap map, int mobility, int deathTimer)
    {
        super(map, mobility, deathTimer);
    }
    /**
     * Metoda która decyduje o zachowaniu organizmu.
     */
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
    /**
     * Metoda która poszukuje najbliższej ofiary i zwaraca ją w postaci ICreature. Jeśli nie znajdzie ofiary to zwraca ona null.
     * @return nearestPray/null
     */
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
    /**
     * Metoda która odpowiada za zjedzenie ofiary(pray) przez organizm. Ofiara ta umiera, a ten organizm zajmuje jej miejsce na planszy.
     * @param pray
     */
    @Override
    public void EatPray(ICreature pray) {
        int[] prayPos = map.GetCreaturePos(pray).clone();
        pray.Die();
        this.Move(prayPos);
        lastMealTime = 0;
    }
    /**
     * Metoda która pozwala na zjedzenie jedzenia, usunięcie obiektu jedzenia w przypadku jedzenia pojedyńczego użytku, zmiany statusu jedzenia wieloużytowego i przesunięcie organizmu na odpowiednie miejsce.
     * @param food obiekt najbliższego jedzenia.
     */
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
    /**
     * Metoda która poszukuje najbliższego jedzenia i zwaraca je w postaci IObject. Jeśli nie znajdzie jedzenia to zwraca ona null.
     * @return nearestFood/null
     */
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

    /**
     * Metoda która poszukuje najbliższego kompana i zwaraca go w postaci ICreature. Jeśli nie znajdzie kompana to zwraca ona null.
     * @return nearestAlly/null
     */
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
