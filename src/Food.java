public class Food extends AObject
{
    public Food (IMap map) { super(map); this.isEdible=true;}

    public boolean isEdible;
    @Override
    public boolean IsEdible() {
        if(isEdible)
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean getEaten()
    {
        map.DeleteObject(this, map.GetObjectPos(this));
        return true;
    }
    @Override
    public boolean getRegenerationStatus(IObject object) {
        return false;
    }
}
