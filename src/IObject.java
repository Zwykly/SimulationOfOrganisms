/**
 * Interfejs który inicuje metody dla klasy abstarkcyjnej AObject.
 */
public interface IObject
{
    public void SetMap(IMap map);
    public boolean getEaten();
    public boolean getRegenerationStatus(IObject object);
    public boolean IsEdible();
}
