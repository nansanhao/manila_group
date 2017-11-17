package manila.model;

/**
 * 海盗类，里面有两个位置，第一个是海盗老大，第二个是海盗小弟
 */
public class Pirate extends Area{

    public Pirate( Position[] pl) {
        this.pos_list = pl;
    }
    @Override
    /**
     * 根据海盗人数平分 第三轮到13格才用这个方法
     * 船长决定船去哪
     */
    public void playerGetProfit(Player[] players) {
        //TODO
    }

    /**
     * 抢劫船，船长船员选择上船
     * @param boats 从GAME获取当前在海上的船数组 遍历是否有位置
     */
    public void getOnBoat(Boat[] boats){
        //TODO
    }

    /**
     * 一个人上船后，更新船长
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
