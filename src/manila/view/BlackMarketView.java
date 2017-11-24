package manila.view;

import manila.model.Game;
import manila.model.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class BlackMarketView extends JPanel{

    protected Game game;

    public BlackMarketView(Game game) {
        this.game=game;
    }

    /**
     * 画出该区域，每个子类要画的东西不同，所以是个抽象方法。
     *
     * @param g2
     */
    public void drawArea(Graphics2D g2) {
        g2.setColor(Color.GRAY);
        // TODO: 2017/11/24  画黑市
//        g2.fill(new Rectangle2D.Double(, AREA_START_Y, ABSOLUTE_W, ABSOLUTE_H));
//
//        g2.setColor(Color.BLACK);
//        g2.setFont(new Font("SansSerif", Font.PLAIN, 18));
//        g2.drawString("领航员区域", AREA_START_X+20, AREA_START_Y+20);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        this.drawArea(g2);
    }
}
