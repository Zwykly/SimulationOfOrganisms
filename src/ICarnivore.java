public interface ICarnivore {
    public boolean IsNearPray();
    public ICreature SearchForNearestPray();
    public void EatPray(ICreature pray);
}
