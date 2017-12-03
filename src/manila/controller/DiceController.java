package manila.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import manila.model.Boat;
import manila.model.Game;

/**
 * 控制摇骰子的按钮的事件监听类
 */
public class DiceController implements ActionListener {

	private Game game;
	
	public DiceController(Game g){
		this.game = g;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		// roll the dice to move the boats
		if(!this.game.isVoyageIsOver() && !this.game.isChoosing()&&this.game.isGameIsStart()){
			for(Boat b : this.game.getBoats()){
				if(b.getBoatId()!=-1)
				b.move(this.game.rollDice());
			}
			this.game.getGameV().getPlayground().repaint();
			
			// prepare the next round
			this.game.setCurrent_round(this.game.getCurrent_round()+1);
			this.game.setChoosing(true);
			
			if(this.game.getCurrent_round() == Game.ROUND_NUMBER){
				// game is over
				this.game.setVoyageIsOver(true);
				this.game.setChoosing(false);
				this.game.boatLand();
				this.game.getGameV().getPlayground().repaint();
				this.game.calculateProfits();
				this.game.showWinner();
			}

		}

		
	}

}
