package manila.model;

/**
 * 海盗类，里面有两个位置，第一个是海盗老大，第二个是海盗小弟
 */
public class Pirate extends Area{

    public Pirate( ) {
        // TODO: 2017/11/19 初始化海盗：何剑冲 11.20 未知位置 POSX POSY待定
        int[] prices = {5,5};
        Position[] aPos_list = new Position[prices.length];
        for(int i=0;i<prices.length;i++){
            aPos_list[i] = new Position(prices[i]);
        }
        this.pos_list=aPos_list;
    }
    @Override
    /**
     * 结算劫船的钱，根据海盗人数平分 第三轮到13格才用这个方法
     * 船长决定船去哪
     */
    public void playerGetProfit(Game game) {
        //TODO：结算劫船之后：何剑冲 需在Game中加一个获取哪艘船被劫的方法
//        Boat theRobbedBoat=game.getRobbedBoat();
//        game.getPlayerByID(this.pos_list[0].getSailorID()).receiveProfit(theRobbedBoat.getCargo_value());
    }



    //下面的方法有待商榷....
    /**
     * 抢劫船，船长船员选择上船
     * @param boats 从GAME获取当前在海上的船数组 遍历是否有位置
     */
    public void getOnBoat(Boat[] boats){
        //TODO
    }

    /**
     * 一个人上船后，更新海盗船长
     */
    public void updateCaptiain(){
        //TODO
    }

    /**
     * 船长决定让船是否进港口 若是让船再move1格 且黑市货物升价
     * @param choice 从controller获得 1进港口 0进船厂
     */
    public void moveBoatTo(int choice){

    }
}
