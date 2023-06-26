package States;

import Entities.Merchants.Merchant;
import Entities.Player;

public class Shop extends State{

    Merchant merchant;
    Player player;

    public Shop(Player player){
        this.player = player;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void execute(Agent entity) {

    }

    @Override
    public void enter(Agent entity) {

    }

    @Override
    public void exit(Agent entity) {

    }

////    @Override
//    public void execute() {
//
//    }
//
////    @Override
//    public void enter() {
//
//    }
//
////    @Override
//    public void exit() {
//
//    }
}
