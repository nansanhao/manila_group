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

    public static final int ABSOLUTE_X=13*(PlaygroundView.SEA_W+PlaygroundView.SEA_INTERVAL)+PlaygroundView.SEA_START_X-POS_W-POS_INTERVAL;

    public static final int ABSOLUTE_W=3*AreaView.POS_INTERVAL+2*AreaView.POS_W;




    public PirateAreaView(Game game) {
        super(game);

    }

    public void drawArea(Graphics2D g2){
        g2.setColor(Color.GRAY);
        g2.fill(new Rectangle2D.Double(0, 0, ABSOLUTE_W, ABSOLUTE_H));

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("SansSerif", Font.PLAIN, 18));
        g2.drawString("海盗船", POS_INTERVAL, 20);
        g2.drawString("海盗老大", POS_INTERVAL, 50);
        g2.drawString("海盗小弟", POS_W+2*POS_INTERVAL, 50);

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
