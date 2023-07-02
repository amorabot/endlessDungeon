package States;

import Entities.Merchants.Fletcher;
import Entities.Merchants.Merchant;
import Entities.Merchants.Potionmaster;
import Entities.Merchants.Weaponsmith;
import Entities.Player;
import Enums.AsciiElements;
import Enums.MerchantItems;
import Enums.SwordStates;
import FuzzyLogic.FuzzyMachines.PotionmasterDesirability;
import InventoryComponents.Bow;
import InventoryComponents.Inventory;
import InventoryComponents.Potion;
import InventoryComponents.Sword;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Shop extends State<EndlessDungeon>{

    private static final State<EndlessDungeon> INSTANCE = new Shop();
    public static double shopChance = 0.3;
    public static double potionmasterChance = 0;

    @Override
    public void execute(EndlessDungeon entity) {
        System.out.println(AsciiElements.BRICK_WALL.getValue());
        renderMerchantInterface(entity);
        System.out.println(AsciiElements.BRICK_WALL.getValue());

        Scanner scan = new Scanner(System.in);

        int choice = scan.nextInt();
        if (choice == -1){
            entity.changeState(Battle.getInstance());
            return;
        }

        Player player = entity.getPlayer();
        Merchant merchant = entity.getMerchant();
        List<MerchantItems> items = merchant.getAvailableItems();

        if (choice < 0 || choice>=items.size()){
            System.out.println("NOT A VALID ITEM. CHOOSE AGAIN.");
            return;
        }

        MerchantItems chosenItem = items.get(choice);

        if (player.getInventory().getCoins()-merchant.getPricedItems().get(chosenItem) < 0){
            System.out.println("I KNOW WHAT I HAVE. DONT TRY TO BARGAIN WITH ME...");
            entity.changeState(Battle.getInstance());
            return;
        }
        player.pay(merchant.getPricedItems().get(chosenItem));
        Inventory playerInventory = player.getInventory();

        switch (chosenItem){
            case ARROW -> {
                Bow playerBow = playerInventory.getBow();
                playerBow.setArrows(Math.min(playerBow.getArrows() + 1, playerBow.getMaxArrows()));
            }
            case ARROW_BUNDLE -> { //A logica acima seria equivalente a abaixo
                Bow playerBow = playerInventory.getBow();
                playerBow.setArrows(Math.min(playerBow.getArrows() + 5, playerBow.getMaxArrows()));
            }
            case NEW_QUIVER -> {
                Bow playerBow = playerInventory.getBow();
                playerBow.setArrows(playerBow.getMaxArrows());
            }
            case POTION -> {
                Potion playerPotion = playerInventory.getPotion();
                playerPotion.setCharges(Math.min(playerPotion.getCharges() + 1, playerPotion.getMaxCharges()));
            }
            case POTION_BAG -> {
                Potion playerPotion = playerInventory.getPotion();
                playerPotion.setCharges(playerPotion.getMaxCharges());
            }
            case SHARPENING_WHETSTONE -> {
                Sword playerSword = playerInventory.getSword();
                int repairValue = (int)(playerSword.getMaxDurability()*0.4);
                playerSword.setDurability(Math.min(playerSword.getDurability()+repairValue, playerSword.getMaxDurability()));
            }
            case POISON_VIAL -> {
                Sword playerSword = playerInventory.getSword();
                SwordStates state = playerSword.getSwordCondition();
                switch (state){
                    case BROKEN -> playerSword.setSwordCondition(SwordStates.BROKEN_POISONOUS);
                    case NORMAL -> playerSword.setSwordCondition(SwordStates.POISONOUS);
                }
                System.out.println("YOU PUT A POWERFUL VENOM ON THE TIP OF YOUR BLADE");
                playerSword.setDmg();
            }
        }
    }

    @Override
    public void enter(EndlessDungeon entity) {
        System.out.println("THE WEARY TRAVELER FINDS A... FRIENDLY FOE?");
        chooseRandomMerchant(entity);
    }

    @Override
    public void exit(EndlessDungeon entity) {
        System.out.println("WE SHALL CROSS PATHS AGAIN, TRAVELER...");
    }

    public void renderMerchantInterface(EndlessDungeon dungeon){
        Merchant merchant = dungeon.getMerchant();
        Player player = dungeon.getPlayer();

        System.out.println();
        System.out.println("YOU ENCOUNTER THE".indent(22));
        System.out.println(("____---===  "+merchant.getName()+"  ===---____").indent(12));
        System.out.println();
        System.out.println(String.format("You have %s coins", player.getInventory().getCoins()).indent(22));
        System.out.println();
        Map<MerchantItems, Integer> pricedItems = merchant.getPricedItems();
        List<MerchantItems> availableItems = merchant.getAvailableItems();

        System.out.println("CHOOSE -1 TO IGNORE THE MERCHANT".indent(14));
        for (int i = 0; i<availableItems.size(); i++){
            MerchantItems currentItem = availableItems.get(i);
            String itemName = currentItem.toString();
            if (itemName.contains("_")){
                itemName = itemName.replace("_", " ");
            }
            String itemIndexIndicator = String.format("PRESS %s FOR >>  ", i);
            System.out.println((itemIndexIndicator+itemName+"  $$ "+pricedItems.get(currentItem)+"\n").indent(14));
        }
    }

    public static void chooseRandomMerchant(EndlessDungeon dungeon){
        calculatePotionmasterChance(dungeon.getPlayer());
        double merchantRoll = Math.random();
        Merchant chosenMerchant;

        //      0-1                 0.33-1
        if (merchantRoll <= potionmasterChance){ //Chosen merchant was a potionmaster
            chosenMerchant = new Potionmaster("Witch doctor", MerchantItems.POTION, MerchantItems.POTION_BAG);
        } else { //Let's choose another one (Fletcher or Weaponsmith)
            // 50-50 roll
            double weaponMerchantRoll = Math.random();
            if (weaponMerchantRoll <= 0.5){ //Weaponsmith
                chosenMerchant = new Weaponsmith("Undead arms dealer", MerchantItems.SHARPENING_WHETSTONE, MerchantItems.POISON_VIAL);
            } else{ //Fletcher
                chosenMerchant = new Fletcher("Shady huntress", MerchantItems.ARROW, MerchantItems.ARROW_BUNDLE, MerchantItems.NEW_QUIVER);
            }
        }
        chosenMerchant.mapPrices(dungeon.getPlayer());

        dungeon.setMerchant(chosenMerchant);
    }

    public static void calculatePotionmasterChance(Player player){
        //TODO: Inferir a chance com base na fuzzy logic das cargas de pocao
        ///LOGICA DO SPAWN DO NPC
//        potionmasterChance = 0.4;
        potionmasterChance = PotionmasterDesirability.calculateDesirability(player);
    }

    public static State<EndlessDungeon> getInstance(){
        return INSTANCE;
    }
}
