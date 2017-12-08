package manila.model;

public class BoatPosition {
    /**对应港口位置的获利*/
    private int profit;
    /**对应港口位置是否有船进港*/
    private boolean isHaveBoat;

    public BoatPosition(int profit) {
        this.profit=profit;
        this.isHaveBoat=false;
    }

    public int getProfit() {
        return profit;
    }

    public boolean isHaveBoat() {
        return isHaveBoat;
    }

    public void setHaveBoat(boolean haveBoat) {
        isHaveBoat = haveBoat;
    }

}
