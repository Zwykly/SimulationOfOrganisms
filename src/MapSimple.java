import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MapSimple implements IMap {
    private int size;
    private Map<ICreature, int[]> creaturesPositions;
    // TO DO: private IObject[] objects;
    //TO IMPLEMENT: private Map<IObject, int[]> objectsPositions;
    public MapSimple(int size)
    {
        this.size = size;
        creaturesPositions = new HashMap<>();
    }
    @Override
    public int GetSize() {
        return size;
    }
    //Methods to be completed and add methods for Objects
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
        return new int[0];
    }

    @Override
    public IObject GetObject(int pos) {
        return null;
    }

    @Override
    public int[] GetObjectPos(IObject object) {
        return new int[0];
    }

    @Override
    public void PrintMap(IMap map)
    {
        System.out.println(creaturesPositions);
    }
}
