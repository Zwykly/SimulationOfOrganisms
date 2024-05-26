import java.util.HashMap;

public class MapSimpleCreator implements IMapCreator {

    int mapSize;
    public MapSimpleCreator(int mapSize) {
        this.mapSize = mapSize;
    }
    @Override
    public HashMap createMap() {
        HashMap<Integer, String> TheMap = new HashMap<Integer, String>();
        for(int i = 0; i < mapSize; i++) {
            TheMap.put(i, "X");
        }
        return TheMap;
    }
}
