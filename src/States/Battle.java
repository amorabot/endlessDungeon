package States;

import Entities.Monster;
import Entities.Player;
import Entities.Skeleton;
import Entities.Slime;
import Enums.AsciiElements;
import Enums.Monsters;
import InventoryComponents.Bow;
import InventoryComponents.Potion;
import InventoryComponents.Sword;

import java.util.Scanner;

public class Battle extends State<EndlessDungeon>{

    private static final State<EndlessDungeon> INSTANCE = new Battle();

    @Override
    public void execute(EndlessDungeon dungeon) {
        System.out.println(AsciiElements.BRICK_WALL.getValue());

        renderCombatInterface(dungeon);

        Scanner scan = new Scanner(System.in);

        int choice = scan.nextInt();

        Player player = dungeon.getPlayer();
        Monster monster = dungeon.getMonster();

        switch (choice){
            case 1:
                System.out.println("A SINGULAR STRIKE! ");
                player.attack(monster,player.getInventory().getSword());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException exception){
                    exception.printStackTrace();
                }
                if (!monster.isAlive()){
                    setNextEncounter(dungeon);
                    return;
                }
                System.out.println(monster.getName() + " RETALIATES! " + "*"+monster.getDamage()+" DMG*");
                monster.attack(player);
                break;
            case 2:
                System.out.println("AIMING WITH BOW!");
                player.attack(monster,player.getInventory().getBow());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException exception){
                    exception.printStackTrace();
                }
                if (!monster.isAlive()){
                    setNextEncounter(dungeon);
                    return;
                }
                System.out.println(monster.getName() + " RETALIATES! " + "*"+monster.getDamage()+" DMG*");
                monster.attack(player);
                break;
            case 3:
                // SCAPE ATTEMPT ( 13+, d20 )
                int scapeRoll = (int) (Math.random()*20) + 1;
                try {
                    System.out.print("ROLLING D20");
                    Thread.sleep(1000);
                    System.out.print(".");
                    Thread.sleep(1000);
                    System.out.print(".");
                    Thread.sleep(1000);
                    System.out.println(".");
                    Thread.sleep(1000);
                } catch (InterruptedException exception){
                    exception.printStackTrace();
                }
                if (scapeRoll >= 13){
                    System.out.println("SUCCESSFUL ESCAPE! *" + scapeRoll + "*");
                    dungeon.changeState(Shop.getInstance()); //Finding a merchant
                    return;
                } else {
                    System.out.println("UNSUCCESSFUL ATTEMPT... YOU GET DAMAGED");
                    monster.attack(player);
                }
                break;
            case 4:
                System.out.println("HEALING WITH POTION");
                try {
                    Thread.sleep(500);
                    System.out.print("GULP");
                    Thread.sleep(500);
                    System.out.print(".");
                    Thread.sleep(200);
                    System.out.println(".");
                    Thread.sleep(500);
                    System.out.println(".");
                    Thread.sleep(700);
                } catch (InterruptedException exception){
                    exception.printStackTrace();
                }
                dungeon.getPlayer().heal();
                break;
            default:
                System.out.println("INVALID OPTION");
                break;
        }
        if (player.getHealth() <=0){
            dungeon.changeState(Over.getInstance());
        }
    }

    @Override
    public void enter(EndlessDungeon dungeon) {
        System.out.println("A NEW BATTLE BEGINS...");
        generateMonster(dungeon);
    }

    @Override
    public void exit(EndlessDungeon dungeon) {
        System.out.println("THIS BLOODY BATTLE COMES TO AN END.");
    }

    private void renderCombatInterface(EndlessDungeon dungeon){
        String heart = AsciiElements.HEART.getValue() + " ";
        String bow = AsciiElements.BOW.getValue();
        String bottle = AsciiElements.BOTTLE.getValue();
        String sword = AsciiElements.SWORD.getValue();
        Player player = dungeon.getPlayer();
        Bow playerBow = player.getInventory().getBow();
//        .indent(n);
//        bow.lines();
        Potion playerPotions = player.getInventory().getPotion();
        Sword playerSword = player.getInventory().getSword();

        System.out.println(sword.replace("#", playerSword.getDurability()+ "/" +playerSword.getMaxDurability()).indent(12));

        System.out.print(bow.replace(">", ">" + "   " + playerBow.getArrows()+"/"+playerBow.getMaxArrows()).indent(20));

        System.out.println("\n"+bottle.replace("#", playerPotions.getCharges()+"/"+playerPotions.getMaxCharges()).indent(20));

        if (player.getHealth()>0){
            System.out.println(("HP "+heart.repeat(player.getHealth())+"  $$$ " + player.getInventory().getCoins()).indent(15));
        } else {
            System.out.println("( X . X )".indent(20));
        }

        Monster dungeonMonster = dungeon.getMonster();
        String dmgIcon = AsciiElements.DAMAGE.getValue() + " ";
        String dmg;
        if (dungeonMonster.getDamage() > 1){
            dmg = dmgIcon.repeat(dungeonMonster.getDamage()).strip();
        } else {
            dmg = dmgIcon;
        }
        String monsterStatsElement;
        if (dungeonMonster.getHealth()>0){
            monsterStatsElement = heart.repeat(dungeonMonster.getHealth())+ dungeonMonster.getName()+ " " + dmg;
        } else {
            monsterStatsElement = " --=DEAD=--" + dungeonMonster.getName()+ " " + dmg;
        }
        String monsterStatsLine = "\\\\  "+ monsterStatsElement + "\n";
        String enemyDeclarationLine = "//  " + "IN COMBAT WITH\n";
        System.out.print(enemyDeclarationLine.indent(18));
        System.out.println(monsterStatsLine.indent(18));

        System.out.print("---=== CHOOSE YOUR ACTION ===---\n".indent(14));
        System.out.print("1 -> SWORD ATTACK\n".indent(21));
        System.out.print("2 -> BOW SHOT\n".indent(21));
        System.out.print("3 -> TRY TO ESCAPE\n".indent(21));
        System.out.println("4 -> USE POTION".indent(21));
        System.out.println(AsciiElements.BRICK_WALL.getValue());
    }

    public static State<EndlessDungeon> getInstance(){
        return INSTANCE;
    }
    public static void generateMonster(EndlessDungeon dungeon){
        Monsters[] monsters = Monsters.values();
        Monsters chosenMonster = monsters[(int) ((monsters.length)*Math.random())];

        switch (chosenMonster){
            case SKELETON -> {
                System.out.println("A NEW SKELETON WANDERS IN");
                dungeon.setMonster(new Skeleton(Monsters.SKELETON.toString()));
            }
            case SLIME -> {
                System.out.println("A NEW SLIME SLITHERS IN");
                dungeon.setMonster(new Slime(Monsters.SLIME.toString()));
            }
            default -> System.out.println("UNABLE TO GENERATE NEW MONSTER");
        }
    }
    private void setNextEncounter(EndlessDungeon dungeon){
        lootEnemy(dungeon);

        double shopRoll = Math.random();
        if (shopRoll<= Shop.shopChance){
            //The battle ends with a shop encounter
            dungeon.changeState(Shop.getInstance());
        } else {
            //A new battle begins
            dungeon.changeState(Battle.getInstance());
        }
    }
    private void lootEnemy(EndlessDungeon dungeon){
        //TODO: implementar loot variavel com base no inimigo
        int coinRoll = 13 + (int)(Math.random()*(27-13) +1);
        System.out.println("...YOU LOOT YOUR DEAD OPONENT AND FIND " +coinRoll+ " COINS");
        dungeon.getPlayer().loot(coinRoll);
    }
}
