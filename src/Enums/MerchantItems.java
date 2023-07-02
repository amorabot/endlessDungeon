package Enums;

public enum MerchantItems {

    NEW_QUIVER(40), // 10x
    ARROW_BUNDLE(20), // 5x
    ARROW(5), // 1x

    SHARPENING_WHETSTONE(15), // 40%
    POISON_VIAL(70), // More base damage

    POTION(12), // 1x
    POTION_BAG(55); // 5x

    private final int itemValue;

    MerchantItems(int value){
        this.itemValue = value;
    }

    public int getItemValue() {
        return itemValue;
    }
}
