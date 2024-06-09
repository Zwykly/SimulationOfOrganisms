public class Food extends AObject
{
    public Food (IMap map) { super(map); }

    @Override
    public boolean getEaten()
    {
        map.DeleteObject(this, map.GetObjectPos(this));
        return true;
    }
}
