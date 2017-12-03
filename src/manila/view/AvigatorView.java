package manila.view;

import manila.model.Game;
import manila.model.Position;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class AvigatorView extends AreaView {


    public static final int ABSOLUTE_X=7*(PlaygroundView.SEA_W+PlaygroundView.SEA_INTERVAL)+PlaygroundView.SEA_START_X-POS_W-POS_INTERVAL;

    public static final int ABSOLUTE_W=3*AreaView.POS_INTERVAL+2*AreaView.POS_W;


    public  AvigatorView(Game game){
        super(game);
    }
    @Override
    public void drawArea(Graphics2D g2) {
        g2.setColor(Color.GRAY);
        g2.fill(new Rectangle2D.Double(0, 0, ABSOLUTE_W, ABSOLUTE_H));

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("SansSerif", Font.PLAIN, 18));
        g2.drawString("小领航员", POS_INTERVAL, 50);
        g2.drawString("大领航员", POS_W+2*POS_INTERVAL, 50);

        Position[] positions = this.game.getAvigator().getPos_list();
        this.drawPosition(g2,positions);

    }
}
