package Entities.Merchants;

import Entities.Merchants.Merchant;
import Enums.MerchantItems;

public class Fletcher extends Merchant {

    public Fletcher(String name, MerchantItems... items){
        super(name, items);
    }

    @Override
    public void mapPrices() {

    }
}
