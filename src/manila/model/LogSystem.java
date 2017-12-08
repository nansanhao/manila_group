package manila.model;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;


/**
 * 用于修改日志输出位置的module
 */
public class LogSystem extends PrintStream{
    private JTextArea text;

    public LogSystem(OutputStream out, JTextArea text) {
        super(out);
        this.text = text;
    }
    /**
     * 在这里重载,所有的打印方法都要调用的方法
     */
    public void write(byte buf[], int off, int len) {

//        final Date date = new Date();
//        final DateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm");
//        final String time = new String(format.format(date));
        final String message = new String(buf, off, len);
        this.text.append(message);
        //使滑轮滚到最下面
        this.text.setCaretPosition(this.text.getText().length()-1);
    }

}
