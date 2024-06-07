public class Food extends AObject
{
    public Food (IMap map) { super(map); }

    public void getEaten(IObject object)
    {
        map.DeleteObject(object, map.GetObjectPos(object));
    }
}
