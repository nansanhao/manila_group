package manila.model;

public class BoatPosition {
    private int profit;
    private boolean isHaveBoat;

        public BoatPosition(int profit) {
        this.profit=profit;
        this.isHaveBoat=false;
    }


    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public boolean isHaveBoat() {
        return isHaveBoat;
    }

    public void setHaveBoat(boolean haveBoat) {
        isHaveBoat = haveBoat;
    }

}
