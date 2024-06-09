public class RenewableFood extends Food
{
    public int regen_time = 0;
    public RenewableFood(IMap map) { super(map); }

    // If regen_time is > 0 then the RenewableFood is still under regeneration
    public boolean getRegenerationStatus(IObject object)
    {
        if(regen_time > 0)
        {
            regen_time = regen_time - 1;
            return false;
        }
        return true;
    }

    @Override
    public boolean getEaten()
    {
        if(getRegenerationStatus(this))
        {
            regen_time = 2;
            return true;
        }
        return false;
    }
}
