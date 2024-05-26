import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class Simulation {

    public int time = 0;
    private final int timeLimit = 5; //needs to be changed when user input is considered

    List<String> CreatureList = new ArrayList<String>(); //change type to ICreature
    List<String> ObjectList = new ArrayList<String>(); //change type to IObject

    public Simulation(IMapCreator mapCreator) {
        HashMap FinalMap = mapCreator.createMap();
        System.out.println("Simulation_Ran_Correctly");
        System.out.print(FinalMap.toString());
    }

    public void runSimulation() {
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("Main_Works"); //checking if main works

        MapSimpleCreator mapCreat = new MapSimpleCreator(20);

        Simulation sim = new Simulation(mapCreat); //simulation run needs to be changed
        sim.CreatureList.add("AAA");
        sim.CreatureList.add("BBB");
        sim.runSimulation();

    }
}
