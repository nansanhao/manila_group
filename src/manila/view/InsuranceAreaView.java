package manila.view;

import manila.model.Game;
import manila.model.Position;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * 保险公司的区域View
 */
public class InsuranceAreaView extends AreaView {

    public static final int ABSOLUTE_X=PlaygroundView.SEA_START_X;

    public static final int ABSOLUTE_W=AreaView.POS_W+2*AreaView.POS_INTERVAL;





    public InsuranceAreaView(Game game) {
        super(game);

    }

    @Override
    public void drawArea(Graphics2D g2) {
        g2.setColor(Color.GRAY);

        g2.fill(new Rectangle2D.Double(0, 0, ABSOLUTE_W, ABSOLUTE_H));

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("SansSerif", Font.PLAIN, 18));
        g2.drawString("保险公司", POS_INTERVAL, 20);

        g2.fill(new Rectangle2D.Double(POS_INTERVAL, 30, POS_W, 20));
        g2.setColor(Color.WHITE);
        g2.drawString("10", POS_INTERVAL+POS_W/2-8, 30+16);

        Position[] positions=this.game.getInsurance().getPos_list();
        this.drawPosition(g2,positions);

    }

    public void drawPosition(Graphics2D g2,Position[] pos_list){

            if(pos_list[0].getSailorID() == -1){
                g2.setColor(Color.WHITE);
                Rectangle2D r_pos = new Rectangle2D.Double(POS_START_X, POS_START_Y, POS_W, POS_H);
                g2.fill(r_pos);
                g2.setColor(Color.BLACK);
                g2.setFont(new Font("SansSerif", Font.PLAIN, 20));
                g2.drawString("!", (int)r_pos.getX()+POS_W/2-4, (int)r_pos.getY()+POS_H/2+5);
            }
            else{
                g2.setColor(this.game.getPlayerByID(pos_list[0].getSailorID()).getC());
                g2.fill(new Rectangle2D.Double(+POS_START_X, POS_START_Y, POS_W, POS_H));
            }
    }
}
