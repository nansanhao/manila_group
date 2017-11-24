package manila.view;

import manila.model.Game;
import manila.model.Position;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class AvigatorView extends AreaView {
    protected static final int ABSOLUTE_X=6*(PlaygroundView.SEA_W+PlaygroundView.SEA_INTERVAL)+PlaygroundView.BOAT_W+10;
    protected static final int ABSOLUTE_Y=20;
    protected static final int ABSOLUTE_W=220;
    protected static final int ABSOLUTE_H=100;


    public AvigatorView(Game game) {
        super(game);
        this.game.getAvigator().setPosX(ABSOLUTE_X);
        this.game.getAvigator().setPosY(ABSOLUTE_Y);

    }

    public void drawArea(Graphics2D g2){
        g2.setColor(Color.GRAY);
        g2.fill(new Rectangle2D.Double(AREA_START_X, AREA_START_Y, ABSOLUTE_W, ABSOLUTE_H));

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("SansSerif", Font.PLAIN, 18));
        g2.drawString("领航员区域", AREA_START_X+20, AREA_START_Y+20);

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
