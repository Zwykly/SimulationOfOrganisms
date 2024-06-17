/**
 * Ta klasa odpowiada odnawialnemu jedzeniu na mapie na którym nie może stać żaden obiekt ani organizm, a niektóre organizmy mogą go zjeść. Jedzenie to odnawia się z czasem symulacji.
 */
public class RenewableFood extends Food
{
    public int regen_time;

    /**
     * Konstruktor tworzący obiekt RenewableFood.
     * @param map mapa do której przypisany jest obiekt.
     */
    public RenewableFood(IMap map) { super(map); this.regen_time=0;}

    /**
     * Metoda odnawaia status odnawialnego jedzenia, lub zmniejsza czas za który ma się on odnowić.
     * @param object obiekt którego status jest sprawdzany.
     * @return boolean - oznacza stan odnowienia jedzenia.
     */
    @Override
    public boolean getRegenerationStatus(IObject object)
    {
        if(regen_time > 0)
        {
            regen_time = regen_time - 1;
            return false;
        }
        isEdible = true;
        return true;
    }

    /**
     * Metoda która zmienia stan odnawialnego jedzenia na zjedzone.
     * @return
     */
    @Override
    public boolean getEaten()
    {
        isEdible = false;
        regen_time = 3;
        return true;
    }
}
