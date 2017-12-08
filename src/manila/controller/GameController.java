package manila.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import manila.model.*;
import manila.view.*;

/**
 * 控制鼠标交互的事件监听类
 */
public class GameController implements MouseListener {
	
	private Game game;

	private int steps;

	private int move_steps;


	public GameController(Game g){
		this.game = g;
		this.steps=0;
		this.move_steps=0;
	}

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
				System.out.println(">------------------------------------------------------");
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

	private boolean setPositionIsRight(int sea) {
		int max;
		max=(Game.SUM_MAX_STEP-steps<Game.ONCE_MAX_STEP)? Game.SUM_MAX_STEP-steps:Game.ONCE_MAX_STEP;
		return  (sea<=max)?true:false;
	}

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
					System.out.println(">------------------------------------------------------");
					System.out.println("请领航员-"+this.game.getPlayerByID(this.game.getCurrent_pid()).getName()+"选择小船并将其移动");
				}
				this.game.getGameV().updatePlayersView(this.game.getCurrent_pid(), true);
			}
		}
	}

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
