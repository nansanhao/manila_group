package manila.model;

/**
 * 海盗类，里面有两个位置，第一个是海盗老大，第二个是海盗小弟
 */
public class Pirate extends Area{
    /**当前活动的位子*/
    private int current_pos;

    public int getCurrent_pos() {
        return current_pos;
    }

    public void setCurrent_pos(int current_pos) {
        this.current_pos = current_pos;
    }

    public Pirate( ) {
        int[] prices = {5,5};
        Position[] aPos_list = new Position[prices.length];
        for(int i=0;i<prices.length;i++){
            aPos_list[i] = new Position(prices[i]);
        }
        this.pos_list=aPos_list;
        this.current_pos=-1;
    }
    @Override
    /**
     * 结算劫船的钱，根据海盗人数平分 第三轮到13格才用这个方法
     * 船长决定船去哪
     */
    public void playerGetProfit(Game game) {
        if(this.getAvailPosIndex()!=0){//不为空
            int profit=0;
            for(Boat b:game.getBoats()){
                if(b.getPos_in_the_sea()==Game.SEA_LENGTH)
                    profit+=b.getCargo_value();
            }
            Player[] players=game.getPlayers();
            for(Position pos : this.getPos_list()){
                if(pos.getSailorID() != -1)//-1是空位
                    players[pos.getSailorID()].receiveProfit(profit/getFilledPosNum());
            }
        }

    }


    /**
     * 一个人上船后，更新海盗船长
     */
    public void updateCaptiain(){
        this.pos_list[0].setSailorID(this.pos_list[1].getSailorID());
        this.pos_list[1].setSailorID(-1);
        this.current_pos=0;
    }


    public int getFirstId(){
        for(int i=0; i<this.pos_list.length; i++){
            if(this.pos_list[i].getSailorID() != -1)
                return pos_list[i].getSailorID();
        }
        return -1;
    }

    public void switchPos_id(){
        if(this.current_pos==-1)
        {
            for(int i=0; i<this.pos_list.length; i++){
                if(this.pos_list[i].getSailorID() != -1) {
                    this.current_pos=i;
                    break;
                }
            }
        }
        else if(this.current_pos==0&&pos_list[1].getSailorID()!=-1){
            this.current_pos=1;
        }
        else{
            this.current_pos=-1;
        }
    }
}
