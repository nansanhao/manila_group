package manila.model;

import manila.view.AreaView;
import manila.view.InsuranceAreaView;
import manila.view.ShipYardView;

/**
 * 修船厂类，里面有三个位置
 */

public class ShipYard extends Area{
    public BoatPosition[] boatPositions;

    public BoatPosition[] getBoatPositions() {
        return boatPositions;
    }

    public void setBoatPositions(BoatPosition[] boatPositions) {
        this.boatPositions = boatPositions;
    }

    public ShipYard(){

        // TODO: 2017/11/20 初始化修船厂：郑抗 11.23完成

        Position[] pos_list=new Position[3];
        pos_list[0]=new Position(4);
        pos_list[1]=new Position(3);
        pos_list[2]=new Position(2);

        this.pos_list = pos_list;

        BoatPosition[] boatPosition=new BoatPosition[3];
        boatPosition[0]= new BoatPosition(6);
        boatPosition[1]=new BoatPosition(8);
        boatPosition[2]=new BoatPosition(15);
        this.boatPositions=boatPosition;

    }
    /**
     * 获得区域当前空着的位置的编号（进入区域时自动从较低的编号开始）
     * @return 当前编号最小的空位所对应的编号值
     */
    public int getAvailBoatPosIndex(){
        for(int i=0; i<this.boatPositions.length; i++){
            if(!this.boatPositions[i].isHaveBoat())
                return i;
        }
        // no position left
        return -1;
    }


    @Override
    public void playerGetProfit(Game game) {
        // TODO: 2017/11/20 修船场结算 ：郑抗 11.23完成

        for(int i=0;i<this.boatPositions.length;i++){
            if(boatPositions[i].isHaveBoat()&&this.pos_list[i].getSailorID()!=-1){
                game.getPlayerByID(this.pos_list[i].getSailorID()).receiveProfit(boatPositions[i].getProfit());
            }
        }
    }


}
