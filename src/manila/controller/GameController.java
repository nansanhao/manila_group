package manila.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import manila.model.*;
import manila.view.*;

/**
 * 控制鼠标交互的事件监听类
 */
public class GameController implements MouseListener {
	/**关联到游戏类*/
	private Game game;
	/**放置船时已放置船的位置的和*/
	private int steps;
	/**大领航员工作时，已移动的格数*/
	private int move_steps;


	public GameController(Game g){
		this.game = g;
		this.steps=0;
		this.move_steps=0;
	}

	/**
	 * 点到对应的区域应该找到第一个默认位置让玩家放置同伙
	 * 其后玩家的同伙数减少
	 * @param a	 点到哪个区域
	 * @param x 鼠标x坐标
	 * @param y 鼠标y坐标
	 */
	private void clickedOnArea(Area a, int x, int y){
		if(isCursorInside(a,x,y)&&a.getAvailPosIndex()!=-1){
			Player p = this.game.getCurrentPlayer();
			p.payPos(a.getAvailPosPrice());
			p.setWorker_nb(p.getWorker_nb()-1);
			a.joinIn(p.getPid());
			if(a instanceof Insurance)
				System.out.println(p.getName()+"在保险公司获得10$");

			// modify the view
			this.game.getGameV().getPlayground().repaint();
			this.game.getGameV().updatePlayersView(p.getPid(), false);


			if(this.game.getCurrent_pid() == this.game.getBoss_pid()-1
					|| this.game.getCurrent_pid() == this.game.getPlayers().length+this.game.getBoss_pid()-1){
				this.game.setChoosing(false);
			}

			this.game.switchPlayer();
			this.game.getGameV().updatePlayersView(this.game.getCurrent_pid(), true);
		}
	}

	/**
	 * 对到对应区域内的对应位置放置玩家同伙
	 * @param a 点到的区域
	 * @param x	 鼠标x坐标
	 * @param y 鼠标y坐标
	 */
	private void clickedOnPos(Area a, int x, int y){
		int numOfPos=clickOnWhichPos(a,x,y);
			if(numOfPos!=-1&&a.pos_list[numOfPos].getSailorID()==-1){
			Player p = this.game.getCurrentPlayer();
			p.payPos(a.getAvailPosPrice());
			p.setWorker_nb(p.getWorker_nb()-1);
			a.joinInPos(numOfPos,p.getPid());

			// modify the view
			this.game.getGameV().getPlayground().repaint();
			this.game.getGameV().updatePlayersView(p.getPid(), false);

			if(this.game.getCurrent_pid() == this.game.getBoss_pid()-1
					|| this.game.getCurrent_pid() == this.game.getPlayers().length+this.game.getBoss_pid()-1){
				this.game.setChoosing(false);
			}

			this.game.switchPlayer();
			this.game.getGameV().updatePlayersView(this.game.getCurrent_pid(), true);
		}

	}

	/**
	 * 判断玩家点到对应区域的第几个位置
	 * @param a 判断的区域
	 * @param x 鼠标x坐标
	 * @param y 鼠标y坐标
	 * @return 位置ID
	 */
	private int clickOnWhichPos(Area a, int x, int y) {
		if(a instanceof ShipYard) {
			for (int i = 0; i < a.pos_list.length; i++) {
				if (x > ShipYardView.ABSOLUTE_X + ShipYardView.POS_START_X
						&& x < ShipYardView.ABSOLUTE_X + ShipYardView.POS_START_X + ShipYardView.POS_W
						&& y > ShipYardView.ABSOLUTE_Y + ShipYardView.POS_START_Y + i * (ShipYardView.POS_H + ShipYardView.INTERVAL)
						&& y < ShipYardView.ABSOLUTE_Y + ShipYardView.POS_START_Y + i * (ShipYardView.POS_H + ShipYardView.INTERVAL) + ShipYardView.POS_H
						)
					return i;
			}
		}
		else if(a instanceof Harbour){
			for (int i=0;i<a.pos_list.length;i++){
				if(x > HarbourView.ABSOLUTE_X+HarbourView.POS_START_X
						&& x < HarbourView.ABSOLUTE_X+HarbourView.POS_START_X+HarbourView.POS_W
						&& y > HarbourView.ABSOLUTE_Y+HarbourView.POS_START_Y+i*(HarbourView.POS_H+HarbourView.INTERVAL)
						&& y < HarbourView.ABSOLUTE_Y+HarbourView.POS_START_Y+i*(HarbourView.POS_H+HarbourView.INTERVAL)+HarbourView.POS_H
						)
					return  i;
			}
		}
		else if(a instanceof Avigator){
			for (int i=0;i<a.pos_list.length;i++){
				if(x > AvigatorView.ABSOLUTE_X+AvigatorView.POS_START_X+i*(AvigatorView.POS_W+AvigatorView.POS_INTERVAL)
						&& x < AvigatorView.ABSOLUTE_X+AvigatorView.POS_START_X+AvigatorView.POS_W+i*(AvigatorView.POS_W+AvigatorView.POS_INTERVAL)
						&& y > AvigatorView.ABSOLUTE_Y+AvigatorView.POS_START_Y
						&& y < AvigatorView.ABSOLUTE_Y+AvigatorView.POS_START_Y+AvigatorView.POS_H)
					return  i;
			}
		}

		else if(a instanceof Pirate){
			for (int i=0;i<a.pos_list.length;i++){
				if(x > PirateAreaView.ABSOLUTE_X+PirateAreaView.POS_START_X+i*(PirateAreaView.POS_W+PirateAreaView.POS_INTERVAL)
						&& x < PirateAreaView.ABSOLUTE_X+PirateAreaView.POS_START_X+PirateAreaView.POS_W+i*(PirateAreaView.POS_W+PirateAreaView.POS_INTERVAL)
						&& y > PirateAreaView.ABSOLUTE_Y+PirateAreaView.POS_START_Y
						&& y < PirateAreaView.ABSOLUTE_Y+PirateAreaView.POS_START_Y+PirateAreaView.POS_H)
					return  i;
			}
		}
		return -1;
	}

	/**
	 * 判断是否点到对应区域
	 * @param a 判断的区域
	 * @param x	 鼠标x坐标
	 * @param y 鼠标y坐标
	 * @return 点到则为true 否则为false
	 */
	private boolean isCursorInside(Area a,int x, int y){

		if(a instanceof Boat){
			Boat aBoat=(Boat) a;
			int aBoat_X=PlaygroundView.BOAT_START_X+aBoat.getPos_in_the_sea()*(PlaygroundView.SEA_INTERVAL+PlaygroundView.SEA_W);
			int aBoat_Y=PlaygroundView.BOAT_START_Y+aBoat.getBoatId()*(PlaygroundView.BOAT_H+PlaygroundView.BOAT_DISTANCE);
			if(x > aBoat_X && x < aBoat_X+ PlaygroundView.BOAT_W
					&& y > aBoat_Y && y< aBoat_Y+PlaygroundView.BOAT_H)
				return true;
			return false;
		}
		else if(a instanceof Pirate)
		{
			if(x > PirateAreaView.ABSOLUTE_X && x < PirateAreaView.ABSOLUTE_X+ PirateAreaView.ABSOLUTE_W
					&& y > PirateAreaView.ABSOLUTE_Y && y< PirateAreaView.ABSOLUTE_Y+PirateAreaView.ABSOLUTE_H)
				return true;
			return false;
		}
		else if(a instanceof Insurance)
		{
			if(x >InsuranceAreaView.ABSOLUTE_X && x < InsuranceAreaView.ABSOLUTE_X+ InsuranceAreaView.ABSOLUTE_W
					&& y > InsuranceAreaView.ABSOLUTE_Y && y< InsuranceAreaView.ABSOLUTE_Y+InsuranceAreaView.ABSOLUTE_H)
				return true;
			return false;
		}
		else
		{
			return false;
		}

	}

	/**
	 * 将当前在CBC选好对应的船放置在鼠标所点那一格海里
	 * @param x 鼠标的x坐标
	 * @param y	 鼠标的y坐标
	 */
	private void setBoatIntoSea(int x, int y) {
		ChoosingBossView cbv=this.game.getGameV().getChoosingBossView();
		int sea = cilckOnWhichSea(x,y);
		Boat b=this.game.getBoats()[this.game.getChoosingBoatId()];
		if (sea != -1&&setPositionIsRight(sea)&&b.getBoatId()==-1) {
			steps+=sea;
			int num=cbv.getCbc().getNumOfHasChoosenBoats();

			b.setPos_in_the_sea(sea);
			b.setBoatId(num);
			cbv.getCbc().setNumOfHasChoosenBoats(num+1);
			cbv.getThirdPanel_panels()[this.game.getChoosingBoatId()].getButton().setVisible(false);

			//界面更新
			this.game.getGameV().getPlayground().repaint();
			if(cbv.getCbc().getNumOfHasChoosenBoats()==this.game.MAX_BOATS_NUM){
				cbv.getCardLayout().next(cbv);

				//设置游戏开关
				System.out.println(">-------------------------------------------------");
				System.out.println("放置完成，开始游戏！！");
				this.game.setChoosing(true);
				this.game.setGameIsStart(true);
				this.game.setVoyageIsOver(false);
				this.game.setSettingBoat(false);
				this.game.setChoosingBoatId(-1);
				this.steps=0;
			}
		}

	}

	/**
	 * 判断点到那一格海是否符合游戏的规则
	 * @param sea 点到的海的格数
	 * @return  符合返回true 否则返回false
	 */
	private boolean setPositionIsRight(int sea) {
		int max;
		max=(Game.SUM_MAX_STEP-steps<Game.ONCE_MAX_STEP)? Game.SUM_MAX_STEP-steps:Game.ONCE_MAX_STEP;
		return  (sea<=max)?true:false;
	}

	/**
	 * 得到所点地方在哪一格海
	 * @param x 鼠标x坐标
	 * @param y 鼠标y坐标
	 * @return 点的海的格数 没有点到返回-1
	 */
	private int cilckOnWhichSea(int x, int y) {

		for(int i=0;i<=this.game.SEA_LENGTH;i++){
			if(       x>PlaygroundView.SEA_START_X+i*(PlaygroundView.SEA_W+PlaygroundView.SEA_INTERVAL)
					&& x<PlaygroundView.SEA_START_X+i*(PlaygroundView.SEA_W+PlaygroundView.SEA_INTERVAL)+PlaygroundView.SEA_W
					&& y>PlaygroundView.SEA_START_Y && y<PlaygroundView.SEA_START_Y+PlaygroundView.SEA_L
					)
				return i;
		}
		return -1;
	}

	/**
	 * 到领航员作用的环节，领航员选择好船后，点击Playground，
	 * 将之坐标传入调用clickOnWhichSea()，返回点的格数判断是否符合规则，若负责则移动船
	 * @param x 鼠标x坐标
	 * @param y	 鼠标y坐标
	 */
	private void avigatorMoveBoat(int x, int y) {
		int num=cilckOnWhichSea(x,y);
		if(num!=-1&&moveIsRight(num)){
			this.game.getBoatByID(this.game.getChoosingBoatId()).setPos_in_the_sea(num);
			this.game.getBoatByID(this.game.getChoosingBoatId()).setChoosen(false);
			this.game.getGameV().getPlayground().repaint();
			this.game.setMovingBoat(false);
			this.game.setChoosingBoatId(-1);
			if(this.game.getAvigator().getCurrent_pos()==1&&move_steps<2)
				this.game.setChoosingBoat(true);
			else{
				this.game.getAvigator().switchPos_id();
				this.game.getGameV().updatePlayersView(this.game.getCurrent_pid(),false);
				if(this.game.getAvigator().getCurrent_pos()==-1){
					this.game.setCurrent_pid(this.game.getBoss_pid());
					this.game.setChoosing(true);
					move_steps = 0;
				}
				else{
					int pos=this.game.getAvigator().getCurrent_pos();
					this.game.setCurrent_pid(this.game.getAvigator().pos_list[pos].getSailorID());
					this.game.setChoosingBoat(true);
					System.out.println(">-------------------------------------------------");
					System.out.println("请领航员-"+this.game.getPlayerByID(this.game.getCurrent_pid()).getName()+"选择小船并将其移动");
				}
				this.game.getGameV().updatePlayersView(this.game.getCurrent_pid(), true);
			}
		}
	}

	/**
	 * 用于判断点的格数是否符合领航员移动的规则
	 * @param num 点的海的格数
	 * @return 符合返回true 否则返回false
	 */
	private boolean moveIsRight(int num) {
		Avigator avigator=this.game.getAvigator();
		Boat boat=this.game.getBoatByID(this.game.getChoosingBoatId());
		int distance = Math.abs(num-boat.getPos_in_the_sea());
		if(avigator.getCurrent_pos()==0){
			return (distance==1)?true:false;
		}
		else if(avigator.getCurrent_pos()==1){
			if(move_steps==0&&distance>0&&distance<=2) {
				move_steps+=distance;
				return true;
			}
			else if(move_steps==1&&distance==1){
				move_steps+=distance;
				return true;
			}
		}
		return false;
	}

	/**
	 * 领航员通过点击选择要移动的船
	 * @param x 鼠标x坐标
	 * @param y	 鼠标y坐标
	 */
	private void chooseBoat(int x, int y) {
		for(Boat b:this.game.getBoats()){
			if(isCursorInside(b,x,y)){
				b.setChoosen(true);
				this.game.setChoosingBoatId(b.getBoatId());
				this.game.getGameV().getPlayground().repaint();
				this.game.setMovingBoat(true);
				this.game.setChoosingBoat(false);
			}
		}
	}

	/**
	 * 海盗通过点击，劫掠小船上对应的位置，并将原来的玩家赶下船，若点到海盗区域可以自动跳过不上船
	 * @param x 鼠标x坐标
	 * @param y 鼠标y坐标
	 */
	private void pirateRobBoat(int x, int y) {
		int num=-1,boat_id=-1;
		for(Boat b:this.game.getBoats()){
			if(b.getPos_in_the_sea()==13) {
				num = clickOnWhichBoatPos(b.getBoatId(), x, y);
				boat_id=b.getBoatId();
			}
		}
		if(isCursorInside(this.game.getPirate(),x,y)){
			this.game.nextPirate(true);
		}
		else if(num!=-1&&this.game.getBoatByID(boat_id).pos_list[num].getSailorID()!=this.game.getCurrent_pid()){
			this.game.getBoatByID(boat_id).pos_list[num].setSailorID(this.game.getCurrent_pid());
			this.game.getPirate().pos_list[this.game.getPirate().getCurrent_pos()].setSailorID(-1);
			this.game.nextPirate(false);
		}
	}

	/**
	 * 通过点击获得所点坐标点的船的对应的位置ID，若位置上跟海盗同一个人则返回-1
 	 * @param boat_id 判断的船的ID
	 * @param x 鼠标x坐标
	 * @param y	 鼠标y坐标
	 * @return 船上被点位置ID
	 */
	private int clickOnWhichBoatPos(int boat_id ,int x, int y) {
		Boat boat=this.game.getBoatByID(boat_id);
		int boat_x=PlaygroundView.BOAT_START_X+boat.getPos_in_the_sea()*(PlaygroundView.SEA_W+PlaygroundView.SEA_INTERVAL);
		int boat_y=PlaygroundView.BOAT_START_Y+boat_id*(PlaygroundView.BOAT_DISTANCE+PlaygroundView.BOAT_H);
		for (int i=0;i<boat.pos_list.length;i++){
			if(x > boat_x+PlaygroundView.POS_START_X-i*(PlaygroundView.POS_W+PlaygroundView.POS_INTERVAL)
					&& x < boat_x+PlaygroundView.POS_START_X-i*(PlaygroundView.POS_W+PlaygroundView.POS_INTERVAL)+PlaygroundView.POS_W
					&& y > boat_y+PlaygroundView.POS_START_Y
					&& y < boat_y+PlaygroundView.POS_START_Y+PlaygroundView.POS_H
					)
				return  i;
		}

		return -1;
	}

	/**
	 * 海盗通过点击把船放回到所点的区域
	 * @param x	 鼠标x坐标
	 * @param y 鼠标y坐标
	 */
	private void pirateReturnBoat(int x, int y) {
		int toWhere = ReturnToWhere(x, y); //1为港口 2为修船厂
		Boat b = this.game.getFirstRobbedBoat();
		if (toWhere == 1 && this.game.getHarbour().getAvailBoatPosIndex() != -1) {
			int i = this.game.getHarbour().getAvailBoatPosIndex();
			b.setHarbourID(i);
			this.game.getHarbour().boatPositions[i].setHaveBoat(true);
			b.setChoosen(false);
			if(this.game.getFirstRobbedBoat()!=null){
				this.game.getFirstRobbedBoat().setChoosen(true);
			}
			else{
				this.game.setReturning(false);
				this.game.endVoyage();
			}
			this.game.getGameV().repaint();

		} else if (toWhere == 2 && this.game.getShipYard().getAvailBoatPosIndex() != -1) {
			int i = this.game.getShipYard().getAvailBoatPosIndex();
			b.setShipYardID(i);
			this.game.getShipYard().boatPositions[i].setHaveBoat(true);
			b.setChoosen(false);
			if(this.game.getFirstRobbedBoat()!=null){
				this.game.getFirstRobbedBoat().setChoosen(true);
			}
			else{
				this.game.setReturning(false);
				this.game.endVoyage();
			}
			this.game.getGameV().repaint();
		}
	}

	/**
	 * 返回点到的区域
	 * @param x 鼠标x坐标
	 * @param y 鼠标y坐标
	 * @return 1为港口 2为船厂 其他为-1
	 */
	private int ReturnToWhere(int x,int y){ // 把船归还到那
		if(x > HarbourView.ABSOLUTE_X && x < HarbourView.ABSOLUTE_X+ HarbourView.ABSOLUTE_W
				&& y > HarbourView.ABSOLUTE_Y && y< HarbourView.ABSOLUTE_Y+HarbourView.ABSOLUTE_H)
			return 1;
		else if(x > ShipYardView.ABSOLUTE_X && x < ShipYardView.ABSOLUTE_X+ ShipYardView.ABSOLUTE_W
				&& y > ShipYardView.ABSOLUTE_Y && y< ShipYardView.ABSOLUTE_Y+ShipYardView.ABSOLUTE_H)
			return 2;
		return -1;
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int x=arg0.getX();
		int y=arg0.getY();
		if(!this.game.isVoyageIsOver() && this.game.isChoosing()) {//选位置

			for(Boat b:this.game.getBoats())
				if(b.getBoatId()!=-1)
				clickedOnArea(b,x, y);
			clickedOnArea(this.game.getInsurance(),x,y);
			clickedOnArea(this.game.getPirate(),x,y);
			clickedOnPos(this.game.getShipYard(),x,y);
			clickedOnPos(this.game.getHarbour(),x,y);
			clickedOnPos(this.game.getAvigator(),x,y);
		}
		else if(this.game.isSettingBoat()){ //放船
			setBoatIntoSea(x,y);
		}
		else if(this.game.isChoosingBoat()){ //选船
			chooseBoat(x,y);
		}
		else if(this.game.isMovingBoat()){ //移船
			avigatorMoveBoat(x,y);
		}
		else if(this.game.isRobbing()) { //上船
			pirateRobBoat(x,y);
		}
		else if(this.game.isReturning()){
			pirateReturnBoat(x,y);
		}

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
