package Entities;

import Enums.DamageTypes;
import InventoryComponents.*;

public class Player {

    private boolean alive = true;

    private int health;
    private final int maxHealth;

    private final Inventory inventory;

    public Player(int maxHealth, int initialCoins){
        this.maxHealth = maxHealth;
        this.health = maxHealth;

        Sword sword = new Sword(new int[]{1,3}, 30, DamageTypes.SLASHING);
        Bow bow = new Bow(2, 3, 12, DamageTypes.PIERCING);
        Potion potion = new Potion(1, 10);

        this.inventory = new Inventory(sword, bow, potion, initialCoins);
    }

    public <T extends Monster> void attack(T enemy, Weapon weapon){
        enemy.takeDamage(this, weapon);
    }
    public <T extends Monster> void takeDamage(T enemy){
        if (getHealth() - enemy.getDamage() <= 0){
            setHealth(0);
            alive = false;
            return;
        }
        setHealth(getHealth()-enemy.getDamage());
    }
    public void heal(){
        Potion potion = inventory.getPotion();
        potion.use(this);
    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public Inventory getInventory() {
        return inventory;
    }
    public void pay(int itemCost){
        getInventory().setCoins(getInventory().getCoins()-itemCost);
    }
    public void loot(int lootedCoins){
        getInventory().setCoins(getInventory().getCoins()+lootedCoins);
    }
}
