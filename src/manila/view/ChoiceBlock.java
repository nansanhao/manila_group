package manila.view;

import manila.model.Boat;

import javax.swing.*;
import java.awt.*;

public class ChoiceBlock extends JPanel {
    private JButton button;
    private Boat boat;
    private ChoosingBossView choosingBossView;

    public JButton getButton() {
            return button;
        }

    public void setButton(JButton button) {
            this.button = button;
        }

    public Boat getBoat() {
            return boat;
        }

    public void setBoat(Boat boat) {
            this.boat = boat;
        }

    public ChoiceBlock(int command,ChoosingBossView cbc,Boat boat) {
        this.boat = boat;
        this.choosingBossView=cbc;
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

