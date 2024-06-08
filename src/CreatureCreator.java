import java.util.LinkedList;
import java.util.List;

public class CreatureCreator implements ICreatureCreator
{
    protected int numCarnivores;
    protected int carnivoreMobility;
    protected int carnivoreDeathTimer;
    protected int numOmnivores;
    protected int herbivoreMobility;
    protected int herbivoreDeathTimer;
    protected int numHerbivores;
    public CreatureCreator()
    {
        this.numCarnivores = 1;
        this.carnivoreMobility = 5;
        this.carnivoreDeathTimer = 5;
        this.numOmnivores = 0;
        this.numHerbivores = 1;
        this.herbivoreMobility = 2;
        this.herbivoreDeathTimer = 5;
    }
    public CreatureCreator(int numCarnivores,int carnivoreMobility,int carnivoreDeathTimer, int numOmnivores, int numHerbivores, int herbivoreMobility, int herbivoreDeathTimer)
    {
        this.numCarnivores = numCarnivores;
        this.carnivoreMobility = carnivoreMobility;
        this.carnivoreDeathTimer = carnivoreDeathTimer;
        this.numOmnivores = numOmnivores;
        this.herbivoreMobility = herbivoreMobility;
        this.herbivoreDeathTimer = herbivoreDeathTimer;
        this.numHerbivores = numHerbivores;
    }

    @Override
    public List<ICreature> CreateCreatures(IMap map)
    {
        List<ICreature> creatureList = new LinkedList<>();
        for(int i = 0; i<numCarnivores; i++)
        {
            creatureList.add(new Carnivore(map, carnivoreMobility, carnivoreDeathTimer));
        }
        for(int i = 0; i<numCarnivores; i++)
        {
            creatureList.add(new Herbivore(map, herbivoreMobility, herbivoreDeathTimer));
        }
        return creatureList;
    }
}
