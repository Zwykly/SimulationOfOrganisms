/**
 * Klasa dla obiektu organizmu mięsożernego
 */
public class Carnivore extends Creature implements ICarnivore {
    /**
     * Konstruktor tworzący obiekt organizmu mięsożernego.
     * @param map aktualna mapa
     * @param mobility zakres ruchu organizmu
     * @param deathTimer długość życia organizmu
     */
    public Carnivore(IMap map, int mobility, int deathTimer)
    {
        super(map, mobility, deathTimer);
    }

    /**
     * Metoda która decyduje o zachowaniu organizmu.
     */
    @Override
    public void DecideAction() {
        if(deathTimer==lastMealTime)
        {
            Die();
            return;
        }
        ICreature nearestPray = SearchForNearestPray();
        ICreature nearestAlly = NearestAlly();
        lastMealTime++;
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

    /**
     * Metoda która poszukuje najbliższej ofiary i zwaraca ją w postaci ICreature. Jeśli nie znajdzie ofiary to zwraca ona null.
     * @return nearestPray/null
     */
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
        level++;
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
