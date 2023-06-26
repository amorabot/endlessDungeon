package InventoryComponents;

import Enums.DamageTypes;

public class Bow extends Weapon{

    final int dmg;

    int arrows;
    final int maxArrows;

    public Bow(int dmg, int arrows, int maxArrows, DamageTypes dmgType){
        this.dmg = dmg;
        this.arrows = arrows;
        this.maxArrows = maxArrows;

        this.dmgType = dmgType;
    }

    @Override
    public int getDamage() {
        return this.dmg;
    }
    @Override
    public DamageTypes getDmgType() {
        return this.dmgType;
    }

    public int getArrows() {
        return arrows;
    }

    public void setArrows(int arrows) {
        this.arrows = arrows;
    }

    public int getMaxArrows() {
        return maxArrows;
    }
}
