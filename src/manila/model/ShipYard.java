package manila.model;

import manila.view.AreaView;
import manila.view.InsuranceAreaView;
import manila.view.ShipYardView;

/**
 * 修船厂类，里面有三个位置
 */

public class ShipYard extends Area{
    public BoatPosition[] boatPosition;
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
        this.boatPosition=boatPosition;

    }
    /**
     * 获得区域当前空着的位置的编号（进入区域时自动从较低的编号开始）
     * @return 当前编号最小的空位所对应的编号值
     */
    public int getAvailBoatPosIndex(){
        for(int i=0; i<this.boatPosition.length; i++){
            if(this.boatPosition[i].getBoatCargo_name() == null)
                return i;
        }
        // no position left
        return -1;
    }

    /**
     * 返回当前区域上已有多少个停了船的位置数
     * @return 已有船的位置数
     */
    public int getFilledBoatPosNum(){
        int pos_ind = getAvailBoatPosIndex();
        if(pos_ind == -1)
            return this.boatPosition.length;
        else
            return pos_ind;
    }

    /**
     * 返回当前编号最小的空位对应的消耗费用
     * @return 当前编号最小的空位对应的消耗费用
     */
    public int getAvailBoatPosPrice(){
        for(int i=0; i<this.boatPosition.length; i++){
            if(this.boatPosition[i].getBoatCargo_name() == null)
                return this.boatPosition[i].getBoatPrice();
        }
        return -1;
    }

    @Override
    public void playerGetProfit(Game game) {
        // TODO: 2017/11/20 修船场结算 ：郑抗 11.23完成

        for(int i=0;i<this.boatPosition.length;i++){
            if(boatPosition[i].getBoatCargo_name()!=null){
                game.getPlayerByID(this.pos_list[i].getSailorID()).receiveProfit(boatPosition[i].getBoatPrice());
            }
        }
    }


}
