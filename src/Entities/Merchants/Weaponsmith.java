package Entities.Merchants;

import Entities.Merchants.Merchant;
import Enums.MerchantItems;

public class Weaponsmith extends Merchant {



    public Weaponsmith(String name, MerchantItems... items){
        super(name, items);
    }

    @Override
    public void mapPrices() {

    }
}
