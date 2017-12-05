package manila.view;

import manila.model.LogSystem;

import javax.swing.*;
import java.awt.*;

public class LogView extends JPanel {
    /**区域高度*/
    public static final int ABSOLUTE_H=220;
    public static final int ABSOLUTE_W=350;

    /**文本区*/
    private JTextArea logText;

    public LogView() {
        this.setPreferredSize(new Dimension(ABSOLUTE_W, ABSOLUTE_H));
        this.setBackground(Color.WHITE);

        this.logText= new JTextArea("日志输出：\n",6,22);
        this.logText.setBackground(Color.LIGHT_GRAY);
        this.logText.setEditable(false);
        this.logText.setFont(new Font("SansSerif", Font.CENTER_BASELINE, 17));
        this.logText.setLineWrap(true);        //激活自动换行功能
        this.logText.setWrapStyleWord(true);            // 激活断行不断字功能

        JTextArea textArea=new JTextArea("日志板");
        textArea.setFont(new Font("SansSerif", Font.CENTER_BASELINE, 20));
        textArea.setEnabled(false);
        textArea.setDisabledTextColor(Color.black);

        this.add(textArea);
        this.add(new JScrollPane(this.logText));

        LogSystem logSystem = new LogSystem(System.out, this.logText);
        System.setOut(logSystem);
        System.setErr(logSystem);

    }
}
