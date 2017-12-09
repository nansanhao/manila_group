package manila.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import manila.model.Boat;
import manila.model.Game;

/**
 * 控制摇骰子的按钮的事件监听类
 */
public class DiceController implements ActionListener {
	/**关联到游戏类*/
	private Game game;
	
	public DiceController(Game g){
		this.game = g;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		// roll the dice to move the boats
		if(!this.game.isVoyageIsOver() && !this.game.isChoosing()&&this.game.isGameIsStart()
				&&!this.game.isChoosingBoat()&&!this.game.isMovingBoat()&&!this.game.isRobbing()&&!this.game.isReturning()){
			for(Boat b : this.game.getBoats()){
				if(b.getBoatId()!=-1) {
					int step=this.game.rollDice();
					b.move(step);
				}
			}
			this.game.getGameV().getPlayground().repaint();
			
			// prepare the next round
			this.game.setCurrent_round(this.game.getCurrent_round()+1);
			this.game.setChoosing(true);

			if(this.game.getCurrent_round()==Game.ROUND_NUMBER){  //到达第三回合时候直接判断船的位置将其驶入港口、船厂
				this.game.boatLand();
				this.game.getGameV().getPlayground().repaint();
			}


			if(this.game.getCurrent_round()!=1&&this.game.isRobbed()&&this.game.getPirate().getFirstId()!=-1){ //第二、三回合时判断有没有到达13格
				this.game.switchToPirate();
			}
			else if(this.game.getCurrent_round()==Game.ROUND_NUMBER-1&&this.game.getAvigator().getFirstId()!=-1){ //第二回合领航员作用
				this.game.switchToAvigator();
			}
			else if(this.game.getCurrent_round() == Game.ROUND_NUMBER&&!(this.game.isRobbed()&&this.game.getPirate().getFirstId()!=-1)){
				// 没有到达13或者没有海盗则完成航程
				this.game.endVoyage();
			}
		}
	}
}
