package manila.model;

/**
 * 玩家类，包含玩家的姓名等基本信息。
 */
import java.awt.Color;
import java.util.ArrayList;
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
		this.haveShares= new ArrayList<Shares>();
	}

	/**
	 * 分配收益时调用的函数
	 * @param profit 为玩家分配的收益，放入余额中
	 */
	public void receiveProfit(int profit){
		this.account_balance += profit;
	}

	/**
	 * 上船为位置花钱
	 * @param amount
	 */
	public void payPos(int amount){
		//TODO：加一个判断跟处理，破产后不再花钱：范贤明 11.23完成
		if(this.account_balance>=amount) {
			this.account_balance -= amount;
		}
		else {
			int num=getNumOfUnPledgeShares();
			int i=0;
			while(i<num){
				pledgeShares();
				this.account_balance+=12;
				if(this.account_balance>=amount)
					break;
				i++;
			}
			this.account_balance-=amount;
			if(this.account_balance<0)
				this.account_balance=0;
			}
	}

	/**
	 * 抵押股票换钱的操作。改变股票的状态，
	 *  要修改的股票类型
	 *  返回卖的第几张股票，没卖就返回-1
	 */
	public int pledgeShares() {
		//TODO：将指定类型的股票的状态修改，然后获得钱：范贤明 11.23完成
		List<Shares> list = this.haveShares;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getStatus_pledge() == 1) {
				list.get(i).setStatus_pledge(2);
				return i;
			}
		}
		return -1;
	}

	/**
	 * 返回还有多少张没被抵押的股票
	 * @return
	 */
	public int getNumOfUnPledgeShares(){
		int i=0;
		for(int j=0;j<haveShares.size();j++){
			if(haveShares.get(j).getStatus_pledge()==1)
				i++;

		}
		return i;
	}

	public void addShares(Shares shares) {
		this.haveShares.add(shares);
	}

	/**
	 * 获得被抵押的股票数量
	 */
	public int getNumOfPledgeShares(){
		int i=0;
		for(Shares s:this.haveShares)
		{
			if(s.isPledged())
				i++;
		}
		return i;
	}

	/**
	 * 用量获得各股票种类的数量
	 * @return 0是玉器 1是丝绸 2可可 3人参
	 */
	public int[] getNumOfShares(){
		int num[] = {0,0,0,0};
		String name=new String();
		for(int i=0;i<haveShares.size();i++){
			name=haveShares.get(i).getCargo_name();
			if(name.equals("玉器"))
				num[0]++;
			else if(name.equals("丝绸"))
				num[1]++;
			else if(name.equals("可可"))
				num[2]++;
			else if(name.equals("人参"))
				num[3]++;
			}
		return num;
	}



}
