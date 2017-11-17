package manila.model;

/**
 * 海盗类，里面有两个位置，第一个是海盗老大，第二个是海盗小弟
 */
public class Pirate extends Area{

    public Pirate( Position[] pl) {
        this.pos_list = pl;
    }
    @Override
    public void playerGetProfit(Player[] players) {
        //TODO
    }
}
