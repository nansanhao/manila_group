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

	public GameController(Game g){
		this.game = g;
		this.steps=0;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public void clickedOnArea(Area a, int x, int y){
		if(isCursorInside(a,x,y)&&a.getAvailPosIndex()!=-1){
			Player p = this.game.getCurrentPlayer();
			p.payPos(a.getAvailPosPrice());
			p.setWorker_nb(p.getWorker_nb()-1);
			a.joinIn(p.getPid());

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



	public void clickedOnPos(Area a, int x, int y){
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
		return -1;
	}

	public boolean isCursorInside(Area a,int x, int y){

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

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int x=arg0.getX();
		int y=arg0.getY();
		if(!this.game.isVoyageIsOver() && this.game.isChoosing()) {

			for(Boat b:this.game.getBoats())
				if(b.getBoatId()!=-1)
				clickedOnArea(b,x, y);
			clickedOnArea(this.game.getInsurance(),x,y);
			clickedOnArea(this.game.getPirate(),x,y);
			clickedOnPos(this.game.getShipYard(),x,y);
			clickedOnPos(this.game.getHarbour(),x,y);
			clickedOnPos(this.game.getAvigator(),x,y);
		}
		else if(this.game.isSettingBoat()){
			setBoatIntoSea(x,y);
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
