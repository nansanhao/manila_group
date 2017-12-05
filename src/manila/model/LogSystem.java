package manila.model;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.text.JTextComponent;

//public class MyPrintStream extends PrintStream {
//
//
//}
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

        final Date date = new Date();
        final DateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm");
        final String time = new String(format.format(date));
        final String message = new String(buf, off, len);
        this.text.append(message);
        this.text.setCaretPosition(this.text.getText().length());
    }

}
