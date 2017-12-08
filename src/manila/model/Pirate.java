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
        if (this.getAvailPosIndex() != 0&&boatIsRobbed(game)) {//不为空
            int profit = 0;
            for (Boat b : game.getBoats()) {
                if (b.getPos_in_the_sea() == Game.SEA_LENGTH)
                    profit += b.getCargo_value();
            }
            Player[] players = game.getPlayers();
            for (Position pos : this.getPos_list()) {
                if (pos.getSailorID() != -1&&profit!=0) {//-1是空位{
                    players[pos.getSailorID()].receiveProfit(profit / getFilledPosNum());
                    System.out.println(game.getPlayerByID(pos.getSailorID()).getName() + "作为海盗劫船" + profit / getFilledPosNum() + "$");
                }
            }
        }
    }

    /**
     * 判断有没有船到达13格
     * @param game
     * @return 有返回true 无返回false
     */
    private boolean boatIsRobbed(Game game){
        for (Boat b : game.getBoats()) {
            if (b.getPos_in_the_sea() == Game.SEA_LENGTH)
                return true;
        }
        return false;
    }


    /**
     * 一个人上船后，更新海盗船长
     */
    public void updateCaptiain(){
        this.pos_list[0].setSailorID(this.pos_list[1].getSailorID());
        this.pos_list[1].setSailorID(-1);
        this.current_pos=0;
    }

    /**
     * 获得当前第一个非空座位上的玩家ID
     * @return 若为空返回-1 若有玩家返回玩家ID
     */
    public int getFirstId(){
        for(int i=0; i<this.pos_list.length; i++){
            if(this.pos_list[i].getSailorID() != -1)
                return pos_list[i].getSailorID();
        }
        return -1;
    }

    /**
     * 切换到下一个作用的座位，若后面座位无人则设置为-1
     */
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
