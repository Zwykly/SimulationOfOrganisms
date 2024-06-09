import java.util.LinkedList;
import java.util.List;

public class CreatureCreator implements ICreatureCreator
{
    protected int numCarnivores;
    protected int carnivoreMobility;
    protected int carnivoreDeathTimer;
    protected int numOmnivores;
    protected int omnivoreMobility;
    protected int omnivoreDeathTimer;
    protected int numHerbivores;
    protected int herbivoreMobility;
    protected int herbivoreDeathTimer;
    public CreatureCreator()
    {
        this.numCarnivores = 4;
        this.carnivoreMobility = 5;
        this.carnivoreDeathTimer = 5;
        this.numOmnivores = 2;
        this.omnivoreMobility = 2;
        this.omnivoreDeathTimer = 5;
        this.numHerbivores = 3;
        this.herbivoreMobility = 2;
        this.herbivoreDeathTimer = 5;
    }
    public CreatureCreator(int numCarnivores,int carnivoreMobility,int carnivoreDeathTimer, int numOmnivores,int omnivoreMobility,int omnivoreDeathTimer, int numHerbivores, int herbivoreMobility, int herbivoreDeathTimer)
    {
        this.numCarnivores = numCarnivores;
        this.carnivoreMobility = carnivoreMobility;
        this.carnivoreDeathTimer = carnivoreDeathTimer;
        this.numOmnivores = numOmnivores;
        this.omnivoreMobility = omnivoreMobility;
        this.omnivoreDeathTimer = omnivoreDeathTimer;
        this.numHerbivores = numHerbivores;
        this.herbivoreMobility = herbivoreMobility;
        this.herbivoreDeathTimer = herbivoreDeathTimer;
    }

    @Override
    public List<ICreature> CreateCreatures(IMap map)
    {
        List<ICreature> creatureList = new LinkedList<>();
        for(int i = 0; i<numCarnivores; i++)
        {
            creatureList.add(new Carnivore(map, carnivoreMobility, carnivoreDeathTimer));
        }
        for(int i = 0; i<numOmnivores; i++)
        {
            creatureList.add(new Omnivore(map, omnivoreMobility, omnivoreDeathTimer));
        }
        for(int i = 0; i<numHerbivores; i++)
        {
            creatureList.add(new Herbivore(map, herbivoreMobility, herbivoreDeathTimer));
        }
        return creatureList;
    }
}
