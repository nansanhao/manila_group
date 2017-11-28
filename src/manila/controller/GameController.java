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

	public GameController(Game g){
		this.game = g;
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
				if (x > ShipYardView.ABSOLUTE_X + ShipYardView.SHIP_POS_START_X
						&& x < ShipYardView.ABSOLUTE_X + ShipYardView.SHIP_POS_START_X + AreaView.POS_W
						&& y > ShipYardView.ABSOLUTE_Y + ShipYardView.SHIP_POS_START_Y + i * (AreaView.POS_H + ShipYardView.SHIP_POS_INTERVAL_Y)
						&& y < ShipYardView.ABSOLUTE_Y + ShipYardView.SHIP_POS_START_Y + i * (AreaView.POS_H + ShipYardView.SHIP_POS_INTERVAL_Y) + AreaView.POS_H)
					return i;
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

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(!this.game.isGameIsOver() && this.game.isChoosing()) {

			for(Boat b:this.game.getBoats())
				this.clickedOnArea(b,arg0.getX(), arg0.getY());
			this.clickedOnArea(this.game.getInsurance(),arg0.getX(),arg0.getY());
			this.clickedOnArea(this.game.getPirate(),arg0.getX(),arg0.getY());
			this.clickedOnPos(this.game.getShipYard(),arg0.getX(),arg0.getY());
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
