package Entities.Merchants;

import Entities.Player;
import Enums.MerchantItems;
import FuzzyLogic.FuzzyMachines.PotionmasterTax;

public class Potionmaster extends Merchant{



    public Potionmaster(String name, MerchantItems... items) {
        super(name, items);
    }

    @Override
    public void mapPrices(Player player) {
        setTax(player);
        for (MerchantItems item : availableItems){
            double finalTax = (1 +tax);
            pricedItems.put(item, (int) (item.getItemValue()*finalTax));
        }
    }

    @Override
    public void setTax(Player player) {
        // TODO: call fuzzy logic for potion inference
//        tax = Math.random();
        tax = PotionmasterTax.calculateTax(player);
    }
}
