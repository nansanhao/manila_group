package manila.view;

import manila.model.Game;
import manila.model.Position;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class HarbourView extends AreaView {

    protected static final int ABSOLUTE_X=15*(PlaygroundView.SEA_W+PlaygroundView.SEA_INTERVAL)+PlaygroundView.BOAT_W+10;
    protected static final int ABSOLUTE_Y=PlaygroundView.SEA_START_Y;
    protected static final int ABSOLUTE_W=220;
    protected static final int ABSOLUTE_H=PlaygroundView.SEA_L/2;

    private static final int POS_INTERVAL_Y=70;

    public HarbourView(Game game) {
        super(game);
        //this.game.getHarbour().setPosX(ABSOLUTE_X);
        //this.game.getHarbour().setPosY(ABSOLUTE_Y);
    }

    @Override
    public void drawArea(Graphics2D g2) {
        g2.setColor(Color.GRAY);
        g2.fill(new Rectangle2D.Double(AREA_START_X, AREA_START_Y, ABSOLUTE_W, ABSOLUTE_H));

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("SansSerif", Font.PLAIN, 18));
        g2.drawString("修船厂", AREA_START_X+20, AREA_START_Y+20);


        Position[] positions = this.game.getShipYard().getPos_list();
        this.drawPosition(g2,positions);
    }
}
