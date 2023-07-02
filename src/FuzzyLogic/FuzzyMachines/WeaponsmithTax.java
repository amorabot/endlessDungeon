package FuzzyLogic.FuzzyMachines;

import Entities.Player;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class WeaponsmithTax {
    public static double calculateTax(Player player){
        String fileName = "src/FuzzyLogic/rules/espada.fcl";

        FIS fuzzyInferenceSystem = FIS.load(fileName, true);

        if (fuzzyInferenceSystem == null){
            System.err.println("Can't load file: '" + fileName + "'");
            return 0;
        }

        FunctionBlock functionBlock = fuzzyInferenceSystem.getFunctionBlock("espada");

//        System.out.println(functionBlock);

        // Show
//        JFuzzyChart.get().chart(functionBlock);

        // Set inputs
        fuzzyInferenceSystem.setVariable("durabilidade_espada", player.getInventory().getSword().getDurability());

        // Evaluate
        fuzzyInferenceSystem.evaluate();

        // Show output variable's chart
        Variable chanceAparecerNpc = functionBlock.getVariable("valor_ferreiro");
//        JFuzzyChart.get().chart(chanceAparecerNpc, chanceAparecerNpc.getDefuzzifier(), true);

        return chanceAparecerNpc.getValue()/1000;
    }
}
