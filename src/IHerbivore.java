/**
 * Interfejs który inicuje metody dla klas Omnivore i Herbivore.
 */
public interface IHerbivore
{
    public void EatFood(IObject food);
    public IObject SearchForNearestFood();
}
