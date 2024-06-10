import java.util.Random;

// nothing can stand on it or move through it
public class Blankspace extends AObject
{
    public Blankspace (IMap map) { super(map); }

    @Override
    public boolean getEaten()
    {
        return false;
    }
    @Override
    public boolean getRegenerationStatus(IObject object) {
        return false;
    }

    @Override
    public boolean IsEdible() {
        return false;
    }
}
