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
    public int[] GetObjectPos(IObject object) {
        return new int[0];
    }

    @Override
    // Checks if you can put object on given space
    public boolean SettleObject(IObject object, int[] pos) {
        if(objectsPositions.containsValue(pos)) {
            return false;
        }
        object.SetMap(this);
        objectsPositions.put(object, pos);
        return true;
    }

    // Prints out the map
    @Override
    public void PrintMap(IMap map)
    {
        char[][] visibleMap = new char[size][size];
        for(int i = 0; i<size;i++)
        {
            for(int j = 0; j<size;j++) {
                visibleMap[i][j] = ' ';
            }
        }
        creaturesPositions.forEach((k,v) -> {if(k instanceof Carnivore){visibleMap[v[0]][v[1]]='C';}});
        for(int i = 0; i<size;i++)
        {
            System.out.println(visibleMap[i]);
        }
    }
}
