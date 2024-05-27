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
        this.map = mapCreator.createMap();
        this.rnd = new Random(seed);
        System.out.println("Simulation_Ran_Correctly");
        System.out.print(FinalMap.toString());
    }

    public void runSimulation() {
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("Main_Works"); //checking if main works

        MapSimpleCreator mapCreate = new MapSimpleCreator(20);

        Simulation sim = new Simulation(mapCreate); //simulation run needs to be changed

        sim.runSimulation();

    }
}
