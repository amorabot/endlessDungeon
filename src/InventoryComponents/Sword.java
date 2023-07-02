package InventoryComponents;

import Enums.DamageTypes;
import Enums.SwordStates;

public class Sword extends Weapon{

    int[] dmg;
    SwordStates swordCondition = SwordStates.NORMAL;

    int durability;
    int maxDurability;

    DamageTypes dmgType;

    public Sword(int[] dmg, int maxDurability, DamageTypes dmgType){
        this.dmg = dmg;
        this.maxDurability = maxDurability;
        this.durability = maxDurability;

        this.dmgType = dmgType;
    }

    @Override
    public int getDamage() {
        return (dmg[0]+ (int)((dmg[1]-dmg[0]+1)*Math.random()));
    }

    public void setDmg() {
        switch (swordCondition){
            case NORMAL -> dmg =           new int[]{1,3};
            case BROKEN -> dmg =           new int[]{1,1};
            case POISONOUS -> dmg =        new int[]{1,5};
            case BROKEN_POISONOUS -> dmg = new int[]{1,2};
        }
    }

    public SwordStates getSwordCondition() {
        return swordCondition;
    }
    public void setSwordCondition(SwordStates condition){
        this.swordCondition = condition;
    }

    @Override
    public DamageTypes getDmgType() {
        return this.dmgType;
    }
    public int getDurability(){
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public int getMaxDurability() {
        return maxDurability;
    }
}
