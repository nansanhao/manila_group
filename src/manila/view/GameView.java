package manila.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import manila.controller.DiceController;
import manila.controller.ResetController;
import manila.model.Game;
import manila.model.Player;

/**
 * 游戏主界面，包含main函数
 */
public class GameView extends JPanel {
	/** 信息窗口的宽度 */
	private static final int INFO_W = 300;
	/** 信息窗口的高度 */
	private static final int INFO_H = 900;
    
	private Game game;
	
	/** 游戏场景窗口 */
	private PlaygroundView playground;
	/** 信息窗口 */
	private JPanel infoView;
	/** 玩家信息窗口 */
	private JPanel playersView;
	/** 摇骰子的窗口 */
	private JPanel diceView;
	
	/** 存放玩家信息视图的数组 */
	private PlayerView[] playersV;
	/** 控制摇骰子的按钮 */
	private JButton diceButton;
	/** 重置船的按钮*/
	private JButton resetButton;
	
	public GameView(){
		this.game = new Game(this);
		
		this.playground = new PlaygroundView(this.game);
        this.infoView = new JPanel();
        
        this.makePlayerView();
        this.makeDiceView();
        
        this.infoView.setPreferredSize(new Dimension(INFO_W, INFO_H));
        this.infoView.setBackground(Color.GREEN);
        this.infoView.setLayout(new BorderLayout());
        this.infoView.add(playersView, BorderLayout.NORTH);
        this.infoView.add(diceView, BorderLayout.CENTER);
        
        this.add(this.playground);
        this.add(this.infoView);
        
        //this.setBackground(Color.RED);
        
	}
	
	/**
	 * 对玩家信息视图进行初始化
	 */
	public void makePlayerView(){
		this.playersView = new JPanel();
		this.playersView.setLayout(new GridLayout(4,1));
		this.playersView.setPreferredSize(new Dimension(INFO_W, 240));
		
		JLabel text = new JLabel("玩家信息");
		text.setHorizontalTextPosition(SwingConstants.LEFT);
		text.setFont(new Font("SansSerif", Font.CENTER_BASELINE, 25));
		this.playersView.add(text,BorderLayout.CENTER);
		Player[] players = this.game.getPlayers();
		this.playersV = new PlayerView[players.length];
		for(int i=0; i<players.length; i++){
			PlayerView pv = new PlayerView(players[i],true);
			this.playersV[i] = pv;
			this.playersView.add(pv);
		}
	}
	
	/**
	 * 对摇骰子的视图进行初始化
	 */
	public void makeDiceView(){
		this.diceView = new JPanel();
		this.diceView.setPreferredSize(new Dimension(INFO_W, 500));
		this.diceView.setBackground(Color.GRAY);
		this.diceButton = new JButton("前进");
		this.diceButton.setFont(new Font("SansSerif", Font.CENTER_BASELINE, 24));
		this.diceButton.addActionListener(new DiceController(this.game));
		this.diceView.add(this.diceButton);
		this.resetButton = new JButton("重置");
		this.resetButton.setFont(new Font("SansSerif", Font.CENTER_BASELINE, 24));
		this.resetButton.addActionListener(new ResetController(this.game));
		this.diceView.add(this.resetButton);
	}
	
	/**
	 * 对玩家的显示在界面上的相关信息进行更新
	 * @param pid 对应玩家的ID
	 * @param active 是否标记此玩家为当前玩家
	 */
	public void updatePlayersView(int pid, boolean active){
		for(PlayerView pv : this.playersV){
			Player p = pv.getPlayer();
			if(p.getPid() == pid){
				if(!active){
					pv.getScoreV().setText(p.getAccount_balance()+"$");
					pv.getWorker_nbV().setText(p.getWorker_nb()+"");
				}
				pv.setActive(active);
			}
			
		}
	}
	

	public PlaygroundView getPlayground() {
		return playground;
	}

	public void setPlayground(PlaygroundView playground) {
		this.playground = playground;
	}

	public JPanel getPlayersView() {
		return playersView;
	}

	public void setPlayersView(JPanel playersView) {
		this.playersView = playersView;
	}

	public PlayerView[] getPlayersV() {
		return playersV;
	}

	public void setPlayersV(PlayerView[] playersV) {
		this.playersV = playersV;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame mw = new JFrame();
		mw.setTitle("Manila");
		GameView gv = new GameView();
		mw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mw.setContentPane(gv);
		mw.pack();
		mw.setVisible(true);
		
		ChoosingBossView cbv = new ChoosingBossView(gv.game);
	}
	
}
