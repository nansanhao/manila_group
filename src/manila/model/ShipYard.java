package manila.model;

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

    @Override
    public void playerGetProfit(Game game) {
        // TODO: 2017/11/20 修船场结算 ：郑抗 11.23完成
        Player[] players = game.getPlayers();
        for(int i=0;i<this.boatPosition.length;i++){
            if(boatPosition[i].getBoatCargo_name()!=null){
                game.getPlayerByID(this.pos_list[i].getSailorID()).receiveProfit(boatPosition[i].getBoatPrice());
            }
        }
    }

    @Override
    public int clickOnWhichPos(int x, int y) {
        for(int i=0;i<pos_list.length;i++){
            if(ShipYard.A)
        }
        return 0;
    }
}
