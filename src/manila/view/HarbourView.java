package manila.view;

import manila.model.BoatPosition;
import manila.model.Game;
import manila.model.Position;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class HarbourView extends YardView{
    public static final int ABSOLUTE_Y=PlaygroundView.SEA_START_Y;

    public HarbourView(Game game) {
        super(game);
    }
    public void drawArea (Graphics2D g2){
        g2.setColor(Color.GRAY);
        g2.fill(new Rectangle2D.Double(AREA_START_X, AREA_START_Y, ABSOLUTE_W, ABSOLUTE_H));

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("SansSerif", Font.PLAIN, 18));
        g2.drawString("港口", AREA_START_X+18, AREA_START_Y+18);

        Position[] positions = this.game.getHarbour().getPos_list();
        BoatPosition[] boat_positions = this.game.getHarbour().getBoatPositions();
        this.drawPosition(g2,positions);
        this.drawProfitBlock(g2,boat_positions);
    }
}
