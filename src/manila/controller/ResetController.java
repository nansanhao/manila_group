package manila.controller;

import manila.model.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetController implements ActionListener {
    /**关联的game类*/
    private Game game;

    public ResetController(Game g){
        this.game = g;
    }
    @Override
    /**
     * 若航程结束后 点击下一次航程将所有游戏状态重置，进入下一个航程，重新竞选船长
     */
    public void actionPerformed(ActionEvent e) {
        if(this.game.isVoyageIsOver()&&this.game.isGameIsStart()&&!this.game.isGameIsOver()){
            this.game.getGameV().getChoosingBossView().getCardLayout().next(this.game.getGameV().getChoosingBossView());
            this.game.getGameV().getChoosingBossView().reset();
            this.game.switchPlayer(); //找到第一局船长左边的玩家开始竞选
            this.game.newVoyage();
            this.game.getGameV().repaint();
            System.out.println(">------------------------------------------------------");
        }

    }
}
