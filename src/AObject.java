/**
* Ta klasa abstrakcyjna ma za zadanie zaimplentować metodę SetMap.
 */
public abstract class AObject implements IObject{
    protected IMap map;

    /**
     * Konstruktor klasy abstrakcyjnej obiektu
     * @param map aktualna mapa
     */
    public AObject(IMap map) { this.map = map; }
    /**
     * Setter aktualnej mapy w obiektcie
     * @param map aktualna mapa
     */
    @Override
    public void SetMap(IMap map) {
        this.map = map;
    }
}
