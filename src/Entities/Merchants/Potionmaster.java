package Entities.Merchants;

import Enums.MerchantItems;

public class Potionmaster extends Merchant{


    public Potionmaster(String name, MerchantItems... items) {
        super(name, items);
    }

    @Override
    public void mapPrices() {

    }
}
