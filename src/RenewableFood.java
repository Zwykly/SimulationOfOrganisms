public class RenewableFood extends Food
{
    public int regen_time = 0;
    public boolean isEdible = true;
    public RenewableFood(IMap map) { super(map); }

    // If regen_time is > 0 then the RenewableFood is still under regeneration
    @Override
    public boolean getRegenerationStatus(IObject object)
    {
        if(regen_time > 0)
        {
            regen_time = regen_time - 1;
            return false;
        }
        isEdible = true;
        return true;
    }

    @Override
    public boolean getEaten()
    {
        isEdible = false;
        regen_time = 3;
        return true;
    }
}
