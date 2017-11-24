package manila.model;

public class BoatPosition {
    private int boatPrice;
    private String boatCargo_name;

    public BoatPosition(int boatPrice) {
        this.boatPrice = boatPrice;
        this.boatCargo_name=null;
    }

    public int getBoatPrice() {
        return boatPrice;
    }

    public void setBoatPrice(int boatPrice) {
        this.boatPrice = boatPrice;
    }

    public String getBoatCargo_name() {
        return boatCargo_name;
    }

    public void setBoatCargo_name(String boatCargo_name) {
        this.boatCargo_name = boatCargo_name;
    }
}
