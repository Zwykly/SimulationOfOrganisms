/**
 * Ta klasa odpowiada pojedyńczemu jedzeniu na mapie na którym nie może stać żaden obiekt ani organizm, a niektóre organizmy mogą go zjeść.
 */
public class OneUseFood extends Food
{
    /**
     * Konstruktor tworzący obiekt OneUseFood.
     * @param map mapa do której przypisany jest obiekt.
     */
    public OneUseFood(IMap map) { super(map); this.isEdible=true; }
}
