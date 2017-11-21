package manila.model;

import manila.view.PlayerView;
import manila.view.PlaygroundView;

/**
 * 小船类，船上装有货物，船上有位置可以装海员。
 */
public class Boat extends Area{
	/** 船上的货物名 */
	private String cargo_name;
	/** 货物的总价值 */
	private int cargo_value;
	/** 船上的空位数组 */
	//private Position[] pos_list;
	/** 船在海中的位置 */
	private int pos_in_the_sea;
	
	/** 船（左上角）在图形界面上的x坐标 */
	//private int posX;
	/** 船（左上角）在图形界面上的y坐标 */
	//private int posY;
	/**是否被截获*/
	boolean isRobbed;
	
	/**
	 * 小船构造函数
	 * @param n 货物名
	 * @param v 货物价值
	 * @param pl 一组位置
	 */
	public Boat(String n, int v, Position[] pl){
		this.cargo_name = n;
		this.cargo_value = v;
		this.pos_list = pl;
		this.pos_in_the_sea = 0;
	}

	public String getCargo_name() {
		return cargo_name;
	}

	public void setCargo_name(String cargo_name) {
		this.cargo_name = cargo_name;
	}

	public int getCargo_value() {
		return cargo_value;
	}

	public void setCargo_value(int cargo_value) {
		this.cargo_value = cargo_value;
	}

	public int getPos_in_the_sea() {
		return pos_in_the_sea;
	}

	public void setPos_in_the_sea(int pos_in_the_sea) {
		this.pos_in_the_sea = pos_in_the_sea;
	}

	/**
	 * 使船在海中前进，更新船在海中的位置和在船在图形界面上的位置
	 * @param step 船在海中前进的步数
	 */
	public void move(int step){
		this.pos_in_the_sea += step;
		this.setPosX(this.getPosX()+step * (PlaygroundView.SEA_INTERVAL+ PlaygroundView.SEA_W));
	}

	/**
	 * 船是否被海盗截获
	 * @return
	 */
	public boolean isRobbed(){
		// TODO: 2017/11/17 比较船当前位置与海的海盗位置(Game的静态变量：Game.SEA_LENGTH)：

		return false;
	}
	@Override
	public void playerGetProfit(Game game) {
		Player[] players=game.getPlayers();
		int money_to_share;
		System.out.println("The boat "+this.getCargo_name()+" has arrived");
		money_to_share = this.getCargo_value()/this.getFilledPosNum();
		System.out.println("money_to_share: "+money_to_share);
		for(Position pos : this.getPos_list()){
			if(pos.getSailorID() != -1)//-1是空位
				players[pos.getSailorID()].receiveProfit(money_to_share);
		}
	}
}
