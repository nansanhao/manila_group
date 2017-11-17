package manila.model;

/**
 * 股票类
 */
public class Shares {
    /**股价*/
    private int price;
    /**货物名字*/
    private String cargo_name;
    /**股票状态，1为持有，2为抵押*/
    private int status_pledge;
    /**股票持有者*/
    private Player owner;

    public Shares(int price, String cargo_name, int status_pledge) {
        this.price = price;
        this.cargo_name = cargo_name;
        this.status_pledge = status_pledge;
    }

    /**
     * 股票是否被抵押
     * @return 被抵押返回true，未被抵押返回false
     */
    public boolean isPledged(){
        // TODO: 2017/11/17 根据 status_pledge返回布尔值
        return false;
    }

    /**
     * 改变股票状态，如果是1改为2，是2则改为1
     */
    public void changeStatus_pledge(){
        // TODO: 2017/11/17 改变股票状态
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStatus_pledge() {
        return status_pledge;
    }

    public void setStatus_pledge(int status_pledge) {
        this.status_pledge = status_pledge;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
