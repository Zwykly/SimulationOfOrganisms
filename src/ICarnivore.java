public interface ICarnivore {
    public boolean IsNearPray();
    public int[] NearestPrayPos();
    public void EatPray(int[] pos);
}
