package manila.model;


import manila.view.PlaygroundView;

/**
 * 领航员类，上面有一个位置。
 */
public class Avigator extends Area{



    public Avigator(){
        //TODO：需要为领航员初始化属性，如位置等，参照其父类：郑抗:11.24完成
        Position[] pos_list=new Position[2];
        pos_list[0]=new Position(2);
        pos_list[1]=new Position(5);
        this.pos_list=pos_list;
    }
    /**
     * 领航员可以移动船
     * @param boat 将被移动的船
     * @param step 移动的步数
     */
   public void moveBoat(Boat boat ,int step) {
       // TODO: 2017/11/19 郑抗:11.24完成
       boat.setPos_in_the_sea(boat.getPos_in_the_sea()+step);
       boat.setPosX(boat.getPosX()+step * (PlaygroundView.SEA_INTERVAL+ PlaygroundView.SEA_W));
   }

    @Override
    public void playerGetProfit(Game game) {
        //TODO：参照boat的获利：郑抗

    }
}
