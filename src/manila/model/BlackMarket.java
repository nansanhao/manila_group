package manila.model;

import java.util.Random;

/**
 * 黑市类，里面是当前四种股票的价格
 */
public class BlackMarket {
    /**
     * 黑市里的股票，结构为二维数组，数组每行为一种股票，一行里有一定数量的该种股票
     */
    private Shares[][] cargo_shares;

    private Random random;

    public Shares[][] getCargo_shares() {
        return cargo_shares;
    }

    public void setCargo_shares(Shares[][] cargo_shares) {
        this.cargo_shares = cargo_shares;
    }

    public BlackMarket() {
        //TODO：范贤明
        cargo_shares = new Shares[4][5];
        for (int i = 0; i < 5; i++) {
            cargo_shares[0][i] = new Shares(0, "玉器", 1);
            cargo_shares[1][i] = new Shares(0, "丝绸", 1);
            cargo_shares[2][i] = new Shares(0, "可可", 1);
            cargo_shares[3][i] = new Shares(0, "人参", 1);
        }
        this.random=new Random();
    }

    /**
     * 获得当前黑市里最高股票的价钱
     *
     * @return 最高价
     */
    public int getTopPrice() {
        // TODO: 2017/11/17 获得当前股市里最高股票的价钱：范贤明，11.24完成
        int biggest_share = cargo_shares[0][1].getPrice();
        for (int i = 1; i <cargo_shares.length ; i++) {
            if (cargo_shares[i][1].getPrice() > biggest_share)
                biggest_share = cargo_shares[i][1].getPrice();
        }
        return biggest_share;
    }

    /**
     * 更新股市价格
     * cargoname为到了港口的船的货物名称
     */
    public void updatePrice(String cargo_name) {
        // TODO: 2017/11/17  根据到了港口的船更新黑市价格：范贤明，11.24完成
        for (int i = 0; i < cargo_shares.length; i++) {
            if (cargo_shares[i][0].getCargo_name().equals(cargo_name)) {
                for (int j = 0; j < cargo_shares[i].length; j++) {
                    int price = cargo_shares[i][1].getPrice();
                    if (price < 10) {
                        cargo_shares[i][j].setPrice(price + 5);
                    } else {
                        cargo_shares[i][j].setPrice(price + 10);
                    }
                }
            }
        }
    }


    /**用于初始化发黑市股票*/
    public void distributeShares(Player[] players) {
        int random_number;//随机股票种类
        for(Player p:players){
            while (p.getHaveShares().size()<2) {
                random_number=random.nextInt(4);
                if(getUnOwnedSharesIndex(random_number)!=-1)
                {
                    this.cargo_shares[random_number][getUnOwnedSharesIndex(random_number)].setOwner(p);
                }
            }
        }
    }

    /**
     * 找到对应股票类型第一张没有排除的number
     * @param random_number
     * @return 若都卖出去了就返回-1
     */
    private int getUnOwnedSharesIndex(int random_number) {
        for(int i=0;i<cargo_shares[random_number].length;i++)
        {
            if(cargo_shares[random_number][i].getOnwer_id()==-1)
                return i;
        }
        return  -1;
    }


}

