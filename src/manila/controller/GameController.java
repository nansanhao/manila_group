package manila.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
	
	public void clickedOnBoat(int x, int y){
		Boat[] boats = game.getBoats();
		for(int i=0; i<boats.length; i++){
			Boat b = boats[i];
			if(b.isCursorInside(x, y)){
				if(b.getAvailPosIndex() != -1){
					Player p = this.game.getCurrentPlayer();
					p.payPos(b.getAvailPosPrice());
					b.getOnboard(p.getPid());
					
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
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(!this.game.isGameIsOver() && this.game.isChoosing()){
			this.clickedOnBoat(arg0.getX(), arg0.getY());
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
