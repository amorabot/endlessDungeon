package FuzzyLogic.FuzzyMachines;

import Entities.Player;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class FletcherTax {

    public static double calculateTax(Player player){
        String fileName = "src/FuzzyLogic/rules/LogistaFlechas.fcl";

        FIS fuzzyInferenceSystem = FIS.load(fileName, true);

        if (fuzzyInferenceSystem == null){
            System.err.println("Can't load file: '" + fileName + "'");
            return 0;
        }

        FunctionBlock functionBlock = fuzzyInferenceSystem.getFunctionBlock("lojistaflechas");

//        System.out.println(functionBlock);

        // Show
//        JFuzzyChart.get().chart(functionBlock);

        // Set inputs
        fuzzyInferenceSystem.setVariable("quantidade_flechas", player.getInventory().getBow().getArrows());

        // Evaluate
        fuzzyInferenceSystem.evaluate();

        // Show output variable's chart
        Variable chanceAparecerNpc = functionBlock.getVariable("preco_venda");
//        JFuzzyChart.get().chart(chanceAparecerNpc, chanceAparecerNpc.getDefuzzifier(), true);

        return 1 - (chanceAparecerNpc.getValue()/100);
    }
}
