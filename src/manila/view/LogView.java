package manila.view;

import manila.model.LogSystem;

import javax.swing.*;
import java.awt.*;

public class LogView extends JPanel {

    /**区域高度*/
    public static final int ABSOLUTE_H=220;
    /**区域宽度*/
    public static final int ABSOLUTE_W=350;

    /**日志文本区*/
    private JTextArea logText;

    public LogView() {
        this.setPreferredSize(new Dimension(ABSOLUTE_W, ABSOLUTE_H));
        this.setBackground(Color.WHITE);

        this.logText= new JTextArea("",8,25);
        this.logText.setBackground(Color.LIGHT_GRAY);
        this.logText.setEditable(false);

        this.logText.setFont(new Font("SansSerif", Font.CENTER_BASELINE, 17));
        //激活自动换行功能
        this.logText.setLineWrap(true);
        // 激活断行不断字功能
        this.logText.setWrapStyleWord(true);


        JTextArea textArea=new JTextArea("日志板");
        textArea.setFont(new Font("SansSerif", Font.CENTER_BASELINE, 18));
        textArea.setEnabled(false);
        textArea.setDisabledTextColor(Color.black);

        this.add(textArea);
        this.add(new JScrollPane(this.logText));

        //设置System的输出方式
        LogSystem logSystem = new LogSystem(System.out, this.logText);
        System.setOut(logSystem);
        System.setErr(logSystem);
    }
}
