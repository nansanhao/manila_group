package manila.view;

import manila.model.BoatPosition;
import manila.model.Game;
import manila.model.Position;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class YardView extends AreaView {

    /**区域宽度*/
    public static final int ABSOLUTE_W=220;
    /**区域高度*/
    public static final int ABSOLUTE_H=PlaygroundView.SEA_L/2-5;
    /**区域X坐标*/
    public static final int ABSOLUTE_X=PlaygroundView.GROUND_W-ABSOLUTE_W;
    /**区域Y坐标*/
    public static final int ABSOLUTE_Y=PlaygroundView.SEA_START_Y+PlaygroundView.SEA_L/2+10;


    /**位置的间隔*/
    public static final int INTERVAL =5;


    /**位置宽度*/
    public static final int POS_W=50;
    /**位置高度*/
    public static final int POS_H=PlaygroundView.BOAT_H;
    /**位置X坐标 相对于区域*/
    public static final int POS_START_X=ABSOLUTE_W-INTERVAL-POS_W;
    /**位置Y坐标 相对于区域*/
    public static final int POS_START_Y=20;


    /**获利的格子宽度*/
    public static final int PROFIT_BLOCK_W=25;
    /**获利的格子高度*/
    public static final int PROFIT_BLOCK_H=PlaygroundView.BOAT_H;
    /**获利的格子的X坐标相对于区域*/
    public static final int PROFIT_BLOCK_START_X=POS_START_X-INTERVAL-PROFIT_BLOCK_W;
    /**获利的格子的Y坐标相对于区域*/
    public static final int PROFIT_BLOCK_START_Y=POS_START_Y;

    /**船进入港口后的X坐标*/
    public static final int SHIP_POS_START_X=ABSOLUTE_X+PROFIT_BLOCK_START_X-INTERVAL-PlaygroundView.BOAT_W;



    public YardView(Game game) {
        super(game);
    }

    @Override
    public void drawArea(Graphics2D g2) {
    }

    /**画出获利数的格子*/
    public void drawProfitBlock(Graphics2D g2,BoatPosition[] pos_list) {

        for(int i=0; i<pos_list.length; i++){
            g2.setColor(Color.BLACK);
            Rectangle2D r_pos = new Rectangle2D.Double(PROFIT_BLOCK_START_X,
                    PROFIT_BLOCK_START_Y+i*(PROFIT_BLOCK_H+ INTERVAL),
                    PROFIT_BLOCK_W, PROFIT_BLOCK_H);
            g2.fill(r_pos);
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("SansSerif", Font.PLAIN, 18));
            g2.drawString(pos_list[i].getProfit()+"", (int)r_pos.getX()+5, (int)r_pos.getY()+POS_H/2);


        }
    }

    @Override
    public void drawPosition(Graphics2D g2, Position[] pos_list) {
        for(int i=0; i<pos_list.length; i++){
            if(pos_list[i].getSailorID() == -1){
                g2.setColor(Color.WHITE);
                Rectangle2D r_pos = new Rectangle2D.Double(POS_START_X,
                        POS_START_Y+i*(POS_H+ INTERVAL),
                        POS_W, POS_H);
                g2.fill(r_pos);
                g2.setColor(Color.BLACK);
                g2.setFont(new Font("SansSerif", Font.PLAIN, 20));
                g2.drawString(pos_list[i].getPrice()+"", (int)r_pos.getX()+POS_W/2-4, (int)r_pos.getY()+POS_H/2+5);
            }
            else{
                g2.setColor(this.game.getPlayerByID(pos_list[i].getSailorID()).getC());
                g2.fill(new Rectangle2D.Double(POS_START_X,
                        POS_START_Y+i*(POS_H+ INTERVAL),
                        POS_W, POS_H));
            }
        }
    }
}
