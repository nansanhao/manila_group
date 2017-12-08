package manila.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.Border;

import manila.controller.DiceController;
import manila.controller.ResetController;
import manila.model.Game;
import manila.model.Pirate;
import manila.model.Player;

/**
 * 游戏主界面，包含main函数
 */
public class GameView extends JPanel {
	/** 信息窗口的宽度 */
	private static final int INFO_W = 350;
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

	/**船老大窗口*/
	private ChoosingBossView choosingBossView;

	/**区域窗口*/
	private JPanel areaView;
	
	/** 存放玩家信息视图的数组 */
	private PlayerView[] playersV;
	/** 控制摇骰子的按钮 */
	private JButton diceButton;
	/** 重置船的按钮*/
	private JButton resetButton;

	public ChoosingBossView getChoosingBossView() {
		return choosingBossView;
	}

	public PlaygroundView getPlayground() {
		return playground;
	}

	public GameView(){
		this.game = new Game(this);

		this.choosingBossView=new ChoosingBossView(this.game);
		this.playground = new PlaygroundView(this.game);
        this.infoView = new JPanel();

        this.makePlayerView();
        this.makeDiceView();

        this.infoView.setPreferredSize(new Dimension(INFO_W, INFO_H));
        this.infoView.setBackground(Color.GREEN);
        this.infoView.setLayout(new BorderLayout());
        this.infoView.add(playersView, BorderLayout.NORTH);
        this.infoView.add(diceView, BorderLayout.CENTER);
        this.infoView.add(this.choosingBossView,BorderLayout.SOUTH);


        this.add(this.playground);
        this.add(this.infoView);


	}
	/**
	 * 对玩家信息视图进行初始化
	 */
	public void makePlayerView(){
		this.playersView = new JPanel();
		this.playersView.setLayout(null);
		this.playersView.setPreferredSize(new Dimension(INFO_W, 200));

		
		JLabel text = new JLabel("玩家信息");
		text.setFont(new Font("SansSerif", Font.CENTER_BASELINE, 18));
		text.setBounds(0,0,INFO_W,20);
		this.playersView.add(text);
		Player[] players = this.game.getPlayers();
		this.playersV = new PlayerView[players.length];
		for(int i=0; i<players.length; i++){
			PlayerView pv = new PlayerView(players[i],true);
			this.playersV[i] = pv;
			pv.setBounds(0,20+i*60,INFO_W,60);
			this.playersView.add(pv);
		}
	}
	
	/**
	 * 对摇骰子的视图进行初始化 在此Jpanel下添加黑市的子jpanel
	 */
	public void makeDiceView(){
		this.diceView = new JPanel();
		this.diceView.setPreferredSize(new Dimension(INFO_W, 600));
		this.diceView.setBackground(Color.WHITE);
		this.diceButton = new JButton("前进");
		this.diceButton.setFont(new Font("SansSerif", Font.CENTER_BASELINE, 24));
		this.diceButton.addActionListener(new DiceController(this.game));
		this.diceView.add(this.diceButton);
		this.resetButton = new JButton("下一个航程");
		this.resetButton.setFont(new Font("SansSerif", Font.CENTER_BASELINE, 24));
		this.resetButton.addActionListener(new ResetController(this.game));
		this.diceView.add(this.resetButton);
		this.diceView.add(new BlackMarketView(this.game)); //TODO 暂时添加在DICEVIEW中
		this.diceView.add(new LogView());//日志板

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
				pv.getScoreV().setText(p.getAccount_balance()+"$");
				pv.getWorker_nbV().setText(p.getWorker_nb()+"");
				int[] numOfShares=p.getNumOfShares();
				pv.getSharesV().setText("玉器："+numOfShares[0]+"  丝绸："+numOfShares[1]+"  可可："+numOfShares[2]+
						"  人参："+numOfShares[3]+"  抵押股票:"+p.getNumOfPledgeShares());
				pv.setActive(active);
			}
			
		}
	}
	


	public static void main(String[] args) {
		// TODO Auto-generated method st ub
		JFrame mw = new JFrame();
		mw.setTitle("Manila");
		GameView gv = new GameView();
		mw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mw.setContentPane(gv);
		mw.pack();
		mw.setVisible(true);
	}
	
}
