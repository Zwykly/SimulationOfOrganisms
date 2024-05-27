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
    //Methodss to be completed and add methods for Objects
    @Override
    public ICreature GetCreature(int pos) {
        return creatures[pos];
    }

    @Override
    public boolean SettleCreature(ICreature creature, int[] pos) {
        return false;
    }

    @Override
    public int[] GetCreaturePos(ICreature creature) {
        return new int[0];
    }

    public void PrintMap()
    {

    }
}
