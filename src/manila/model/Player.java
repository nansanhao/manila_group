package manila.model;

/**
 * 玩家类，包含玩家的姓名等基本信息。
 */
import java.awt.Color;
import java.util.List;

public class Player {
	/** 玩家名 */
	private String name;
	/** 玩家的ID */
	private int pid;
	/** 账户余额 */
	private int account_balance;
	/** 玩家拥有的工人（海员）数 */
	private int worker_nb;
	/** 玩家所对应的颜色 */
	private Color c;
	/** 持有的股票 */
	private List<Shares> haveShares;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getAccount_balance() {
		return account_balance;
	}

	public void setAccount_balance(int account_balance) {
		this.account_balance = account_balance;
	}

	public int getWorker_nb() {
		return worker_nb;
	}

	public void setWorker_nb(int worker_nb) {
		this.worker_nb = worker_nb;
	}

	public Color getC() {
		return c;
	}

	public void setC(Color c) {
		this.c = c;
	}

	public List<Shares> getHaveShares() {
		return haveShares;
	}

	public void setHaveShares(List<Shares> haveShares) {
		this.haveShares = haveShares;
	}
	/**
	 * 玩家构造函数
	 * @param name 玩家姓名
	 * @param pid 玩家ID
	 * @param c 玩家分配的颜色
	 */
	public Player(String name, int pid, Color c){
		this.name = name;
		this.pid = pid;
		this.account_balance = 30;
		this.c = c;
		this.worker_nb = Game.ROUND_NUMBER;
	}

	/**
	 * 分配收益时调用的函数
	 * @param profit 为玩家分配的收益，放入余额中
	 */
	public void receiveProfit(int profit){
		this.account_balance += profit;
	}

	/**
	 * 加一个判断跟处理，破产后不再花钱
	 * @param amount
	 */
	public void payPos(int amount){
		//TODO
		this.account_balance -= amount;
		this.worker_nb--;
	}

	/**
	 * 判断财产是否只剩下0
	 * @return
	 */
	public boolean isBankrupt(){
		//TODO
		return true;
	}

	/**
	 * 抵押股票还钱的操作
	 */
	public void pledgeShares(){
		//TODO

	}

}
