public interface IHerbivore
{
    public boolean IsNearFood();
    public int[] NearestFoodPos();
    public void EatFood(int[] pos);
}
