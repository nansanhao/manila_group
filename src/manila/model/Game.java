package manila.model;

import java.awt.Color;
import java.util.Random;

import manila.view.GameView;

/**
 * Manila 游戏的主要类，包含玩家信息和每条船的信息。
 */
public class Game {
	/** 玩家数组 */
	private Player[] players;
	/** 小船数组 */
	private Boat[] boats;
	/** 随机数产生器 */
	private Random dice_generator;
	
	/** 当前是否处于玩家选位置的阶段 */
	private boolean choosing;
	/** 游戏是否已结束 */
	private boolean gameIsOver;
	/** 当前游戏处于第几轮 */
	private int current_round;
	/** 当前正在选位置的玩家ID */
	private int current_pid;
	/** 被选为船老大的玩家ID */
	private int boss_pid;
	
	/** 游戏的总轮数 */
	public static final int ROUND_NUMBER = 3;
	/** 海路的总长度 */
	public static final int SEA_LENGTH = 8;
	
	private GameView gameV;
	
	public Game(GameView gv){
		this.gameV = gv;
		
		int[] prices1 = {3,4,5,5};
		int[] prices2 = {2,3,3};
		int[] prices3 = {3,4,5};
		Position[] pos1 = new Position[prices1.length];
		Position[] pos2 = new Position[prices2.length];
		Position[] pos3 = new Position[prices3.length];
		
		for(int i=0;i<prices1.length;i++){
			pos1[i] = new Position(prices1[i]);
		}
		for(int i=0;i<prices2.length;i++){
			pos2[i] = new Position(prices2[i]);
		}
		for(int i=0;i<prices3.length;i++){
			pos3[i] = new Position(prices3[i]);
		}
		
		Boat s1 = new Boat("丝绸", 36, pos1);
		Boat s2 = new Boat("可可",18, pos2);
		Boat s3 = new Boat("玉器", 30, pos3);
		this.boats = new Boat[3];
		boats[0] = s1;
		boats[1] = s2;
		boats[2] = s3;
		
		this.dice_generator = new Random();
		this.current_pid = 0;
		this.boss_pid = -1;
		this.current_round = 0;
		this.choosing = true;
		this.gameIsOver = false;
		
		this.players = new Player[3];
		this.players[0] = new Player("路飞", 0, Color.RED);
		this.players[1] = new Player("杰克", 1, Color.GREEN);
		this.players[2] = new Player("哥伦布", 2, Color.BLUE);
	}
	
	/**
	 * 产生一个1-6之间的随机整数（模拟骰子的功能）。
	 * @return 1-6之间的随机整数
	 */
	public int rollDice(){
		return dice_generator.nextInt(5)+1;
	}
	
	/**
	 * 返回当前正在进行操作的玩家对象。
	 * @return 当前玩家对象
	 */
	public Player getCurrentPlayer(){
		return this.players[this.current_pid];
	}
	
	/**
	 * 根据玩家的ID返回玩家对象。
	 * @param id 要寻找的玩家ID
	 * @return 玩家对象
	 */
	public Player getPlayerByID(int id){
		return this.players[id];
	}
	
	public void showCurrentState(){
		for(Boat s : this.boats){
			String res;
			res = "The "+s.getCargo_name()+" boat ("+s.getCargo_value()+"): [ ";
			for(Position pos: s.getPos_list()){
				if(pos.getSailorID() == -1)
					res += pos.getPrice()+"$ ";
				else
					res += this.players[pos.getSailorID()].getName()+" ";
			}
			
			res += "].";
			res += "The boat is at: "+s.getPos_in_the_sea(); 
			System.out.println(res);
		}
	}
	
	/**
	 * 在所有轮结束后，根据船是否到港以及船上海员的归属，为每位玩家分配收益。
	 */
	public void calculateProfits(){
		// for boats that get to the harbor
		// share the money by selling the cargo
		int money_to_share;
		for(Boat s : this.boats){
			if(s.getPos_in_the_sea() > SEA_LENGTH){
				System.out.println("The boat "+s.getCargo_name()+" has arrived");
				money_to_share = s.getCargo_value()/s.getFilledPosNum();
				System.out.println("money_to_share: "+money_to_share);
				for(Position pos : s.getPos_list()){
					if(pos.getSailorID() != -1)
						this.players[pos.getSailorID()].receiveProfit(money_to_share);
				}
			}
			else
				System.out.println("The boat "+s.getCargo_name()+" has sank!");
		}
		
		for(Player p : this.players)
			this.gameV.updatePlayersView(p.getPid(), false);
	}
	
	/**
	 * 在终端打印出谁是获胜玩家，即得分最高（账户余额最高）。
	 */
	public void showWinner(){
		int winner_id = 0;
		int high_balance = -1;
		for(Player p : this.players){
			if(p.getAccount_balance() > high_balance){
				winner_id = p.getPid();
				high_balance = p.getAccount_balance();
			}
			System.out.println(p.getName()+" has "+p.getAccount_balance()+"$");
		}
		System.out.println("The winner is: "+this.players[winner_id].getName());
	}

	public void switchPlayer(){
		this.current_pid = (this.current_pid+1)%this.players.length;
	}
	
	public Player[] getPlayers() {
		return players;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
	}

	public Boat[] getBoats() {
		return boats;
	}

	public void setBoats(Boat[] boats) {
		this.boats = boats;
	}

	public boolean isChoosing() {
		return choosing;
	}

	public void setChoosing(boolean choosing) {
		this.choosing = choosing;
	}

	public int getCurrent_pid() {
		return current_pid;
	}

	public void setCurrent_pid(int current_pid) {
		this.current_pid = current_pid;
	}

	public boolean isGameIsOver() {
		return gameIsOver;
	}

	public void setGameIsOver(boolean gameIsOver) {
		this.gameIsOver = gameIsOver;
	}

	public int getCurrent_round() {
		return current_round;
	}

	public void setCurrent_round(int current_round) {
		this.current_round = current_round;
	}

	public GameView getGameV() {
		return gameV;
	}

	public void setGameV(GameView gameV) {
		this.gameV = gameV;
	}

	public int getBoss_pid() {
		return boss_pid;
	}

	public void setBoss_pid(int boss_pid) {
		this.boss_pid = boss_pid;
	}
	
	

}
