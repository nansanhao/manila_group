package manila.view;

import manila.model.Boat;

import javax.swing.*;
import java.awt.*;



public class ChoiceBlockView extends JPanel {
    /**
     * 该PENAL用于放置船只
     */

    /** 选择按钮*/
    private JButton button;
    /**对应被选中的船引用*/
    private Boat boat;
    /**关联的CBV类*/
    private ChoosingBossView choosingBossView;

    public JButton getButton() {
            return button;
        }

    /**
     * 根据传入的船引用构造一个小格子 用船中的信息生成格子内的信息
     * @param command 按钮对应的命令字符
     * @param cbv   关联的cbv类
     * @param boat 船的引用
     */

    public ChoiceBlockView(int command, ChoosingBossView cbv, Boat boat) {
        this.boat = boat;
        this.choosingBossView=cbv;
        this.setLayout(null);

        //设置按钮
        this.button=new JButton("选择");
        button.setBounds(4,ChoosingBossView.CHOOSING_VIEW_H/2,
                ChoosingBossView.CHOOSING_VIEW_W/choosingBossView.getGame().getBoats().length-2*4,50);
        this.add(button);
        button.setActionCommand(command+"");
        button.addActionListener(this.choosingBossView.getCbc());

        //设置中间文字
        JLabel cargoNameLabel =new JLabel(this.boat.getCargo_name());
        cargoNameLabel.setFont(new Font("SansSerif", Font.CENTER_BASELINE, 24));
        JLabel cargoPriceLabel=new JLabel(this.boat.getCargo_value()+"$");
        cargoPriceLabel.setFont(new Font("SansSerif", Font.CENTER_BASELINE, 20));

        this.add(cargoNameLabel);
        this.add(cargoPriceLabel);
        cargoNameLabel.setBounds(button.getBounds().width/2-24,button.getBounds().y-50,button.getBounds().width,25);
        cargoPriceLabel.setBounds(button.getBounds().width/2-15,button.getBounds().y-20,button.getBounds().width,20);
        this.add(cargoNameLabel);
        this.add(cargoPriceLabel);
        }
}

