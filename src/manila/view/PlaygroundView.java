package manila.view;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import manila.controller.GameController;
import manila.model.Boat;
import manila.model.Game;
import manila.model.Position;

/**
 * 游戏场景界面类
 */
public class PlaygroundView extends JPanel {

	private PirateAreaView pirateAreaView;
	private InsuranceAreaView insuranceAreaView;
	private ShipYardView shipYardView;

	private AvigatorView avigatorView;
	private HarbourView harbourView;

	/** 游戏场景宽度 */
	public static final int GROUND_W = 1300;
	/** 游戏场景高度 */
	public static final int GROUND_H = 900;
	
	/** 一段航程在界面上的长度（直线的间隔） */
	public static final int SEA_INTERVAL = 40;
	/** 第一条大海线段的起点x坐标*/
	public static final int SEA_START_X = 200;
	/** 第一条大海线段的起点y坐标 */
	public static final int SEA_START_Y = 200;
	/** 每一格海的宽 */
	public static final int SEA_W = 20;
	/**  每一格海的长*/
	public static final int SEA_L =680;
	
	/** 一条小船的宽度 */
	public static final int BOAT_W = 180;
	/** 一条小船的高度 */
	public static final int BOAT_H = 100;
	/** 船之间的竖向间隔 */
	public static final int BOAT_DISTANCE =(SEA_L-3*BOAT_H)/4;
	/** 最上边小船左上角x坐标 */
	public static final int BOAT_START_X = SEA_START_X-BOAT_W+SEA_W/2;
	/** 最上边小船左上角y坐标 */
	public static final int BOAT_START_Y = SEA_START_Y+BOAT_DISTANCE;
	
	/** 小船上位置的宽度 */
	private static final int POS_W = 25;
	/** 小船上位置的高度 */
	private static final int POS_H = 60;
	/** 小船上最右位置左上角的x坐标 */
	private static final int POS_START_X = BOAT_W-35-POS_W;
	/** 小船上最右位置左上角的y坐标 */
	private static final int POS_START_Y = 20;
	/** 小船上位置间在X方向上的间隔 */
	private static final int POS_INTERVAL = 10;
	
	private Game game;


	public PlaygroundView(Game g){
		this.game = g;
		this.setPreferredSize(new Dimension(GROUND_W, GROUND_H));
		this.setLayout(null);
		this.addMouseListener(new GameController(this.game));


		this.pirateAreaView=new PirateAreaView(this.game);
		this.insuranceAreaView=new InsuranceAreaView(this.game);
		this.shipYardView=new ShipYardView(this.game);

		this.avigatorView=new AvigatorView(this.game);
		this.harbourView=new HarbourView(this.game);


		this.insuranceAreaView.setBounds(InsuranceAreaView.ABSOLUTE_X,InsuranceAreaView.ABSOLUTE_Y,InsuranceAreaView.ABSOLUTE_W,InsuranceAreaView.ABSOLUTE_H);
		this.pirateAreaView.setBounds(PirateAreaView.ABSOLUTE_X,PirateAreaView.ABSOLUTE_Y,PirateAreaView.ABSOLUTE_W,PirateAreaView.ABSOLUTE_H);
		this.shipYardView.setBounds(ShipYardView.ABSOLUTE_X,ShipYardView.ABSOLUTE_Y,ShipYardView.ABSOLUTE_W,ShipYardView.ABSOLUTE_H);

		this.harbourView.setBounds(HarbourView.ABSOLUTE_X,HarbourView.ABSOLUTE_Y,HarbourView.ABSOLUTE_W,HarbourView.ABSOLUTE_H);

		this.avigatorView.setBounds(AvigatorView.ABSOLUTE_X,AvigatorView.ABSOLUTE_Y,AvigatorView.ABSOLUTE_W,AvigatorView.ABSOLUTE_H);

		this.add(pirateAreaView);
		this.add(insuranceAreaView);
		this.add(shipYardView);
		this.add(harbourView);
		this.add(avigatorView);

	}


	public void paintComponent(Graphics g) {
        super.paintComponent(g);       
    }
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	    Graphics2D g2 = (Graphics2D) g;
	    
	    this.drawSea(g2);
	    this.drawBoats(g2);
	}
	
	/**
	 * 画出大海（一组线段）
	 * @param g2  图形类
	 */
	public void drawSea(Graphics2D g2){
		g2.setFont(new Font("SansSerif", Font.PLAIN, 20));
		for(int i=0;i<=Game.SEA_LENGTH;i++){

			g2.setColor(Color.CYAN);
			g2.fill(new Rectangle2D.Double(SEA_START_X+i*(SEA_INTERVAL+SEA_W), SEA_START_Y, SEA_W, SEA_L));
			g2.setColor(Color.BLACK);
	    	g2.drawString(i+"", SEA_START_X+i*(SEA_INTERVAL+SEA_W), SEA_START_Y);
		}
	}
	
	/**
	 * 根据小船的信息在界面上画出一条小船以及船上的所有位置
	 * @param g2 图形类
	 * @param b 一个小船对象
	 */
	public void drawBoat(Graphics2D g2, Boat b,int boat_pos_X,int boat_pos_Y){

		g2.setColor(Color.lightGray);
		Rectangle2D r=new Rectangle2D.Double(boat_pos_X, boat_pos_Y, BOAT_W, BOAT_H);
		g2.fill(r);
		
		g2.setColor(Color.BLACK);
		g2.setFont(new Font("SansSerif", Font.PLAIN, 18));
		g2.drawString(b.getCargo_value()+"", boat_pos_X+BOAT_W-30, boat_pos_Y+BOAT_H/2+4);
		
		Position[] pos_list = b.getPos_list();
		for(int i=0; i<pos_list.length; i++){
			if(pos_list[i].getSailorID() == -1){
				g2.setColor(Color.WHITE);
				Rectangle2D r_pos = new Rectangle2D.Double(boat_pos_X+POS_START_X-i*(POS_W+POS_INTERVAL),
						boat_pos_Y+POS_START_Y,
						POS_W, POS_H);
				g2.fill(r_pos);
				g2.setColor(Color.BLACK);
				g2.setFont(new Font("SansSerif", Font.PLAIN, 14));
				g2.drawString(pos_list[i].getPrice()+"", (int)r_pos.getX()+POS_W/2-4, (int)r_pos.getY()+POS_H/2+5);
			}
			else{
				g2.setColor(this.game.getPlayerByID(pos_list[i].getSailorID()).getC());
				g2.fill(new Rectangle2D.Double(boat_pos_X+POS_START_X-i*(POS_W+POS_INTERVAL),
						boat_pos_Y+POS_START_Y,
						POS_W, POS_H));
			}
		}
		if(b.isChoosen()){
			g2.setStroke(new BasicStroke(5));
			g2.setColor(Color.orange);
			g2.drawRect(boat_pos_X, boat_pos_Y, BOAT_W, BOAT_H);
		}
	}
	
	/**
	 * 画出所有的小船
	 * @param g2 图形类
	 */
	public void drawBoats(Graphics2D g2){
		Boat[] boats = this.game.getBoats();
		for(int i=0;i<boats.length;i++){
			if(boats[i].getBoatId()!=-1) { //-1指没有出船
				if (boats[i].getHarbourID() == -1 && boats[i].getShipYardID() == -1) {
					drawBoat(g2, boats[i],
							BOAT_START_X + boats[i].getPos_in_the_sea() * (SEA_W + SEA_INTERVAL),
							BOAT_START_Y + boats[i].getBoatId() * (BOAT_DISTANCE + BOAT_H));
				} else if (boats[i].getShipYardID() != -1) {
					drawBoat(g2, boats[i],
							ShipYardView.SHIP_POS_START_X,
							ShipYardView.SHIP_POS_START_Y + boats[i].getShipYardID() * (ShipYardView.INTERVAL + BOAT_H));
				} else if (boats[i].getHarbourID() != -1) {
					drawBoat(g2, boats[i],
							HarbourView.SHIP_POS_START_X,
							HarbourView.SHIP_POS_START_Y + boats[i].getHarbourID() * (HarbourView.INTERVAL + BOAT_H));
				}
			}
		}
	}
}
