import Entities.Player;
import States.*;

public class Main {

    public static void main(String[] args){
//        new DungeonStates(new Player(10, 20));
        EndlessDungeon fsm = new EndlessDungeon(new Player(10, 20));
//        State<EndlessDungeon> state = Battle.getInstance();

        while(!(fsm.getCurrentState() instanceof Over)){
            fsm.update();
        }
    }
}
