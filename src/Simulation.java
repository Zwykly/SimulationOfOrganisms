import java.util.List;
import java.util.Random;
import java.util.Scanner;

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
        System.out.println();
        do{
            time++;
            objectList.forEach(object -> {if(object instanceof RenewableFood){object.getRegenerationStatus(object);}});
            creatureList.forEach(creature -> {if(map.GetCreaturePos(creature)!=null){creature.DecideAction();}});
            System.out.flush();
            System.out.println("Time: "+time+"/"+timeLimit);
            System.out.println("Number of creatures: "+creatureList.size());
            map.PrintMap(map);
            System.out.println();
        } while(time<timeLimit);
    }

    public static void main(String[] args) {
        System.out.println("Main_Works"); //checking if main works
        int mapSize = 0;
        int simSeed = 0;
        int simTime = 0;
        int numHerbivores = 0;
        int herbivoreDeathTimer = 0;
        int herbivoreMobility = 0;
        int numOmnivores = 0;
        int omnivoreDeathTimer = 0;
        int omnivoreMobility = 0;
        int numCarnivores = 0;
        int carnivoreDeathTimer = 0;
        int carnivoreMobility = 0;
        int numBlankspace = 0;
        int numSingleUseFoods=0;
        int numRenewableFoods=0;
        Scanner s = new Scanner(System.in);

        do {
            do {
                System.out.println("Podaj wielkość mapy:");
                mapSize = s.nextInt();
                if (mapSize < 0) {
                    System.out.println("Niepoprawny rozmiar mapy");
                }
            }   while(mapSize <= 0);

            System.out.println("Podaj seed symulacji:");
            simSeed = s.nextInt();
            do {
                System.out.println("Podaj długość symulacji:");
                simTime = s.nextInt();
                if (simTime < 0) {
                    System.out.println("Za malo czasu");
                    break;
                }
            } while(simTime <= 0);
            do
            {
                System.out.println("Podaj ilość roślinożerców:");
                numHerbivores = s.nextInt();
                if(numHerbivores < 0)
                {
                    System.out.println("Niepoprawna ilość roślinożerców");
                }
            } while(numHerbivores < 0);

            if(numHerbivores > 0)
            {
                System.out.println("Podaj ile pól mogą poruszać się roślinożercy:");
                herbivoreMobility = s.nextInt();
                System.out.println("Podaj jak długo mają żyć roślinożercy:");
                herbivoreDeathTimer = s.nextInt();
            }

            do {

                System.out.println("Podaj ilość wszystkożerców:");
                numOmnivores = s.nextInt();
                if(numOmnivores < 0)
                {
                    System.out.println("Niepoprawna ilość wszystkożerców");
                }
            } while(numOmnivores < 0);

            if(numOmnivores > 0) {
                System.out.println("Podaj ile pól mogą poruszać się wszystkożercy:");
                omnivoreMobility = s.nextInt();
                System.out.println("Podaj jak długo mają żyć wszystkożercy:");
                omnivoreDeathTimer = s.nextInt();
            }

            do {

                System.out.println("Podaj ilość mięsożerców:");
                numCarnivores = s.nextInt();
                if(numCarnivores < 0)
                {
                    System.out.println("Niepoprawna ilość miesożerców");
                }
            } while(numCarnivores < 0);

            if(numCarnivores > 0) {
                System.out.println("Podaj ile pól mogą poruszać się miesożercy:");
                carnivoreMobility = s.nextInt();
                System.out.println("Podaj jak długo mają żyć miesożercy:");
                carnivoreDeathTimer = s.nextInt();
            }

            do {
                System.out.println("Podaj ilość pustych miejsc(na których nic nie może stać):");
                numBlankspace = s.nextInt();
                if(numBlankspace < 0) {
                    System.out.println("Niepoprawna ilosc pustych miejsc");
                }
            }
            while(numBlankspace < 0);


            do {
                System.out.println("Podaj ilość odnawialnego jedzenia:");
                numRenewableFoods = s.nextInt();
                if(numRenewableFoods < 0) {
                    System.out.println("Niepoprawna ilosc odnawialnego jedzenia");
                }
            }
            while(numRenewableFoods < 0);

            do {
                System.out.println("Podaj ilość jedzenia pojedyńczego użytku:");
                numSingleUseFoods = s.nextInt();
                if(numSingleUseFoods < 0) {
                    System.out.println("Niepoprawna ilosc jedzenia pojedyńczego użytku");
                }
            }
            while(numSingleUseFoods < 0);

        } while (mapSize==0 || simTime==0 || (mapSize*mapSize)<=numBlankspace+numRenewableFoods+numSingleUseFoods+numCarnivores+numHerbivores+numHerbivores);
        MapSimpleCreator mapCreate = new MapSimpleCreator(mapSize);
        CreatureCreator creatureCreate = new CreatureCreator(numCarnivores,carnivoreMobility,carnivoreDeathTimer,numOmnivores,omnivoreMobility,omnivoreDeathTimer,numHerbivores,herbivoreMobility,herbivoreDeathTimer);
        ObjectCreator objectCreator = new ObjectCreator(numRenewableFoods, numSingleUseFoods, numBlankspace);


        Simulation sim = new Simulation(mapCreate, creatureCreate, objectCreator, simSeed, simTime); //simulation run needs to be changed


        sim.runSimulation();

    }
}
