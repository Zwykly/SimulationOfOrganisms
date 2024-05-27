import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Simulation {

    private IMap map;
    private Random rnd;
    private List<ICreature> creatureList;

    //Currently not made: private List<IObject> objectList;
    public int time = 1;
    private int timeLimit; //needs to be changed when user input is considered


    public Simulation(IMapCreator mapCreator, ICreatureCreator creatureCreator, long seed, int timeLimit) {
        this.timeLimit = timeLimit;
        this.map = mapCreator.CreateMap();
        this.rnd = new Random(seed);
        this.creatureList = creatureCreator.CreateCreatures(map);
        for(int i=0; i<creatureList.size(); i++) {
            int[] checkedpos = new int[2];
            do {
                checkedpos[0] = rnd.nextInt(map.GetSize());
                checkedpos[1] = rnd.nextInt(map.GetSize());
            } while (!map.SettleCreature(creatureList.get(i), checkedpos));
        }
    }

    public void runSimulation() {
        map.PrintMap(map);
        do{
            creatureList.forEach(creature -> creature.DecideAction());
        } while(time<=timeLimit);
    }

    public static void main(String[] args) {
        System.out.println("Main_Works"); //checking if main works

        MapSimpleCreator mapCreate = new MapSimpleCreator(20);
        CreatureCreator creatureCreate = new CreatureCreator();

        Simulation sim = new Simulation(mapCreate, creatureCreate, 123, 5); //simulation run needs to be changed

        sim.runSimulation();

    }
}
