package manila.view;

import manila.model.Game;
import manila.model.Position;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class ShipYardView extends AreaView {
    public static final int ABSOLUTE_X=15*(PlaygroundView.SEA_W+PlaygroundView.SEA_INTERVAL)+PlaygroundView.BOAT_W+10;
    public static final int ABSOLUTE_Y=PlaygroundView.SEA_START_Y+PlaygroundView.SEA_L/2;
    public static final int ABSOLUTE_W=220;
    public static final int ABSOLUTE_H=PlaygroundView.SEA_L/2;


    public static final int SHIP_POS_START_X=POS_START_X+120;
    public static final int SHIP_POS_START_Y=POS_START_Y;


    public static final int SHIP_POS_INTERVAL_Y =70;




    public ShipYardView(Game game) {
        super(game);
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

    @Override
    public void drawPosition(Graphics2D g2, Position[] pos_list) {
        for(int i=0; i<pos_list.length; i++){
            if(pos_list[i].getSailorID() == -1){
                g2.setColor(Color.WHITE);
                Rectangle2D r_pos = new Rectangle2D.Double(SHIP_POS_START_X,
                        SHIP_POS_START_Y+i*(POS_H+ SHIP_POS_INTERVAL_Y),
                        POS_W, POS_H);
                g2.fill(r_pos);
                g2.setColor(Color.BLACK);
                g2.setFont(new Font("SansSerif", Font.PLAIN, 14));
                g2.drawString(pos_list[i].getPrice()+"", (int)r_pos.getX()+POS_W/2-4, (int)r_pos.getY()+POS_H/2+5);
            }
            else{
                g2.setColor(this.game.getPlayerByID(pos_list[i].getSailorID()).getC());
                g2.fill(new Rectangle2D.Double(+SHIP_POS_START_X,
                        SHIP_POS_START_Y+i*(POS_H+ SHIP_POS_INTERVAL_Y),
                        POS_W, POS_H));
            }
        }
    }
}
