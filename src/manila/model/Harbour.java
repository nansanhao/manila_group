package manila.model;
/**港口类，里面有三个位置，还有三个放船的位置*/
public class Harbour extends Area {

    public BoatPosition[] boatPositions;

    public BoatPosition[] getBoatPositions() {
        return boatPositions;
    }

    public void setBoatPositions(BoatPosition[] boatPositions) {
        this.boatPositions = boatPositions;
    }

    public Harbour(){
        Position[] pos_list=new Position[3];
        pos_list[0]=new Position(4);
        pos_list[1]=new Position(3);
        pos_list[2]=new Position(2);
        this.pos_list=pos_list;

        BoatPosition[] boatPositions=new BoatPosition[3];
        boatPositions[0] =new BoatPosition(6);
        boatPositions[1]=new BoatPosition(8);
        boatPositions[2] =new BoatPosition(15);
        this.boatPositions=boatPositions;

    }
    @Override

    public void playerGetProfit(Game game) {
        // TODO: 2017/11/24  参照ShipYard 完成功能：范贤明 ：11.25完成
        for(int i=0;i<this.boatPositions.length;i++){
            if(boatPositions[i].isHaveBoat()&&this.pos_list[i].getSailorID()!=-1){
                game.getPlayerByID(this.pos_list[i].getSailorID()).receiveProfit(boatPositions[i].getProfit());
            }
        }
    }

    public int getAvailBoatPosIndex(){
        for(int i=0; i<this.boatPositions.length; i++){
            if(!this.boatPositions[i].isHaveBoat())
                return i;
        }
        // no position left
        return -1;
    }

}
