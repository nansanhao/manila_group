package manila.view;

import manila.model.Game;
import manila.model.Position;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class InsuranceAreaView extends AreaView {

    public InsuranceAreaView(Game game) {
        super(game);
        this.game.getInsurance().setPosX(AREA_START_X);
        this.game.getInsurance().setPosY(AREA_START_Y);
    }
    @Override
    public void drawArea(Graphics2D g2) {
        g2.setColor(Color.GRAY);
        g2.fill(new Rectangle2D.Double(AREA_START_X, AREA_START_Y, AREA_W, AREA_H/4));

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("SansSerif", Font.PLAIN, 18));
        g2.drawString("保险公司", AREA_START_X+20, AREA_START_Y+20);
        g2.drawString("保险职员", AREA_START_X+20, AREA_START_Y+40);

        Position[] positions=this.game.getInsurance().getPos_list();
        this.drawPosition(g2,positions);

    }
}
