package Entities;

import Enums.DamageTypes;
import Enums.SwordStates;
import InventoryComponents.Bow;
import InventoryComponents.Sword;
import InventoryComponents.Weapon;

public class Slime extends Monster{

    public Slime(String name){
        this.health = 5;
        this.damage = 1;

        setName(name);
    }
    @Override
    public void attack(Player player) {
        player.takeDamage(this);
    }

    @Override
    public void takeDamage(Player player, Weapon playerWeapon) {
        int dmg = playerWeapon.getDamage();

        DamageTypes dmgType = playerWeapon.getDmgType();
        switch (dmgType){
            case SLASHING -> {
                int durabilityDrain = 5;
                //Dmg monster
                if (getHealth() - dmg <= 0) {
                    die();
                    System.out.println("THE SLIME MELTS");
                    return;
                }
                setHealth(getHealth()-dmg);
                //Subtract durability or break
                Sword playerSword = (Sword) playerWeapon;
                int currentDurability = playerSword.getDurability();
                if (currentDurability - durabilityDrain <= 0){
                    System.out.println("YOUR SWORD SHATTERS");
                    //Change the sword state
                    if (playerSword.getSwordCondition() == SwordStates.NORMAL){
                        playerSword.setSwordCondition(SwordStates.BROKEN);
                    } else if (playerSword.getSwordCondition() == SwordStates.POISONOUS){
                        playerSword.setSwordCondition(SwordStates.BROKEN_POISONOUS);
                    }
                    playerSword.setDmg(); //Setting the dmg accordingly
                } else {
                    playerSword.setDurability(currentDurability - durabilityDrain);
                }
            }
            case PIERCING -> {
                Bow playerBow = (Bow) playerWeapon;
                int arrows = playerBow.getArrows();
                if (arrows>=1){ //If has enough arrows
                    playerBow.setArrows(arrows-1); //Consume arrow
                    int accuracyRoll = (int) (Math.random()*20) + 1; //Check if it hits
                    if (accuracyRoll <= 3){
                        System.out.println("YOUR SHOT MISSED");
                        return;
                    }
                    System.out.println("SEEMS TO HAVE NO EFFECT ON THIS CREATURE...");
                } else {
                    System.out.println("NO ARROWS LEFT");
                }
            }
        }
    }
}
