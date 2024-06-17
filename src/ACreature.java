/**
* Ta klasa abstrakcyjna ma za zadanie zaimplentować metody SetMap oraz GetMap.
 */
public abstract class ACreature implements ICreature
{
    protected IMap map;
    public ACreature(IMap map) {
        this.map = map;
    }

    /**
     * Getter mapy przypisanej do organizmu
     * @return map - aktualna mapa
     */
    @Override
    public IMap GetMap() {
        return map;
    }
    /**
     * Setter mapy przypisanej do organizmu
     * @param map aktualna mapa
     */
    @Override
    public void SetMap(IMap map) {
        this.map = map;
    }
}
