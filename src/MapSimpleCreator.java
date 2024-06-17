import java.util.HashMap;

/**
 * Klasa dla kreatora obiektu MapSimple.
 */
public class MapSimpleCreator implements IMapCreator {
    int mapSize;

    /**
     * Konstruktor tworzący obiekt MapSimpleCreator z podanym rozmiarem mapy.
     * @param mapSize wielkość mapy.
     */
    public MapSimpleCreator(int mapSize) {
        this.mapSize = mapSize;
    }

    /**
     * Metoda która tworzy i zwraca mapę o danym rozmiarze.
     * @return IMap - mapa na której odbywa się symulacja.
     */
    @Override
    public IMap CreateMap() {
        return new MapSimple(mapSize);
    }
}
