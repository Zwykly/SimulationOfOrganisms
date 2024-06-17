/**
 * Interfejs który inicuje metody dla klas Carnivore oraz Omnivore.
 */
public interface ICarnivore {
    public ICreature SearchForNearestPray();
    public void EatPray(ICreature pray);
}
