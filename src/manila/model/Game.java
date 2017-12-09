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
	/**海盗区*/
	private Pirate pirate;
	/**领航员区*/
	private Avigator avigator;
	/**保险公司*/
	private Insurance insurance;
	/**修船厂*/
	private ShipYard shipYard;
	/**港口*/
	private Harbour harbour;


	/** 随机数产生器 */
	private Random dice_generator;


	/**判断游戏是否开始 用于修复船长*/
	private boolean gameIsStart;
	/** 当前是否处于玩家选位置的阶段 */
	private boolean choosing;
	/** 游戏是否已结束 */
	private boolean gameIsOver;
	/** 航程是否已结束 */
	private boolean voyageIsOver;
	/** 是否在选船下海 */
	private boolean isSettingBoat;
	/** 哪艘船在被选 */
	private int choosingBoatId ;
	/** 是否在选船 用于领航员和海盗*/
	private boolean isChoosingBoat;
	/**领航员是否在工作*/
	private boolean isMovingBoat;
	/**海盗是否在上船*/
	private boolean isRobbing;
	/**海盗是否在放船*/
	private boolean isReturning;

	/** 当前游戏处于第几轮 */
	private int current_round;
	/** 当前正在选位置的玩家ID */
	private int current_pid;
	/** 被选为船老大的玩家ID */
	private int boss_pid;
	/** 当前游戏的黑市*/
	private BlackMarket aBlackMarket;
	/** 游戏的总轮数 */
	public static final int ROUND_NUMBER = 3;
	/** 海路的总长度 */
	public static final int SEA_LENGTH = 13;
	/**下海船的数量 */
	public static final int MAX_BOATS_NUM=3;
	/**单次最大步数*/
	public static final int ONCE_MAX_STEP=5;
	/**总共最大步数*/
	public static final int SUM_MAX_STEP=9;
	/**最大股票价格*/
	public static final int MAX_SHARES_PRICE=30;
	/**对应gameV*/
	private GameView gameV;


	public boolean isVoyageIsOver() {
		return voyageIsOver;
	}

	public BlackMarket getaBlackMarket() {
		return aBlackMarket;
	}

	public void setVoyageIsOver(boolean voyageIsOver) {
		this.voyageIsOver = voyageIsOver;
	}

	public Harbour getHarbour() {
		return harbour;
	}

	public Pirate getPirate() {
		return pirate;
	}

	public Avigator getAvigator() {
		return avigator;
	}

	public Insurance getInsurance() {
		return insurance;
	}

	public ShipYard getShipYard() {
		return shipYard;
	}

	public Player[] getPlayers() {
		return players;
	}

	public boolean isChoosingBoat() {
		return isChoosingBoat;
	}

	public void setChoosingBoat(boolean choosingBoat) {
		isChoosingBoat = choosingBoat;
	}

	public int getChoosingBoatId() {
		return choosingBoatId;
	}

	public void setChoosingBoatId(int choosingBoatId) {
		this.choosingBoatId = choosingBoatId;
	}

	public Boat[] getBoats() {
		return boats;
	}

	public boolean isChoosing() {
		return choosing;
	}

	public boolean isGameIsStart() {
		return gameIsStart;
	}

	public void setGameIsStart(boolean gameIsStart) {
		this.gameIsStart = gameIsStart;
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

	public int getCurrent_round() {
		return current_round;
	}

	public void setCurrent_round(int current_round) {
		this.current_round = current_round;
	}

	public GameView getGameV() {
		return gameV;
	}

	public int getBoss_pid() {
		return boss_pid;
	}

	public void setBoss_pid(int boss_pid) {
		this.boss_pid = boss_pid;
	}

	public boolean isSettingBoat() {
		return isSettingBoat;
	}

	public void setSettingBoat(boolean settingBoat) {
		isSettingBoat = settingBoat;
	}

	public boolean isMovingBoat() {
		return isMovingBoat;
	}

	public void setMovingBoat(boolean movingBoat) {
		isMovingBoat = movingBoat;
	}

	public boolean isRobbing() {
		return isRobbing;
	}

	public boolean isReturning() {
		return isReturning;
	}

	public void setReturning(boolean returning) {
		isReturning = returning;
	}

	public Game(GameView gv){
		this.gameV = gv;


		/**船初始化*/
		int[] prices1 = {3,4,5,5};
		int[] prices2 = {2,3,4};
		int[] prices3 = {3,4,5};
		int[] prices4 ={1,2,3};
		Position[] pos1 = new Position[prices1.length];
		Position[] pos2 = new Position[prices2.length];
		Position[] pos3 = new Position[prices3.length];
		Position[] pos4 = new Position[prices4.length];
		
		for(int i=0;i<prices1.length;i++){
			pos1[i] = new Position(prices1[i]);
		}
		for(int i=0;i<prices2.length;i++){
			pos2[i] = new Position(prices2[i]);
		}
		for(int i=0;i<prices3.length;i++){
			pos3[i] = new Position(prices3[i]);
		}
		for(int i=0;i<prices4.length;i++){
			pos4[i] = new Position(prices4[i]);
		}

		Boat s1 = new Boat("玉器", 36, pos1);
		Boat s2 = new Boat("可可",24, pos2);
		Boat s3 = new Boat("丝绸", 30, pos3);
		Boat s4 = new Boat("人参",18,pos4);

		this.boats = new Boat[4];
		boats[0] = s1;
		boats[1] = s2;
		boats[2] = s3;
		boats[3] = s4;




		/**游戏参数初始化*/
		this.dice_generator = new Random();
		this.current_pid = 0;
		this.boss_pid = -1;
		this.current_round = 0;
		this.choosing = false;
		this.gameIsOver = false;
		this.gameIsStart =false;
		this.voyageIsOver=true;
		this.isSettingBoat =false;
		this.isChoosingBoat=false;
		this.isReturning=false;
		this.isRobbing=false;
		this.choosingBoatId =-1;

		/**玩家初始化*/
		this.players = new Player[3];
		this.players[0] = new Player("路飞", 0, Color.RED);
		this.players[1] = new Player("杰克", 1, Color.GREEN);
		this.players[2] = new Player("哥伦布", 2, Color.BLUE);
		// TODO: 2017/11/19 黑市的初始化和分发股票 何剑冲完成

		this.aBlackMarket=new BlackMarket();
		this.aBlackMarket.distributeShares(this.players);



		/**海盗区域初始化*/
		this.pirate=new Pirate();
		/**保险公司初始化*/
		this.insurance=new Insurance();
		/**修船厂初始化*/
		this.shipYard=new ShipYard();

		/**港口初始化*/
		this.harbour=new Harbour();
		/**领航员初始化*/
		this.avigator=new Avigator();
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
	
	/**
	 * 一次航程结束之后，结算所有区域及到港的钱
	 */
	public void calculateProfits(){
		//结算将在其他功能逐步完善之后慢慢添加
		// TODO: 2017/11/19 海盗结算 完成
		// TODO: 2017/11/19 保险公司结算 完成
		// TODO: 2017/11/19 修船厂/港口结算 12.1完成
		System.out.println(">-------------------------------------------------");
		for(Boat s : this.boats){
			if(s.getPos_in_the_sea() > SEA_LENGTH){
				s.playerGetProfit(this);
			}
			if(s.getHarbourID()!=-1){//即进港了
				this.getaBlackMarket().updatePrice(s.getCargo_name());
			}
		}
		this.shipYard.playerGetProfit(this);
		this.harbour.playerGetProfit(this);
		this.insurance.playerGetProfit(this);
		this.pirate.playerGetProfit(this);

		
		for(Player p : this.players)
			this.gameV.updatePlayersView(p.getPid(), false);
	}
	
	/**
	 * 游戏结束时
	 * 在日志板打印出谁是获胜玩家，即得分最高（账户余额最高）。
	 */
	public void showWinner(){
		int winner_id = 0;
		int high_balance = -1;
		System.out.println(">-------------------------------------------------");
		System.out.println("游戏结束！");
		for(Player p : this.players){
			if(p.getFinalMoney() > high_balance){
				winner_id = p.getPid();
				high_balance = p.getFinalMoney();
			}
			System.out.println(p.getName()+" 有 "+p.getFinalMoney()+"$");
		}
		System.out.println("胜者是: "+this.players[winner_id].getName());
	}

	/**
	 * 每次放置同伙后
	 * 切换到下一个玩家
	 */
	public void switchPlayer(){
		this.current_pid = (this.current_pid+1)%this.players.length;
	}

	/**
	 * 将参数初始化 进入新航程 参考构造器，完成后在构造器中直接调用这个函数
	 */
	public void newVoyage() {
		// TODO: 2017/11/19  完成航程循环，重置部分游戏数据：何剑冲
		//游戏数据包括船位置，船老大，和一些其他，将会在功能逐步完善之后逐步加入
		this.current_round = 0;
		this.choosing = false;
		this.voyageIsOver = false;
		this.gameIsStart=false;

		//位置全部清空
		for(Position p:this.pirate.pos_list){
			p.setSailorID(-1);
		}
		for(Position p:this.shipYard.pos_list){
			p.setSailorID(-1);
		}
		for(Position p:this.harbour.pos_list){
			p.setSailorID(-1);
		}
		for(Position p:this.avigator.pos_list) {
			p.setSailorID(-1);
		}
		this.insurance.pos_list[0].setSailorID(-1);


		//船全部清空
		for(BoatPosition p:this.shipYard.boatPositions){
			p.setHaveBoat(false);
		}
		for(BoatPosition p:this.harbour.boatPositions){
			p.setHaveBoat(false);
		}

		//船重置
		for (Boat b:this.boats){
			b.setPos_in_the_sea(0);
			b.setHarbourID(-1);
			b.setShipYardID(-1);
			b.setBoatId(-1);
			for(Position p:b.getPos_list()){
				p.setSailorID(-1);
			}
		}
		for(Player p:this.players){
			p.setWorker_nb(3);
		}
	}

	/**
	 * 通过船是第几艘获取船的引用
	 * @param id 船的号码
	 * @return 船的引用
	 */
	public Boat getBoatByID(int id){
		for(Boat b:this.boats){
			if(b.getBoatId()==id)
				return b;
		}
		return null;
	}

	/**
	 * 第三回合是船要上岸 若大于13到港口 小于13到船厂 等于13保留
	 */
	public void boatLand() {
		for(Boat b:this.boats){
			if(b.getBoatId()!=-1){
				if(b.getPos_in_the_sea()>SEA_LENGTH){
					int i=this.harbour.getAvailBoatPosIndex();
					b.setHarbourID(i);
					this.harbour.boatPositions[i].setHaveBoat(true);
				}
				else if(b.getPos_in_the_sea()<SEA_LENGTH){
					int i=this.shipYard.getAvailBoatPosIndex();
					b.setShipYardID(i);
					this.shipYard.boatPositions[i].setHaveBoat(true);
				}
			}
		}
	}

	/**
	 * 判断有没有船到达13格
	 * @return 有返回 true 无返回 false
	 */
	public boolean isRobbed(){
		for(Boat b:this.boats){
			if(b.getPos_in_the_sea()==13)
				return true;
		}
		return false;
	}

	/**
	 * 到达第2/3回合时若有船到达13格将其选中给海盗玩家使用
	 */
	public void showBoats() {
		if (current_round == 2) {
			for(Boat b:boats){
				if(b.getPos_in_the_sea()==13) {
					b.setChoosen(true);
				}
			}
		}
		else if(current_round==ROUND_NUMBER){
			for(Boat b:boats){
				if(b.getPos_in_the_sea()==13) {
					b.setChoosen(true);
					break;
				}
			}
		}

		this.gameV.getPlayground().repaint();
	}

	/**
	 * 切换到领航员玩家
	 */
	public void switchToAvigator(){
		choosing=false;
		isChoosingBoat=true;
		gameV.updatePlayersView(current_pid,false);
		current_pid=(avigator.getFirstId());
		gameV.updatePlayersView(avigator.getFirstId(),true);
		avigator.switchPos_id();
		System.out.println(">-------------------------------------------------");
		System.out.println("请领航员-"+getPlayerByID(current_pid).getName()+"选择小船并将其移动");
	}

	/**
	 * 切换到海盗玩家
	 */
	public void switchToPirate(){
		gameV.updatePlayersView(current_pid,false);
		current_pid=(pirate.getFirstId());
		choosing=false;
		if(current_round==2) {
			isRobbing=true;
			System.out.println(">-------------------------------------------------");
			System.out.println("遇到海盗了！");
			System.out.println("请海盗-"+getPlayerByID(current_pid).getName()+"选择船上的位置抢劫");
			System.out.println("点击海盗区域则视为放弃");
		}
		else if(current_round==ROUND_NUMBER) {
			isReturning = true;
			System.out.println(">-------------------------------------------------");
			System.out.println("遇到海盗了！");
			System.out.println("请海盗-"+getPlayerByID(current_pid).getName()+"选择把船归还到何处");
			System.out.println("请点击船厂或者港口区域");
		}

		gameV.updatePlayersView(pirate.getFirstId(),true);
		pirate.switchPos_id();
		showBoats();

	}

	/**
	 * 切换到下一个海盗玩家
	 * @param isPass 第一个玩家是否选择了跳过不上船
	 */
	public void nextPirate(boolean isPass) {
		pirate.switchPos_id();
		gameV.updatePlayersView(current_pid,false);
		if(pirate.getCurrent_pos()==-1){
			for(Boat b:boats)
				b.setChoosen(false);
			isRobbing=false;
			if(avigator.getFirstId()!=-1){
				switchToAvigator();
			}
			else{
				current_pid=boss_pid;
				choosing=true;
			}
		}
		else{
			int pos=pirate.getCurrent_pos();
			current_pid=pirate.pos_list[pos].getSailorID();
			if(!isPass)
				pirate.updateCaptiain();
			System.out.println("请海盗-"+getPlayerByID(current_pid).getName()+"选择船上的位置抢劫");
			System.out.println("点击海盗区域则视为放弃");
		}
		gameV.updatePlayersView(current_pid, true);
		gameV.getPlayground().repaint();
	}

	/**
	 * 按顺序获得第一艘被打劫的船的引用
	 * @return 到达13格的船的引用
	 */
	public Boat getFirstRobbedBoat() {
		for (Boat b : this.boats) {
			if (b.getPos_in_the_sea() == SEA_LENGTH && b.getHarbourID() == -1 && b.getShipYardID() == -1) {
				return b;
			}
		}
		return null;
	}

	/**
	 * 结束一轮航程将属性关闭 等待点击下一个航程 若股票到达30游戏结束 输出胜利者
	 */
	public void endVoyage(){
		setVoyageIsOver(true);
		setChoosing(false);
		calculateProfits();
		System.out.println(">-------------------------------------------------");
		System.out.println("该次航程结束，请点击下一次航程进入下一航程");
		if(this.aBlackMarket.getTopPrice()==MAX_SHARES_PRICE){
			gameIsOver=true;
			showWinner();
		}
	}
}
