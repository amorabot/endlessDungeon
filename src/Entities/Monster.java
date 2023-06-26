package Entities;

import InventoryComponents.Weapon;

public abstract class Monster {

    protected boolean alive = true;

    protected String name;

    protected int health;
    protected int damage;

    public abstract void attack(Player player);
    public abstract void takeDamage(Player player, Weapon playerWeapon);

    public int getHealth(){
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage(){
        return damage;
    }

    public boolean isAlive(){
        return alive;
    }
    public void die(){
        alive = false;
        health = 0;
    }

    public void setName(String monsterName){
        name = monsterName;
    }

    public String getName() {
        return name;
    }
}
