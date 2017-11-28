package manila.view;

import manila.model.Game;
import manila.model.Position;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * 保险公司的区域View
 */
public class InsuranceAreaView extends AreaView {

    public static final int ABSOLUTE_X=3*(PlaygroundView.SEA_W+PlaygroundView.SEA_INTERVAL);
    public static final int ABSOLUTE_Y=20;
    public static final int ABSOLUTE_W=AreaView.POS_W+2*AreaView.POS_INTERVAL;
    public static final int ABSOLUTE_H=100;




    public InsuranceAreaView(Game game) {
        super(game);

    }

    @Override
    public void drawArea(Graphics2D g2) {
        g2.setColor(Color.GRAY);
        g2.fill(new Rectangle2D.Double(AREA_START_X, AREA_START_Y, AREA_W, AREA_H/4));

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("SansSerif", Font.PLAIN, 18));
        g2.drawString("保险公司", AREA_START_X+20, AREA_START_Y+20);
        g2.drawString("保险职员--10$", AREA_START_X+20, AREA_START_Y+40);

        Position[] positions=this.game.getInsurance().getPos_list();
        this.drawPosition(g2,positions);

    }

    public void drawPosition(Graphics2D g2,Position[] pos_list){

            if(pos_list[0].getSailorID() == -1){
                g2.setColor(Color.WHITE);
                Rectangle2D r_pos = new Rectangle2D.Double(POS_START_X, POS_START_Y, POS_W, POS_H);
                g2.fill(r_pos);
                g2.setColor(Color.BLACK);
                g2.setFont(new Font("SansSerif", Font.PLAIN, 14));
                g2.drawString("!", (int)r_pos.getX()+POS_W/2-4, (int)r_pos.getY()+POS_H/2+5);
            }
            else{
                g2.setColor(this.game.getPlayerByID(pos_list[0].getSailorID()).getC());
                g2.fill(new Rectangle2D.Double(+POS_START_X, POS_START_Y, POS_W, POS_H));
            }
    }
}
