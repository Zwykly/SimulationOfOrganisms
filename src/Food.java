/**
 * Klasa z której dziedziczą obiekty jedzenia.
 */
public class Food extends AObject
{
    /**
     * Konstruktor przypisujący obiektowi mapę.
     * @param map mapa do której przypisany jest obiekt.
     */
    public Food (IMap map) { super(map); this.isEdible=true;}

    public boolean isEdible;

    /**
     * Getter dla zmiennej isEdbile.
     * @return boolean - zmienna mówiąca czy obiekt jedzenia jest możliwy do zjedzenia.
     */
    @Override
    public boolean IsEdible() {
        if(isEdible)
        {
            return true;
        }
        return false;
    }

    /**
     * Metoda która usuwa obiekt z HashMapy obiektów i ich pozycji.
     * @return boolean - potwierzdzający usunięcie obiektu.
     */
    @Override
    public boolean getEaten()
    {
        map.DeleteObject(this, map.GetObjectPos(this));
        return true;
    }

    /**
     * Metoda ta w tym miejscu nic nie robi i została zaimplenetowana tylko ze względu na interfejs.
     * @param object
     * @return zawsze zwraca false
     */
    @Override
    public boolean getRegenerationStatus(IObject object) {
        return false;
    }
}
