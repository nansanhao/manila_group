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
	/** 船在海中的位置 */
	private int pos_in_the_sea;
	/**船处在哪个港口*/
	private int harbourID;
	/**船再哪个修船厂*/
	private int shipYardID;
	/**是否被选中*/
	private boolean isChoosen;


	/**船的号码第几艘船*/
	private int boatId;


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
		this.shipYardID=-1;
		this.harbourID=-1;
		this.boatId=-1;
		this.isChoosen=false;

	}

	public int getHarbourID() {
		return harbourID;
	}

	public void setHarbourID(int harbourID) {
		this.harbourID = harbourID;
	}

	public int getShipYardID() {
		return shipYardID;
	}

	public void setShipYardID(int shipYardID) {
		this.shipYardID = shipYardID;
	}

	public int getBoatId() {
		return boatId;
	}

	public void setBoatId(int boatId) {
		this.boatId = boatId;
	}

	public String getCargo_name() {
		return cargo_name;
	}

	public int getCargo_value() {
		return cargo_value;
	}

	public int getPos_in_the_sea() {
		return pos_in_the_sea;
	}

	public void setPos_in_the_sea(int pos_in_the_sea) {
		this.pos_in_the_sea = pos_in_the_sea;
	}

	public boolean isChoosen() {
		return isChoosen;
	}

	public void setChoosen(boolean choosen) {
		isChoosen = choosen;
	}

	/**
	 * 使船在海中前进，更新船在海中的位置和在船在图形界面上的位置
	 * @param step 船在海中前进的步数
	 */
	public void move(int step){
		this.pos_in_the_sea += step;
	}

	@Override
	public void playerGetProfit(Game game) {
		Player[] players = game.getPlayers();
		int money_to_share;
		String s = new String("");
		if (getAvailPosIndex() != 0) { //如果船不为空才能算钱
			System.out.println(this.cargo_name + "船运送成功！");
			money_to_share = this.getCargo_value() / this.getFilledPosNum();
			for (Position pos : this.getPos_list()) {
				if (pos.getSailorID() != -1) {//-1是空位
					players[pos.getSailorID()].receiveProfit(money_to_share);
					s += "玩家" + players[pos.getSailorID()].getName() + "获得" + money_to_share + "$\n";
				}
			}
			System.out.println(s);
		}
	}

}
