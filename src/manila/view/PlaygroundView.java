package manila.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	/** 游戏场景宽度 */
	private static final int GROUND_W = 1300;
	/** 游戏场景高度 */
	private static final int GROUND_H = 900;
	
	/** 一段航程在界面上的长度（直线的间隔） */
	public static final int SEA_INTERVAL = 40;
	/** 第一条大海线段的起点x坐标*/
	public static final int SEA_START_X = 200;
	/** 第一条大海线段的起点y坐标 */
	private static final int SEA_START_Y = 200;
	/** 每一格海的宽 */
	public static final int SEA_W = 20;
	/**  每一格海的长*/
	private static final int SEA_L =600;
	
	/** 一条小船的宽度 */
	public static final int BOAT_W = 180;
	/** 一条小船的高度 */
	public static final int BOAT_H = 100;
	/** 船之间的竖向间隔 */
	private static final int BOAT_DISTANCE =(SEA_L-3*BOAT_H)/4;
	/** 最上边小船左上角x坐标 */
	public static final int BOAT_START_X = SEA_START_X-BOAT_W+SEA_W/2;
	/** 最上边小船左上角y坐标 */
	private static final int BOAT_START_Y = SEA_START_Y+BOAT_DISTANCE;
	
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
		
		this.initBoats();

		this.pirateAreaView=new PirateAreaView(this.game);
		this.insuranceAreaView=new InsuranceAreaView(this.game);
		this.insuranceAreaView.setBounds(insuranceAreaView.ABSOLUTE_X,insuranceAreaView.ABSOLUTE_Y,insuranceAreaView.ABSOLUTE_W,insuranceAreaView.ABSOLUTE_H);
		this.pirateAreaView.setBounds(PirateAreaView.ABSOLUTE_X,PirateAreaView.ABSOLUTE_Y,PirateAreaView.ABSOLUTE_W,PirateAreaView.ABSOLUTE_H);
		this.add(pirateAreaView);
		this.add(insuranceAreaView);

	}
	
	/**
	 * 对小船们的位置进行初始化
	 */
	private void initBoats() {
		Boat[] boats = this.game.getBoats();
		for(int i=0;i<boats.length;i++){
			boats[i].setPosX(BOAT_START_X);
			boats[i].setPosY(BOAT_START_Y+i*(BOAT_H+BOAT_DISTANCE));
		}
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
	public void drawBoat(Graphics2D g2, Boat b){
		g2.setColor(Color.GRAY);
		g2.fill(new Rectangle2D.Double(b.getPosX(), b.getPosY(), BOAT_W, BOAT_H));
		
		g2.setColor(Color.BLACK);
		g2.setFont(new Font("SansSerif", Font.PLAIN, 18));
		g2.drawString(b.getCargo_value()+"", b.getPosX()+BOAT_W-30, b.getPosY()+BOAT_H/2+4);
		
		Position[] pos_list = b.getPos_list();
		for(int i=0; i<pos_list.length; i++){
			if(pos_list[i].getSailorID() == -1){
				g2.setColor(Color.WHITE);
				Rectangle2D r_pos = new Rectangle2D.Double(b.getPosX()+POS_START_X-i*(POS_W+POS_INTERVAL),
						b.getPosY()+POS_START_Y,
						POS_W, POS_H);
				g2.fill(r_pos);
				g2.setColor(Color.BLACK);
				g2.setFont(new Font("SansSerif", Font.PLAIN, 14));
				g2.drawString(pos_list[i].getPrice()+"", (int)r_pos.getX()+POS_W/2-4, (int)r_pos.getY()+POS_H/2+5);
			}
			else{
				g2.setColor(this.game.getPlayerByID(pos_list[i].getSailorID()).getC());
				g2.fill(new Rectangle2D.Double(b.getPosX()+POS_START_X-i*(POS_W+POS_INTERVAL),
						b.getPosY()+POS_START_Y,
						POS_W, POS_H));
			}
		}
	}
	
	/**
	 * 画出所有的小船
	 * @param g2 图形类
	 */
	public void drawBoats(Graphics2D g2){
		Boat[] boats = this.game.getBoats();
		for(int i=0;i<boats.length;i++){
			this.drawBoat(g2, boats[i]);
		}
	}
}
