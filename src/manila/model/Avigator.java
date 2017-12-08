package manila.model;


import manila.view.PlaygroundView;

/**
 * 领航员类，上面有一个位置。
 */
public class Avigator extends Area {
    /**当前在作用的座位号*/
    private int current_pos;

    public int getCurrent_pos() {
        return current_pos;
    }

    public Avigator() {
        //TODO：需要为领航员初始化属性，如位置等，参照其父类：郑抗:11.24完成
        Position[] pos_list = new Position[2];
        pos_list[0] = new Position(2);
        pos_list[1] = new Position(5);
        this.pos_list = pos_list;
        this.current_pos=-1;
    }


    @Override
    public void playerGetProfit(Game game) {
    }

    /**
     * 获得当前第一个非空座位上的玩家ID
     * @return 若为空返回-1 若有玩家返回玩家ID
     */
    public int getFirstId(){
        for(int i=0; i<this.pos_list.length; i++){
            if(this.pos_list[i].getSailorID() != -1)
                return pos_list[i].getSailorID();
        }
        return -1;
    }

    /**
     * 切换到下一个作用的座位，若后面座位无人则设置为-1
     */
    public void switchPos_id(){
        if(this.current_pos==-1)
        {
            for(int i=0; i<this.pos_list.length; i++){
                if(this.pos_list[i].getSailorID() != -1) {
                    this.current_pos=i;
                    break;
                }
            }
        }
        else if(this.current_pos==0&&pos_list[1].getSailorID()!=-1){
            this.current_pos=1;
        }
        else{
            this.current_pos=-1;
        }
    }
}


