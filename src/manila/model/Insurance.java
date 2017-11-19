package manila.model;

/**
 * 保险公司类，里面有一个位置，位置价钱为-10
 */
public class Insurance extends Area{

    public Insurance(){
        //TODO：初始化保险类，包括位置等：何剑冲
    }
    @Override
    /**
     * 航程结束，且有船沉时调用
     */
    public void playerGetProfit(Game game) {
        //TODO：计算赔钱，参照Boat类中的计算方法：何剑冲
    }
}
