package manila.model;

/**
 * 修船厂类，里面有三个位置
 */
public class ShipYard extends Area{
    private boolean[] isShipPositionOccupied;
    private int[] numOfProfit;
    public ShipYard(Position[] pos_list){
        // TODO: 2017/11/20 初始化修船厂：郑抗
        this.pos_list = pos_list;
        for (int i = 0; i < 3; i++) {
            isShipPositionOccupied[i] = false;
        }
        numOfProfit[0] = 6;
        numOfProfit[1] = 8;
        numOfProfit[2] = 15;
    }
    @Override
    public void playerGetProfit(Game game) {
        // TODO: 2017/11/20 修船场结算 ：郑抗
        Player[] players = game.getPlayers();
        for(int i=0;i<this.numOfProfit.length;i++){
            if(this.isShipPositionOccupied[i]){
                game.getPlayerByID(this.pos_list[i].getSailorID()).receiveProfit(numOfProfit[i]);
            }
        }
    }
}
