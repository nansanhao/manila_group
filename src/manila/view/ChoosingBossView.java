package manila.view;

import java.awt.*;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.*;

import manila.controller.ChoosingBossController;
import manila.model.*;

/**
 * Manila 游戏选举船老大的窗口。
 */
public class ChoosingBossView extends JPanel {
    private Game game;
    private ChoosingBossController cbc;


    public static final int CHOOSING_VIEW_W=350;
    public static final int CHOOSING_VIEW_H=200;


    private CardLayout cardLayout;


    /**日志板*/
    private JPanel consolePanel;

    /**第三个面板中的每一格*/
    private ChoiceBlock[] thirdPanel_panels;
    /**第三面板*/
    private JPanel thirdPanel;
    /**第二个面板*/
    private JPanel secondPnael;
    /**第一个面板*/
    private JPanel firstPanel;
    /** 玩家名称面板（用来装简略版PlayerView） */
    private JPanel playerView;
    /** 当前船老大姓名和对应金额面板 */
    private JPanel bossView;
    /** 选择船老大的面板 */
    private JPanel chooseView;
    /** 用于显示当前船老大姓名和对应金额 */
    private JLabel bossLabel;

    /** 竞选金额输入框 */
    private JTextField amountT;
    /** 竞选按钮 */
    private JButton bidB;
    /** 跳过本轮竞选按钮 */
    private JButton passB;
    /** 结束竞选按钮 */
    private JButton confirmB;

    /** 简略版PlayerView的数组 */
    private PlayerView[] pvList;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public JTextField getAmountT() {
        return amountT;
    }

    public void setAmountT(JTextField amountT) {
        this.amountT = amountT;
    }

    public PlayerView[] getPvList() {
        return pvList;
    }

    public void setPvList(PlayerView[] pvList) {
        this.pvList = pvList;
    }

    public JLabel getBossLabel() {
        return bossLabel;
    }

    public void setBossLabel(JLabel bossLabel) {
        this.bossLabel = bossLabel;
    }

    public JPanel getFirstPanel() {
        return firstPanel;
    }

    public void setFirstPanel(JPanel firstPanel) {
        this.firstPanel = firstPanel;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public JPanel getSecondPnael() {
        return secondPnael;
    }

    public JPanel getConsolePanel() {
        return consolePanel;
    }

    public void setConsolePanel(JPanel consolePanel) {
        this.consolePanel = consolePanel;
    }

    public ChoosingBossController getCbc() {
        return cbc;
    }

    public ChoiceBlock[] getThirdPanel_panels() {
        return thirdPanel_panels;
    }

    public ChoosingBossView(Game g){
        this.game = g;
        this.thirdPanel_panels =new ChoiceBlock[game.getBoats().length];
        this.cbc = new ChoosingBossController(this);
        this.setPreferredSize(new Dimension(CHOOSING_VIEW_W, CHOOSING_VIEW_H));
        this.cardLayout=new CardLayout();
        this.setLayout(cardLayout);


        this.firstPanel=makeFirstPanel();
        this.secondPnael=makeSecondPanel();
        this.thirdPanel=makeThirdPanel();
        this.consolePanel=makeConsolePanel();
        this.add(this.firstPanel);
        this.add(this.secondPnael);
        this.add(this.thirdPanel);
        this.add(this.consolePanel);

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setVisible(true);
    }



    public void updateBidView(int pid, boolean active){
        for(PlayerView pv : this.pvList){
            Player p = pv.getPlayer();
            if(p.getPid() == pid){
                pv.setActive(active);
            }
        }
    }


    private JPanel makeSecondPanel() {
        JPanel secondPanel=new JPanel();
        secondPanel.setLayout(new GridLayout(2,2));

        JButton jade= new JButton("玉器");
        JButton silk= new JButton("丝绸");
        JButton coco= new JButton("可可");
        JButton ginseng= new JButton("人参");

        jade.setActionCommand("jade");
        silk.setActionCommand("silk");
        coco.setActionCommand("coco");
        ginseng.setActionCommand("ginseng");

        jade.addActionListener(this.cbc);
        silk.addActionListener(this.cbc);
        coco.addActionListener(this.cbc);
        ginseng.addActionListener(this.cbc);

        secondPanel.add(jade);
        secondPanel.add(silk);
        secondPanel.add(coco);
        secondPanel.add(ginseng);


        return secondPanel;
    }

    private JPanel makeFirstPanel(){

        JPanel firstPanel=new JPanel();

        this.playerView = new JPanel();
        this.bossView = new JPanel();
        this.chooseView = new JPanel();

        firstPanel.setLayout(new BorderLayout());


        /**上方玩家*/
        this.pvList = new PlayerView[this.game.getPlayers().length];
        for(int i=0; i<this.game.getPlayers().length; i++){
            this.pvList[i] = new PlayerView(this.game.getPlayers()[i],false);
            this.playerView.add(this.pvList[i]);
            if(i == 0){
                this.pvList[i].setActive(true);
            }
        }
        firstPanel.add(this.playerView,BorderLayout.NORTH);


        /**中间*/
        JLabel label = new JLabel("船老大:                 ");
        label.setFont(new Font("SansSerif", Font.CENTER_BASELINE, 24));
        label.setHorizontalTextPosition(SwingConstants.LEFT);
        this.bossView.add(label);
        this.bossLabel = new JLabel("xxxx");
        this.bossLabel.setFont(new Font("SansSerif", Font.CENTER_BASELINE, 24));
        this.amountT = new JTextField(10);
        this.bossView.add(bossLabel);
        this.bossView.add(this.amountT);
        firstPanel.add(this.bossView, BorderLayout.CENTER);


        /**下方*/
        this.bidB = new JButton("竞选");
        this.passB = new JButton("跳过");
        this.confirmB = new JButton("确认");
        this.bidB.addActionListener(this.cbc);
        this.bidB.setActionCommand("bid");
        this.passB.addActionListener(this.cbc);
        this.passB.setActionCommand("pass");
        this.confirmB.addActionListener(this.cbc);
        this.confirmB.setActionCommand("confirm");

        this.chooseView.add(this.bidB);
        this.chooseView.add(this.passB);
        this.chooseView.add(this.confirmB);

        firstPanel.add(this.chooseView,BorderLayout.SOUTH);

        return  firstPanel;
    }

    private JPanel makeThirdPanel(){
        JPanel thirdPanel=new JPanel();
        Boat[] boats=game.getBoats();
        thirdPanel.setLayout(new GridLayout(1,boats.length));

        for(int i=0;i<boats.length;i++){
            ChoiceBlock panel=new ChoiceBlock(i,this,boats[i]);
            //加到主panel中
            thirdPanel_panels[i]=panel;
             thirdPanel.add(panel);
        }


        return  thirdPanel;

    }


    private JPanel makeConsolePanel(){
        JPanel cPanel = new JPanel();
        return  cPanel;
    }


    public void setThirdPanelActive(int i,boolean active){
        if(active){
            thirdPanel_panels[i].setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        }
        else {
            thirdPanel_panels[i].setBorder(null);
        }

    }


    public void reset() {
        this.bossLabel.setText("xxxx");
        this.cbc.setBid_amount(0);
        this.amountT.setText("");
        this.cbc.setCurrentBoatId(-1);
        this.cbc.setNumOfHasChoosenBoats(0);
        for(int i=0;i<this.thirdPanel_panels.length;i++){
            thirdPanel_panels[i].getButton().setVisible(true);
            setThirdPanelActive(i,false);
        }
    }


}

