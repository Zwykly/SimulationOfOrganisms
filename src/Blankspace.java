/**
Ta klasa odpowiada pustemu miejscu na mapie na którym nie może stać żaden obiekt ani organizm.
 */
public class Blankspace extends AObject
{
    /**
     * Konstruktor obiektu Blankspace
     * @param map aktualna mapa
     */
    public Blankspace (IMap map) { super(map); }

    /**
     * Metoda która byliśmy zmuszeni zaimplenetować ze względu na interfejs.
     * @return
     */
    @Override
    public boolean getEaten()
    {
        return false;
    }
    /**
     * Metoda która byliśmy zmuszeni zaimplenetować ze względu na interfejs.
     * @return
     */
    @Override
    public boolean getRegenerationStatus(IObject object) {
        return false;
    }
    /**
     * Metoda która byliśmy zmuszeni zaimplenetować ze względu na interfejs.
     * @return
     */
    @Override
    public boolean IsEdible() {
        return false;
    }
}
