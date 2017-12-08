package manila.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import manila.model.BlackMarket;
import manila.model.Game;
import manila.model.Player;
import manila.model.Shares;
import manila.view.ChoosingBossView;

/**
 * Manila 游戏选举船老大的窗口控制器。
 */
public class ChoosingBossController implements ActionListener {
	/**关联到对应的view类*/
	private ChoosingBossView cbv;
	/**当前竞选的价格*/
	private int bid_amount;
	/**当前船长的id*/
	private int boss_pid;
	/**当前被选的船的ID用于放置船*/
	private int currentBoatId;
	/**已经放置好船的数量*/
	private int numOfHasChoosenBoats;

	public ChoosingBossView getCbv() {
		return cbv;
	}

	public void setBid_amount(int bid_amount) {
		this.bid_amount = bid_amount;
	}

	public void setCurrentBoatId(int currentBoatId) {
		this.currentBoatId = currentBoatId;
	}

	public void setNumOfHasChoosenBoats(int numOfHasChoosenBoats) {
		this.numOfHasChoosenBoats = numOfHasChoosenBoats;
	}

	public int getNumOfHasChoosenBoats() {
		return numOfHasChoosenBoats;
	}

	public ChoosingBossController(ChoosingBossView cbv){
		this.cbv = cbv;
		this.bid_amount = 0;
		this.numOfHasChoosenBoats=0;
		this.currentBoatId=-1;
	}
	
	/**
	 * 当前玩家参与竞价，如果输入价格小于当前标价，也认为竞标有效，自动将标价+1
	 * 如果输入为空，则不作相应（默认输入为数字，不处理其他复杂情况）。
	 * 读取完输入后将文本框内容清空，并将当前玩家设为船老大。
	 * 将下一名玩家设为当前玩家，并更新竞选面板的显示内容。
	 */
	public void bid(){
		// 读取玩家输入的金额
		String amountText = this.cbv.getAmountT().getText();
		if(isNumeric(amountText)&&!amountText.equals("")){
			int amount = Integer.parseInt(amountText);
			if(amount > this.bid_amount){
				this.bid_amount = amount;
				this.boss_pid = this.cbv.getGame().getCurrent_pid();
				this.cbv.getBossLabel().setText(this.cbv.getGame().getCurrentPlayer().getName()
						+ " " + this.bid_amount+"$");
				this.cbv.getAmountT().setText("");
				this.cbv.updateBidView(this.boss_pid, false);
				System.out.println(cbv.getGame().getPlayerByID(boss_pid).getName()+"以"+amount+"$竞标");
				this.cbv.getGame().switchPlayer();
				this.cbv.updateBidView(this.cbv.getGame().getCurrent_pid(), true);

			}
			else{
				System.out.println("价格太低，竞标无效，请重新输入");
			}
		}
		else
			System.out.println("输入有误请输入数字");
	}
	
	/**
	 * 当前玩家跳过竞价环节，不加价。
	 * 将下一名玩家设为当前玩家，并更新竞选面板的显示内容。
	 */
	public void pass(){
		System.out.println("该玩家跳过竞选");
		this.cbv.updateBidView(this.cbv.getGame().getCurrent_pid(), false);
		
		this.cbv.getGame().switchPlayer();
		this.cbv.updateBidView(this.cbv.getGame().getCurrent_pid(), true);
	}
	
	/**
	 * 竞价结束，产生船老大(扣除费用)，
	 * 将船老大设置为游戏的当前玩家，
	 * 关闭竞选窗口，更新主游戏画面（扣除费用，并为船老大对应玩家加框）。
	 */
	public void confirm(){
		Player p = this.cbv.getGame().getPlayerByID(boss_pid);
		Game g=this.cbv.getGame();
		p.payPos(this.bid_amount);
		
		// 设置当前玩家
		g.setCurrent_pid(boss_pid);
		g.setBoss_pid(boss_pid);
		System.out.println(">------------------------------------------------------");
		System.out.println(g.getPlayerByID(boss_pid).getName()+"以"+bid_amount+"$竞选成功");
		System.out.println("请"+g.getPlayerByID(boss_pid).getName()+"选择购买股票");

		// 显示边框
		g.getGameV().updatePlayersView(boss_pid, true);

		//窗口改变
		this.cbv.getCardLayout().next(this.cbv);
	}

	/**
	 * 选择需要放到海里的船
	 * @param i 选择船的id
	 */
	private void setBoatPosition(int i) {

		if(numOfHasChoosenBoats<Game.MAX_BOATS_NUM){ //判断够三艘没有
			if(this.currentBoatId!=-1&&this.cbv.getGame().getBoats()[currentBoatId].getBoatId()==-1){ //上一艘没下海 就把那一格熄了
				this.cbv.setThirdPanelActive(currentBoatId,false);
			}
			currentBoatId=i;
			this.cbv.setThirdPanelActive(currentBoatId,true);
			this.cbv.getGame().setSettingBoat(true);
			this.cbv.getGame().setChoosingBoatId(currentBoatId);
		}
	}

	/**
	 * 竞选成功后，船长选择购买股票，并且可以选择不购买
	 * @param command 点击到哪个按钮的命令数
	 */
	private void buyshares(String command) {
		int cargo_id =-1;
		Game g=this.cbv.getGame();
		BlackMarket blackMarket=g.getaBlackMarket();
		Player p =g.getPlayerByID(g.getCurrent_pid());
		cargo_id=Integer.parseInt(command)-this.getCbv().getGame().getBoats().length;
		if(cargo_id!=4){
			int sharesIndex =blackMarket.getUnOwnedSharesIndex(cargo_id);
			if(sharesIndex!=-1){
				Shares s=blackMarket.getCargo_shares()[cargo_id][sharesIndex];
				s.setOwner(p);
				p.payPos(s.getPrice());
				g.getGameV().updatePlayersView(boss_pid,true);
				System.out.println(">------------------------------------------------------");
				System.out.println(p.getName()+"以"+s.getPrice()+"$购买了"+s.getCargo_name()+"股票");
				System.out.println("请"+p.getName()+"开始放置船的位置");
				System.out.println("注意：\n1.每艘船最远能放置位置不超过5\n2.三艘船位置之和不超过9");
				this.cbv.getCardLayout().next(this.cbv);
			}
		}
		else {
			g.getGameV().updatePlayersView(boss_pid,true);
			this.cbv.getCardLayout().next(this.cbv);
			System.out.println(">------------------------------------------------------");
			System.out.println(p.getName()+"放弃了购买股票股票");
			System.out.println("请"+p.getName()+"开始放置船的位置");
			System.out.println("注意：\n1.每艘船最远能放置位置不超过5\n2.三艘船位置之和不超过9");
		}
	}

	/**
	 * 用于判断 输入的字符串是否为数字
	 * @param str 输入的字符串
	 * @return 若为数字返回true 否则为false
	 */
	public static boolean isNumeric(String str){
		Pattern pattern = Pattern.compile("[0-9]*$");
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() ){
			return false;
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String command = arg0.getActionCommand();
		if(command.equals("bid")){
			this.bid();
		}
		else if(command.equals("pass")){
			this.pass();
		}
		else if(command.equals("confirm")&&this.bid_amount!=0){
			this.confirm();
		}
		else if(command.equals("4")||command.equals("5")||command.equals("6")||command.equals("7")||command.equals("8")){
			this.buyshares(command);
		}
		else if(command.equals("0")||command.equals("1")||command.equals("2")||command.equals("3")){
			this.setBoatPosition(Integer.parseInt(command));
		}
	}


}
