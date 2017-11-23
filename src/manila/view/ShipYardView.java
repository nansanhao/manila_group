package manila.view;

import manila.model.Game;
import manila.model.Position;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class ShipYardView extends AreaView {
    protected static final int ABSOLUTE_X=12*(PlaygroundView.SEA_W+PlaygroundView.SEA_INTERVAL)+PlaygroundView.BOAT_W;
    protected static final int ABSOLUTE_Y=20;
    protected static final int ABSOLUTE_W=200;
    protected static final int ABSOLUTE_H=100;



    public ShipYardView(Game game) {
        super(game);
        this.game.getPirate().setPosX(AREA_START_X);
        this.game.getPirate().setPosY(AREA_START_Y);

    }

    @Override
    public void drawArea(Graphics2D g2) {
        g2.setColor(Color.GRAY);
        g2.fill(new Rectangle2D.Double(AREA_START_X, AREA_START_Y, AREA_W, AREA_H/4));

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("SansSerif", Font.PLAIN, 18));
        g2.drawString("修船厂", AREA_START_X+20, AREA_START_Y+20);
        g2.drawString("", AREA_START_X+20, AREA_START_Y+40);


        Position[] positions = this.game.getPirate().getPos_list();
        this.drawPosition(g2,positions);
    }
}
