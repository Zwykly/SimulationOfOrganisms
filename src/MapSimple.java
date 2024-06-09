import java.util.HashMap;
import java.util.Map;

public class MapSimple implements IMap {
    private int size;
    private Map<ICreature, int[]> creaturesPositions;
    private Map<IObject, int[]> objectsPositions;
    public MapSimple(int size)
    {
        this.size = size;
        creaturesPositions = new HashMap<>();
        objectsPositions = new HashMap<>();
    }
    @Override
    public int GetSize() {
        return size;
    }

    @Override
    public boolean SettleCreature(ICreature creature, int[] pos) {
        if (creaturesPositions.containsValue(pos)) {
            return false;
        }
        creature.SetMap(this);
        creaturesPositions.put(creature, pos);
        return true;
    }

    @Override
    public int[] GetCreaturePos(ICreature creature) {
        return creaturesPositions.get(creature);
    }

    // Methods to be completed and add methods for Objects
    @Override
    public boolean SettleObject(IObject object, int[] pos) {
        if(objectsPositions.containsValue(pos))
        {
            return false;
        }
        object.SetMap(this);
        objectsPositions.put(object, pos);
        return true;
    }

    // Deleting objects (eating food)
    public void DeleteObject(IObject object, int[] pos)
    {
        if(objectsPositions.containsValue(pos))
        {
        object.SetMap(this);
        objectsPositions.remove(object,pos);
        }

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

    // Method to get position of a given Object
    @Override
    public int[] GetObjectPos(IObject object) {
        return objectsPositions.get(object);
    }
      
    // Prints out the map
    @Override
    public void PrintMap(IMap map)
    {
        char[][] visibleMap = new char[size][size];
        for(int i = 0; i<size;i++)
        {
            for(int j = 0; j<size;j++) {
                visibleMap[i][j] = '_';
            }
        }
        creaturesPositions.forEach((k,v) -> {if(k instanceof Carnivore){visibleMap[v[0]][v[1]]='C';}});
        objectsPositions.forEach((k,v) -> {if(k instanceof Blankspace){visibleMap[v[0]][v[1]]='B';}});
        objectsPositions.forEach((k,v) -> {if(k instanceof OneUseFood){visibleMap[v[0]][v[1]]='F';}});
        objectsPositions.forEach((k,v) -> {if(k instanceof RenewableFood){visibleMap[v[0]][v[1]]='R';}});

        creaturesPositions.forEach((k,v) -> {if(k instanceof Carnivore){visibleMap[v[0]][v[1]]='C';} else if (k instanceof Herbivore) {
            visibleMap[v[0]][v[1]]='H';
        } else {
            visibleMap[v[0]][v[1]]='O';
        }
        });
        for(int i = 0; i<size;i++)
        {
            System.out.println(visibleMap[i]);
        }
    }

    @Override
    public void KillCreature(ICreature creature) {
        creaturesPositions.remove(creature);
    }
}
