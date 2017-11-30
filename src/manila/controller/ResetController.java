package manila.controller;

import manila.model.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetController implements ActionListener {
    private Game game;

    public ResetController(Game g){
        this.game = g;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(this.game.isVoyageIsOver()){
            this.game.getGameV().getChoosingBossView().setVisible(true);
            this.game.getGameV().getChoosingBossView().reset();
            this.game.switchPlayer(); //找到第一局船长左边的玩家开始竞选
            this.game.newVoyage();
            this.game.getGameV().getPlayground().repaint();
            this.game.getGameV().repaint();
        }

    }
}
