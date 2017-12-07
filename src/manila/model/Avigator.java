package manila.model;


import manila.view.PlaygroundView;

/**
 * 领航员类，上面有一个位置。
 */
public class Avigator extends Area {

    private int current_pos;

    public int getCurrent_pos() {
        return current_pos;
    }

    public void setCurrent_pos(int current_pos) {
        this.current_pos = current_pos;
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

    public int getFirstId(){
        for(int i=0; i<this.pos_list.length; i++){
            if(this.pos_list[i].getSailorID() != -1)
                return pos_list[i].getSailorID();
        }
        return -1;
    }


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


