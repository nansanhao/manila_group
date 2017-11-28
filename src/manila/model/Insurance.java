package manila.model;

/**
 * 保险公司类，里面有一个位置，位置价钱为-10
 */
public class Insurance extends Area{

    public Insurance(){
        //TODO：初始化保险类，包括位置等：何剑冲完成
        Position[] p=new Position[1];
        p[0]=new Position(-10);//默认价格为-10 即进入获得10块
        this.pos_list=p;
    }
    @Override
    /**
     * 航程结束，且有船沉时调用
     */
    public void playerGetProfit(Game game) {
        //TODO：计算赔钱，参照Boat类中的计算方法：何剑冲 Game还未添加Shipyard Shipyard类中需要一个判断是否进船的方法
//        ShipYard s=game.getShipYard();
//        int cost=0;
//        for(Position p:s.pos_list){
//            if(p.getSailorID()!=-1&&s.isHaveBoat(p))      //判断船厂有没有人
//                cost+=p.getPrice();
//        }
//        game.getPlayerByID(this.pos_list[0].getSailorID()).payPos(cost);
    }


}
