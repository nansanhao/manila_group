package manila.model;

import manila.view.PlaygroundView;

/**
 * 区域类，被其他实际区域继承
 */
public  abstract class Area {
    /**
     * 区域上的座位
     */
    public Position[] pos_list;
    /** 区域（左上角）在图形界面上的x坐标 */
    private int posX;
    /**区域（左上角）在图形界面上的y坐标 */
    private int posY;

    public Position[] getPos_list() {
        return pos_list;
    }

    public void setPos_list(Position[] pos_list) {
        this.pos_list = pos_list;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * 获得区域当前空着的位置的编号（进入区域时自动从较低的编号开始）
     * @return 当前编号最小的空位所对应的编号值
     */
    public int getAvailPosIndex(){
        for(int i=0; i<this.pos_list.length; i++){
            if(this.pos_list[i].getSailorID() == -1)
                return i;
        }
        // no position left
        return -1;
    }

    /**
     * 返回当前区域上已有多少个坐了人的位置数
     * @return 已有人的位置数
     */
    public int getFilledPosNum(){
        int pos_ind = getAvailPosIndex();
        if(pos_ind == -1)
            return this.pos_list.length;
        else
            return pos_ind;
    }

    /**
     * 返回当前编号最小的空位对应的消耗费用
     * @return 当前编号最小的空位对应的消耗费用
     */
    public int getAvailPosPrice(){
        for(int i=0; i<this.pos_list.length; i++){
            if(this.pos_list[i].getSailorID() == -1)
                return this.pos_list[i].getPrice();
        }
        return -1;
    }

    /**
     * 判断鼠标光标是否在该船的范围内
     * @param x 光标的横坐标
     * @param y 光标的纵坐标
     * @return 是否在该船的范围内
     */
    public boolean isCursorInside(int x, int y){
        if(x > this.posX && x < this.posX+ PlaygroundView.BOAT_W
                && y > this.posY && y< this.posY+PlaygroundView.BOAT_H)
            return true;
        return false;
    }

    /**
     * 进入能进入的位置
     * @param pid 进入人的Id
     */
    public void joinIn(int pid){
        this.pos_list[getAvailPosIndex()].setSailorID(pid);
    }

    /**
     * 根据各个区域的规则让players获取利润， 该方法在Game的calculateProfit调用
     * @param game 游戏对象
     */
    public abstract void playerGetProfit(Game game);

}
