package FuzzyLogic.FuzzyMachines;

import Entities.Player;
import net.sourceforge.jFuzzyLogic.FIS; //Fuzzy inference system
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class PotionmasterDesirability {

    public static double calculateDesirability(Player player){
        String fileName = "src/FuzzyLogic/rules/VendedorPorcoes.fcl";

        FIS fuzzyInferenceSystem = FIS.load(fileName, true);

        if (fuzzyInferenceSystem == null){
            System.err.println("Can't load file: '" + fileName + "'");
            return 0;
        }

        FunctionBlock functionBlock = fuzzyInferenceSystem.getFunctionBlock("VendedorPocoes");

//        System.out.println(functionBlock);

        // Show
//        JFuzzyChart.get().chart(functionBlock);

        // Set inputs
        fuzzyInferenceSystem.setVariable("quantidade_porcoes_jogador", player.getInventory().getPotion().getCharges());
//        fuzzyInferenceSystem.setVariable("food", 7);

        // Evaluate
        fuzzyInferenceSystem.evaluate();

        // Show output variable's chart
        Variable chanceAparecerNpc = functionBlock.getVariable("chance_aparecer_npc");
//        JFuzzyChart.get().chart(chanceAparecerNpc, chanceAparecerNpc.getDefuzzifier(), true);

        return chanceAparecerNpc.getValue();
//        // Print ruleSet
//        System.out.println(fuzzyInferenceSystem);
    }
}
