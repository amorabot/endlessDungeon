package InventoryComponents;

import Entities.Player;

public class Potion {

    private final int heal;

    private int charges;
    private final int maxCharges;

    public Potion(int heal, int maxCharges){
        this.heal = heal;
        this.maxCharges = maxCharges;
        this.charges = 8;
    }

    public void use(Player player){
        if (!consumeCharge()){
            System.out.println("... UNABLE TO USE");
            return;
        }
        if (player.getHealth() + getHeal() >= player.getMaxHealth()) {
            player.setHealth(player.getMaxHealth());
        } else {
            player.setHealth(player.getHealth() + getHeal());
        }
    }
    private boolean consumeCharge(){
        if (charges>=1){
            this.charges -= 1;
            return true;
        } else {
            System.out.println("NO POTION CHARGES LEFT");
            return false;
        }
    }

    public void refill(){
        this.charges = getMaxCharges();
    }

    public int getCharges() {
        return charges;
    }

    public void setCharges(int charges) {
        this.charges = charges;
    }

    public int getHeal() {
        return heal;
    }

    public int getMaxCharges(){
        return this.maxCharges;
    }
}
