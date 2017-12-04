package manila.view;

import manila.model.Game;
import manila.model.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * 区域View类
 */
public abstract class AreaView extends JPanel{

    /** 区域上位置的宽度 */
    public static final int POS_W = PlaygroundView.SEA_W+2*PlaygroundView.SEA_INTERVAL;
    /** 区域上位置的高度 */
    public static final int POS_H = POS_W/2;


    /** 区域最上面位置左上角的x坐标 */
    public static final int POS_START_X = PlaygroundView.SEA_W;
    /** 区域最上面位置左上角的y坐标 */
    public static final int POS_START_Y = 60;

    /** 区域位置间在x方向上的间隔 */
    public static final int POS_INTERVAL = PlaygroundView.SEA_W;

    /**区域起始Y坐标*/
    public static final int ABSOLUTE_Y=20;
    /**区域高度*/
    public static final int ABSOLUTE_H=POS_START_Y+POS_H+POS_INTERVAL;




    protected Game game;

    public AreaView(Game game) {
        this.game = game;

    }

    /**
     * 画出该区域，每个子类要画的东西不同，所以是个抽象方法。
     * @param g2
     */
    public abstract void drawArea(Graphics2D g2);

    /**
     * 划出区域里的位置
     * @param g2
     * @param pos_list 位置数组
     */
    public void drawPosition(Graphics2D g2,Position[] pos_list){
        for(int i=0; i<pos_list.length; i++){
            if(pos_list[i].getSailorID() == -1){
                g2.setColor(Color.WHITE);
                Rectangle2D r_pos = new Rectangle2D.Double(POS_START_X+i*(POS_W+POS_INTERVAL),
                        POS_START_Y,
                        POS_W, POS_H);
                g2.fill(r_pos);
                g2.setColor(Color.BLACK);
                g2.setFont(new Font("SansSerif", Font.PLAIN, 20));
                g2.drawString(pos_list[i].getPrice()+"", (int)r_pos.getX()+POS_W/2-4, (int)r_pos.getY()+POS_H/2+5);
            }
            else{
                g2.setColor(this.game.getPlayerByID(pos_list[i].getSailorID()).getC());
                g2.fill(new Rectangle2D.Double(+POS_START_X+i*(POS_W+POS_INTERVAL),
                        POS_START_Y,
                        POS_W, POS_H));
            }
        }
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        this.drawArea(g2);
    }


}
