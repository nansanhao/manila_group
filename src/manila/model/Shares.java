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
    /**持有者ID*/
    private  int onwer_id;

    public int getOnwer_id() {
        return onwer_id;
    }

    public String getCargo_name() {
        return cargo_name;
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





    public Shares(int price, String cargo_name, int status_pledge) {
        this.price = price;
        this.cargo_name = cargo_name;
        this.status_pledge = status_pledge;
        this.onwer_id=-1;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
        owner.addShares(this);
        this.onwer_id=owner.getPid();

    }
    /**
     * 股票是否被抵押
     * @return 被抵押返回true，未被抵押返回false
     */
    public boolean isPledged(){
        // TODO: 2017/11/17 根据 status_pledge返回布尔值：何剑冲 11.20完成
        if(this.status_pledge==1)
            return false;
        else
            return true;
    }

    /**
     * 改变股票状态，如果是1改为2，是2则改为1
     */
    public void changeStatus_pledge(){
        // TODO: 2017/11/17 改变股票状态：何剑冲 11.20完成
        if(this.status_pledge==1)
            this.status_pledge=2;
        else if(this.status_pledge==2)
            this.status_pledge=1;
    }


}
