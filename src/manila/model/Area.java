package manila.model;

import manila.view.AreaView;
import manila.view.InsuranceAreaView;
import manila.view.PirateAreaView;
import manila.view.PlaygroundView;

import java.awt.*;

/**
 * 区域类，被其他实际区域继承
 */
public  abstract class Area {
    /**区域上的座位*/
    public Position[] pos_list;

    public Position[] getPos_list() {
        return pos_list;
    }

    public void setPos_list(Position[] pos_list) {
        this.pos_list = pos_list;
    }

    /**
     * 获得区域当前空着的位置的编号（进入区域时自动从较低的编号开始）
     * @return 当前编号最小的空位所对应的编号值
     */
    public int  getAvailPosIndex(){
        for(int i=0; i<this.pos_list.length; i++){
            if(this.pos_list[i].getSailorID() == -1)
                return i;
        }
        // no position left
        return -1;
    }

    /**
     * 返回当前区域上已有多少个坐了人的位置数
     * @return 已有人的位置数
     */
    public int getFilledPosNum(){
        int pos_ind = getAvailPosIndex();
        if(pos_ind == -1)
            return this.pos_list.length;
        else
            return pos_ind;
    }

    /**
     * 返回当前编号最小的空位对应的消耗费用
     * @return 当前编号最小的空位对应的消耗费用
     */
    public int getAvailPosPrice(){
        for(int i=0; i<this.pos_list.length; i++){
            if(this.pos_list[i].getSailorID() == -1)
                return this.pos_list[i].getPrice();
        }
        return -1;
    }

    /**
     * 进入能进入的位置
     * @param pid 进入人的Id
     */
    public void joinIn(int pid){
        this.pos_list[getAvailPosIndex()].setSailorID(pid);
    }


    /**
     * 根据各个区域的规则让players获取利润， 该方法在Game的calculateProfit调用
     * @param game 游戏对象
     */
    public abstract void playerGetProfit(Game game);


    /**
     * 按照posID进入区域对应的座位
     * @param numOfPos 对应座位的id
     * @param pid 进入的玩家id
     */
    public  void joinInPos(int numOfPos, int pid){
        this.pos_list[numOfPos].setSailorID(pid);
    }
}
