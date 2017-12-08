package manila.model;

/**
 * 保险公司类，里面有一个位置，位置价钱为-10
 */
public class Insurance extends Area{

    public Insurance(){
        //TODO：初始化保险类，包括位置等：何剑冲：11.24完成
        Position[] p=new Position[1];
        p[0]=new Position(-10);//默认价格为-10 即进入获得10块
        this.pos_list=p;
    }
    @Override
    /**
     * 航程结束，且有船沉时调用
     */
    public void playerGetProfit(Game game) {
        //TODO：计算赔钱，参照Boat类中的计算方法：何剑冲：11.24完成
        ShipYard s=game.getShipYard();
        int cost=0;
        if(this.pos_list[0].getSailorID()!=-1){
            for(int i=0;i<s.getBoatPositions().length;i++){
                if(s.getBoatPositions()[i].isHaveBoat()&&s.pos_list[i].getSailorID()!=-1)
                    cost+=s.getBoatPositions()[i].getProfit();
            }
            game.getPlayerByID(this.pos_list[0].getSailorID()).payPos(cost);
        }

    }


}
