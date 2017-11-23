package manila.view;

import manila.model.Game;
import manila.model.Pirate;
import manila.model.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * 海盗区域View
 */
public class PirateAreaView extends AreaView {

    public static final int ABSOLUTE_X=12*(PlaygroundView.SEA_W+PlaygroundView.SEA_INTERVAL)+PlaygroundView.BOAT_W;
    public static final int ABSOLUTE_Y=20;
    public static final int ABSOLUTE_W=200;
    public static final int ABSOLUTE_H=100;



    public PirateAreaView(Game game) {
        super(game);
        this.game.getPirate().setPosX(AREA_START_X);
        this.game.getPirate().setPosY(AREA_START_Y);

    }

    public void drawArea(Graphics2D g2){
        g2.setColor(Color.GRAY);
        g2.fill(new Rectangle2D.Double(AREA_START_X, AREA_START_Y, AREA_W, AREA_H/4));

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("SansSerif", Font.PLAIN, 18));
        g2.drawString("海盗区域", AREA_START_X+20, AREA_START_Y+20);
        g2.drawString("海盗老大", AREA_START_X+20, AREA_START_Y+40);
        g2.drawString("海盗小弟", AREA_START_X+POS_W+POS_INTERVAL, AREA_START_Y+40);

        Position[] positions = this.game.getPirate().getPos_list();
        this.drawPosition(g2,positions);

    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        this.drawArea(g2);
    }
}
