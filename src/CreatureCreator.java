import java.util.LinkedList;
import java.util.List;

public class CreatureCreator implements ICreatureCreator
{
    protected int numCarnivores;
    protected int numOmnivores;
    protected int numHerbivores;
    public CreatureCreator()
    {
        this.numCarnivores = 5;
        this.numOmnivores = 0;
        this.numHerbivores = 0;
    }
    public CreatureCreator(int numCarnivores, int numOmnivores, int numHerbivores)
    {
        this.numCarnivores = numCarnivores;
        this.numOmnivores = numOmnivores;
        this.numHerbivores = numHerbivores;
    }

    @Override
    public List<ICreature> CreateCreatures(IMap map)
    {
        List<ICreature> creatureList = new LinkedList<>();
        for(int i = 0; i<numCarnivores; i++)
        {
            creatureList.add(new Carnivore(map));
        }
        return creatureList;
    }
}
