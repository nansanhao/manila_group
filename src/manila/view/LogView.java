package manila.view;

import manila.model.LogSystem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LogView extends JPanel {
    /**区域高度*/
    public static final int ABSOLUTE_H=900;
    public static final int ABSOLUTE_W=200;

    /**文本区*/
    private JTextArea logText;
    private JScrollPane jScrollPane;

    public JTextArea getLogText() {
        return logText;
    }

    public void setLogText(JTextArea logText) {
        this.logText = logText;
    }

    public LogView() {
        this.setPreferredSize(new Dimension(ABSOLUTE_W, ABSOLUTE_H));
        //this.setBorder(new EmptyBorder(5,5,5,5));
        this.setBackground(Color.GREEN);
        this.logText= new JTextArea("日志输出：\n",10,13);
        this.logText.setEditable(true);
        this.logText.setFont(new Font("标楷体", Font.BOLD, 17));
        this.logText.setLineWrap(true);        //激活自动换行功能
        this.logText.setWrapStyleWord(true);            // 激活断行不断字功能


        this.jScrollPane=new JScrollPane(this.logText);
        this.add(jScrollPane);
        JScrollBar vertical = this.jScrollPane.getVerticalScrollBar();
        vertical.setValue( vertical.getMaximum());
        LogSystem mps = new LogSystem(System.out, this.logText);
        System.setOut(mps);
        System.setErr(mps);

    }
}
