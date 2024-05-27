import java.util.HashMap;

public class MapSimpleCreator implements IMapCreator {

    int mapSize;
    public MapSimpleCreator(int mapSize) {
        this.mapSize = mapSize;
    }
    @Override
    public IMap CreateMap() {
        return new MapSimple(mapSize);
    }
}
