import java.util.HashMap;
import java.util.Map;

public class MapSimple implements IMap {
    private int size;
    private ICreature[] creatures;
    private Map<ICreature, int[]> creaturesPositions;
    // TO DO: private IObject[] objects;
    //TO IMPLEMENT: private Map<IObject, int[]> objectsPositions;
    public MapSimple(int size)
    {
        this.size = size;
        creatures = new ICreature[100];
        creaturesPositions = new HashMap<>();
    }

    public int GetSize() {
        return size;
    }
    //Methods to be completed and add methods for Objects
    @Override
    public ICreature GetCreature(int pos) {
        return creatures[pos];
    }

    @Override
    public boolean SettleCreature(ICreature creature, int[] pos) {
        if (creaturesPositions.containsValue(pos)) {
            return false;
        }
        creature.SetMap(this);
        creatures[pos[0]] = creature;
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
    public void PrintMap()
    {

    }
}
