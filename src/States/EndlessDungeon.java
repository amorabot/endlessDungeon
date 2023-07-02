package States;

import Entities.Merchants.Merchant;
import Entities.Monster;
import Entities.Player;

public class EndlessDungeon extends Agent {

    private Monster monster;
    private Merchant merchant;
    private final Player player;

    private State<EndlessDungeon> currentState;

    public EndlessDungeon(Player player){
        this.player = player;
        this.merchant = null;

        this.currentState = Battle.getInstance();
        Battle.generateMonster(this);
    }

    @Override
    public void update(){
        currentState.execute(this);
    }
    public void changeState(State<EndlessDungeon> newState){
        currentState.exit(this);
        this.currentState = newState;
        currentState.enter(this);
    }

    public State<EndlessDungeon> getCurrentState() {
        return currentState;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public Player getPlayer() {
        return player;
    }
}
