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
        if(this.game.isGameIsOver()){
            this.game.newVoyage();
            this.game.getGameV().getPlayground().repaint();
        }
        for(int i=0;i<this.game.getPlayers().length;i++){
            this.game.getGameV().updatePlayersView(i,false);
        }
        this.game.getGameV().updatePlayersView(this.game.getBoss_pid(),true);
    }
}
