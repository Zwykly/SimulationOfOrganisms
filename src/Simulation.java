import java.util.List;
import java.util.Random;

public class Simulation {

    private IMap map;
    static private Random rnd;

    private List<ICreature> creatureList;
    private List<IObject> objectList;

    public int time = 1;

    private int timeLimit; //needs to be changed when user input is considered


    public Simulation(IMapCreator mapCreator, ICreatureCreator creatureCreator, IObjectCreator objectCreator, long seed, int timeLimit) {
        this.timeLimit = timeLimit;
        this.map = mapCreator.CreateMap();
        this.rnd = new Random(seed);

        //putting creatures on the map
        this.creatureList = creatureCreator.CreateCreatures(map);
        for(int i = 0; i < creatureList.size(); i++)
        {
            int[] checkedpos = new int[2];
            do
            {
                checkedpos[0] = rnd.nextInt(map.GetSize());
                checkedpos[1] = rnd.nextInt(map.GetSize());
            } while (!map.SettleCreature(creatureList.get(i), checkedpos));
        }

        //putting objects on the map
        this.objectList = objectCreator.CreateObjects(map);
        for(int i = 0; i < objectList.size(); i++)
        {
            int[] checkedpos = new int[2];
                do
                {
                    checkedpos[0] = rnd.nextInt(map.GetSize());
                    checkedpos[1] = rnd.nextInt(map.GetSize());
                } while (!map.SettleObject(objectList.get(i), checkedpos));
        }
    }

    public void runSimulation() {
        System.out.flush();
        System.out.println("Time: "+time+"/"+timeLimit);
        map.PrintMap(map);
        System.out.println("Press Enter to continue");
        try{System.in.read();}
        catch(Exception e){}
        do{
            time++;
            creatureList.forEach(creature -> {if(map.GetCreaturePos(creature)!=null){creature.DecideAction();}});
            System.out.flush();
            System.out.println("Time: "+time+"/"+timeLimit);
            map.PrintMap(map);
            System.out.println("Press Enter to continue");
            try{System.in.read();}
            catch(Exception e){}
        } while(time<timeLimit);
    }

    public static void main(String[] args) {
        System.out.println("Main_Works"); //checking if main works

        MapSimpleCreator mapCreate = new MapSimpleCreator(20);
        CreatureCreator creatureCreate = new CreatureCreator();
        ObjectCreator objectCreator = new ObjectCreator();


        Simulation sim = new Simulation(mapCreate, creatureCreate, objectCreator, 123, 5); //simulation run needs to be changed


        sim.runSimulation();

    }
}
