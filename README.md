# endlessDungeon
 A dungeon crawling, old-style, adventure game implementing and combining simple A.I. techniques, such as Finite State Machines and Fuzzy Logic Inference. 

![image](https://github.com/amorabot/endlessDungeon/assets/16783145/a2c87940-d436-4c15-ad67-2900becf0bd7)

## There are 4 main actions and 5 attributes the player needs to keep track of:

  Actions:
  * Sword attack (Diminishes it's durability)
  * Bow attacks (Consumes a arrow, not entirely effective)
  * Drinking a potion (Regenerates health)
  * Scape attempt (If failed, gets you damaged, if succeeded gets you out of combat)

  Player Attributes:
  * Sword durability (If it comes to 0, the sword will break, drops significantly agains corrosive enemies)
  * Remaining arrows (Using a arrow may waste it in some cases...)
  * Potion charges (Tracks down how many uses of potions the player has left, each use regenerates a HP)
  * HP (Tracks the player's hit points, if it comes to 0... things may not end well)
  * Coins (You get coins after every successful combat, it may serve you well in the shop)

## Once a player escapes a battle or gets lucky after one ends, The Shop may appear.

![image](https://github.com/amorabot/endlessDungeon/assets/16783145/e8fa4329-1232-460e-b479-a1e3682c9bac)

## The all-knowing dungeon sees right through you and sets things up accordingly...
### Here you can find one of three merchant NPCs:
* **The Witch Doctor** (The potion merchant. He may not appear when you need him most, and if he does... he may charge extra. Keep you potions up)
* **The Shady Huntress** (The bow & arrow merchant. She appears often, but knows when you need her equipments and charges you accordingly for it)
* **The Undead Arms Dealer** (The sword merchant. Another common appearance. It sells sword repairs and upgrades, and knows all too well if you need them. It also charges you extra for your needs)

### For every monster you kill, you gain coins. Those coins can be spent here.
### but use them well, bargaining will not be a option

Every NPC has it's roster of items to sell. If you are low on the NPC item's status (Durability, Arrows left...) they will charge extra based on it (Fuzzy inference)

For example: The Arms dealer will charge more for a repair if your sword is almost broken, since you really need it fixed.

After you spend what you can or want, you may exit the shop or try to bargain (and get kicked out of there for good). Once you leave the shop, another battle begins

# How to play this game:

### Intellij IDE
1. Go to the | <> Code | button and download the project's ZIP file
2. Extract anywhere you want
3. Go to "Open project" in your Intellij IDE and open the extracted project folder
4. Setup you JDK environment (if not done previously)
5. Go to src/Main
6. Run the main class and resize your console!
