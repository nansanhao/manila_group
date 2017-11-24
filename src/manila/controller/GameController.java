package manila.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import manila.model.Area;
import manila.model.Boat;
import manila.model.Game;
import manila.model.Player;

/**
 * 控制鼠标交互的事件监听类
 */
public class GameController implements MouseListener {
	
	private Game game;

	public GameController(Game g){
		this.game = g;
	}
	


	public void clickedOnArea(Area a, int x, int y){
		if(a.isCursorInside(x,y)&&a.getAvailPosIndex()!=-1){
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
		int numOfPos=a.clickOnWhichPos(x,y);
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
