package InventoryComponents;

public class Inventory {

    Sword sword;
    Bow bow;
    Potion potion;

    int coins;

    public Inventory(Sword sword, Bow bow, Potion potion, int initialCoins){
        this.sword = sword;
        this.bow = bow;
        this.potion = potion;

        this.coins = initialCoins;
    }

    public Sword getSword() {
        return sword;
    }

    public Bow getBow() {
        return bow;
    }

    public Potion getPotion() {
        return potion;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
}
