package Entities.Merchants;

import Enums.MerchantItems;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public abstract class Merchant {

    protected String name;

    protected List<MerchantItems> availableItems;
    protected Map<MerchantItems, Integer> pricedItems;

    public Merchant(String name, MerchantItems... items){
        this.name = name;
        this.availableItems = Arrays.asList(items);
    }

    public abstract void mapPrices();
}
