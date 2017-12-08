package manila.view;

import manila.model.BoatPosition;
import manila.model.Game;
import manila.model.Position;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class ShipYardView extends YardView {

    /**船厂里船位置的起始Y*/
    public static final int SHIP_POS_START_Y=ABSOLUTE_Y+POS_START_Y;

    public ShipYardView(Game game) {
        super(game);
    }

    public void drawArea (Graphics2D g2){
        g2.setColor(Color.GRAY);
        g2.fill(new Rectangle2D.Double(0, 0, ABSOLUTE_W, ABSOLUTE_H));

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("SansSerif", Font.PLAIN, 18));
        g2.drawString("修船厂", 18, 18);

        Position[] positions = this.game.getShipYard().getPos_list();
        BoatPosition[] boat_positions = this.game.getShipYard().getBoatPositions();
        this.drawPosition(g2,positions);
        this.drawProfitBlock(g2,boat_positions);
    }
}
