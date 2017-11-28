package manila.view;

import manila.model.Game;
import manila.model.Shares;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class BlackMarketView extends JPanel {



    /**第一格股票的X*/
    public static final int BLOCK_START_X=10;
    /**第一格股票的Y*/
    public static final int BLOCK_START_Y =10;
    /**每一格股票的边长*/
    public static final int BLOCK_LENGTH =50;
    /**每一格股票的间隔*/
    public static final int BLOCK_INTERVAL =6;
    /**View 的高度 */
    public static final int View_H=4*BLOCK_LENGTH+3*BLOCK_INTERVAL+2*BLOCK_START_Y;

    private Game game;

    public BlackMarketView(Game game){
        this.game=game;
        this.setBackground(Color.GREEN);
        this.setPreferredSize(new Dimension(350,View_H));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        this.drawShares(g2);
    }

    private void drawShares(Graphics2D g2) {
        int block_x,block_y;
        String price;
        Shares[][] shares=this.game.getaBlackMarket().getCargo_shares();
        for(int i=0;i<shares.length;i++){
            for(int j=0;j<6;j++){  //6为6种价格
                if(0<j&&j<=3)
                    price=String.valueOf((j-1)*5);
                else if(j>3)
                    price=String.valueOf(20+(j-4)*10);
                else
                    price=shares[i][0].getCargo_name();
                block_y=BLOCK_START_Y+i*(BLOCK_INTERVAL+BLOCK_LENGTH);
                block_x=BLOCK_START_X+j*(BLOCK_INTERVAL+BLOCK_LENGTH);
                g2.setColor(Color.WHITE);
                Rectangle2D r_pos = new Rectangle2D.Double(block_x,block_y,BLOCK_LENGTH,BLOCK_LENGTH);
                g2.fill(r_pos);
                g2.setColor(Color.BLACK);
                g2.setFont(new Font("SansSerif", Font.PLAIN, 14));
                g2.drawString(price+"", (int)r_pos.getX()+BLOCK_LENGTH/2-10, (int)r_pos.getY()+BLOCK_LENGTH/2+5);
            }
        }
    }
}
