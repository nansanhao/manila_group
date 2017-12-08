package manila.view;

import manila.model.BoatPosition;
import manila.model.Game;
import manila.model.Position;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * 海港view，用于停靠船。
 */

public class HarbourView extends YardView{
    /**重写区域的Y坐标*/
    public static final int ABSOLUTE_Y=PlaygroundView.SEA_START_Y;
    /**重写区域上船开始的坐标*/
    public static final int SHIP_POS_START_Y=ABSOLUTE_Y+POS_START_Y;

    public HarbourView(Game game) {
        super(game);
    }

    public void drawArea (Graphics2D g2){
        g2.setColor(Color.GRAY);
        g2.fill(new Rectangle2D.Double(0, 0, ABSOLUTE_W, ABSOLUTE_H));

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("SansSerif", Font.PLAIN, 18));

        g2.drawString("港口", 18, 18);

        Position[] positions = this.game.getHarbour().getPos_list();
        BoatPosition[] boat_positions = this.game.getHarbour().getBoatPositions();
        this.drawPosition(g2,positions);
        this.drawProfitBlock(g2,boat_positions);
    }
}
