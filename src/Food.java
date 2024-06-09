public class Food extends AObject
{
    public Food (IMap map) { super(map); }

    public boolean getEaten(IObject object)
    {
        map.DeleteObject(object, map.GetObjectPos(object));
        return true;
    }
}
