import java.io.PipedOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Klasa dla obiektu mapy, która przechowuje HashMapy obiektów i ich pozycji, organizmów i ich pozycji oraz oraz swoją wielkość.
 */
public class MapSimple implements IMap {
    private int size;
    private Map<ICreature, int[]> creaturesPositions;
    private Map<IObject, int[]> objectsPositions;

    /**
     * Konstruktor tworzący obiekt MapSimple o podanym rozmiarze.
     * @param size
     */
    public MapSimple(int size)
    {
        this.size = size;
        creaturesPositions = new HashMap<>();
        objectsPositions = new HashMap<>();
    }

    /**
     * Getter wielkości mapy
     * @return size - Podaje wielkość mapy jako int
     */
    @Override
    public int GetSize() {
        return size;
    }

    /**
     * Metoda która sprawdza czy dane miejsce jest wolne i stawia dany obiekt organizmu w odpowiednie miejsce na planszy.
     * @param creature organizm który chcemy przemieścić.
     * @param pos pozycja na którą chcemy przemieścić organizm.
     * @return boolean - oznaczajacy pozytywne przeniesienie organizmu.
     */
    @Override
    public boolean SettleCreature(ICreature creature, int[] pos)
    {
        if (GetCreatureByPos(pos) != null || GetObjectByPos(pos) != null)
        {
            return false;
        }
        creaturesPositions.put(creature, pos);
        creature.SetMap(this);
        return true;
    }

    /**
     * Getter aktualnej pozycji danego organizmu na mapie.
     * @param creature dany organizm
     * @return int[] - pozycja organizmu na mapie
     */
    @Override
    public int[] GetCreaturePos(ICreature creature) {
        return creaturesPositions.get(creature);
    }

    /**
     * Metoda umieszczającą dany obiekt na mapie.
     * @param object dany obiekt
     * @param pos pozycja na którym ma być umieszczony.
     * @return boolean - oznaczający pozytywne wykonanie zadania metody.
     */
    @Override
    public boolean SettleObject(IObject object, int[] pos)
    {
        if(GetCreatureByPos(pos) != null || GetObjectByPos(pos) != null)
        {
            return false;
        }
        object.SetMap(this);
        objectsPositions.put(object, pos);
        return true;
    }

    /**
     * Metoda która zwraca dany obiekt po jego pozycji na mapie. Jeśli obiekt o tej pozycji nie istnieje to zwraca ona null.
     * @param pos pozycja obiektu
     * @return null/IObject
     */
    @Override
    public IObject GetObjectByPos(int[] pos) {
        for(Map.Entry<IObject, int[]> entry: objectsPositions.entrySet())
        {
            if(Arrays.equals(entry.getValue(),pos))
            {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * Metoda która usuwa dany obiekt z hashMapy.
     * @param object obiekt który chcemy usunąć.
     * @param pos pozycja obiektu który chcemy usunąć.
     */
    public void DeleteObject(IObject object, int[] pos)
    {
        if (objectsPositions.containsValue(pos)) {
            object.SetMap(this);
            objectsPositions.remove(object, pos);
        }
    }

    /**
     * Metoda która zwraca dany organizm po jego pozycji na mapie. Jeśli organizm o tej pozycji nie istnieje to zwraca ona null.
     * @param pos pozycja organzimu
     * @return null/ICreature
     */
    public ICreature GetCreatureByPos(int[] pos) {
        for(Map.Entry<ICreature, int[]> entry: creaturesPositions.entrySet())
        {
            if(Arrays.equals(entry.getValue(),pos))
            {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * Zwraca pozycje na mapie danego obiektu.
     * @param object obiekt którego pozycje chcemy dostać.
     * @return int[] - pozycja na mapie danego obiektu.
     */
    @Override
    public int[] GetObjectPos(IObject object) {
        return objectsPositions.get(object);
    }

    /**
     * Metoda która zwraca String z aktualnym statusem mapy.
     * @param map obiekt mapy.
     * @return shownMap - aktualny stan mapy
     */
    @Override
    public String PrintMap(IMap map)
    {
        char[][] visibleMap = new char[size][size];
        for(int i = 0; i<size;i++)
        {
            for(int j = 0; j<size;j++) {
                visibleMap[i][j] = '_';
            }
        }
        objectsPositions.forEach((k,v) -> {
            if(k instanceof RenewableFood)
            {
                if(k.IsEdible())
                {
                    visibleMap[v[0]][v[1]]='R';
                } else {
                    visibleMap[v[0]][v[1]]='r';
                }
            } else if (k instanceof OneUseFood) {
                visibleMap[v[0]][v[1]]='F';
            }
            else if (k instanceof Blankspace) {
                visibleMap[v[0]][v[1]]='B';
            }
        });

        creaturesPositions.forEach((k,v) -> {if(k instanceof Carnivore){visibleMap[v[0]][v[1]]='C';} else if (k instanceof Herbivore) {
            visibleMap[v[0]][v[1]]='H';
        } else {
            visibleMap[v[0]][v[1]]='O';
        }
        });
        String shownMap = "";
        for(int i = 0; i<size;i++)
        {
            String mapLine = new String(visibleMap[i].clone());
            shownMap += mapLine+"\n";
        }
        return shownMap;
    }

    /**
     * Metoda która usuwa organizm z hashMapy organzmów i ich pozycji.
     * @param creature organizm który chemy usunąć.
     */
    @Override
    public void KillCreature(ICreature creature) {
        creaturesPositions.remove(creature);
    }
}
