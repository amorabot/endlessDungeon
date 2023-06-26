package InventoryComponents;

import Enums.DamageTypes;

public abstract class Weapon {

    protected DamageTypes dmgType;

    public abstract int getDamage();
    public abstract DamageTypes getDmgType();
}
