import Entities.Player;
import FuzzyLogic.FuzzyMachines.FletcherTax;
import FuzzyLogic.FuzzyMachines.PotionmasterTax;
import FuzzyLogic.FuzzyMachines.WeaponsmithTax;
import States.EndlessDungeon;
import States.Over;

public class Main {

    public static void main(String[] args) throws Exception {
        EndlessDungeon fsm = new EndlessDungeon(new Player(10, 20));


        while(!(fsm.getCurrentState() instanceof Over)){
            fsm.update();
        }

    }
}
