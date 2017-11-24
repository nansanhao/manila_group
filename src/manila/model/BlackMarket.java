package manila.model;

/**
 * 黑市类，里面是当前四种股票的价格
 */
public class BlackMarket {
    /**黑市里的股票，结构为二维数组，数组每行为一种股票，一行里有一定数量的该种股票*/
    private Shares[][] cargo_shares;

    public Shares[][] getCargo_shares() {
        return cargo_shares;
    }

    public void setCargo_shares(Shares[][] cargo_shares) {
        this.cargo_shares = cargo_shares;
    }

    public BlackMarket(){
        //TODO：范贤明
        cargo_shares=new Shares[4][5];
        for(int i=0;i<5;i++){
            cargo_shares[0][i]=new Shares(0,"玉器",1);
            cargo_shares[1][i]=new Shares(0,"丝绸",1);
            cargo_shares[2][i]=new Shares(0,"可可",1);
            cargo_shares[3][i]=new Shares(0,"人参",1);
        }
    }
    /**
     * 获得当前股市里最高股票的价钱
     * @return 最高价
     */
    public int getTopPrice(){
        // TODO: 2017/11/17 获得当前股市里最高股票的价钱：范贤明
        return -1;
    }

    /**
     * 更新股市价格
     * @param boats 数组里装有到了港口的船的货物名称
     */
    public void updatePrice(String[] boats){
        // TODO: 2017/11/17  根据到了港口的船更新黑市价格：范贤明
    }


}
