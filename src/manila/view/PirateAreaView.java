package manila.view;

import manila.model.Game;
import manila.model.Pirate;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class PirateAreaView extends JPanel {
    /** 区域宽度 */
    private static final int AREA_W = 200;
    /** 区域高度 */
    private static final int AREA_H = 800;

    /**区域的开始位置的x坐标*/
    private static final int AREA_START_X=0;
    /**区域的开始位置的y坐标*/
    private static final int AREA_START_Y=0;

    /** 区域上位置的宽度 */
    private static final int POS_W = 40;
    /** 区域上位置的高度 */
    private static final int POS_H = 20;

    
    /** 区域最上面位置左上角的x坐标 */
    private static final int POS_START_X = 5;
    /** 区域最上面位置左上角的y坐标 */
    private static final int POS_START_Y = 20;
    
    /** 区域位置间在x方向上的间隔 */
    private static final int POS_INTERVAL = 10;

    private Game game;

    public PirateAreaView(Game game) {
        this.game = game;
        this.game.getPirate().setPosX(AREA_START_X);
        this.game.getPirate().setPosY(AREA_START_Y);

    }
    public void drawArea(Graphics2D g2){
        g2.setColor(Color.GRAY);
        g2.fill(new Rectangle2D.Double(AREA_START_X, AREA_START_Y, AREA_W, AREA_H));

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("SansSerif", Font.PLAIN, 18));
        g2.drawString("区域", AREA_START_X+14, AREA_START_Y+18);

    }
}
