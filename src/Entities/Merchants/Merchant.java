package Entities.Merchants;

import Entities.Player;
import Enums.MerchantItems;

import java.util.*;

public abstract class Merchant {

    protected String name;

    protected double tax;

    protected List<MerchantItems> availableItems;
    protected Map<MerchantItems, Integer> pricedItems = new HashMap<>();

    public Merchant(String name, MerchantItems... items){
        this.name = name;
        this.availableItems = Arrays.asList(items);
    }

    public abstract void mapPrices(Player player);
    public abstract void setTax(Player player);

    public String getName() {
        return name;
    }

    public List<MerchantItems> getAvailableItems() {
        return availableItems;
    }

    public Map<MerchantItems, Integer> getPricedItems() {
        return pricedItems;
    }
}
