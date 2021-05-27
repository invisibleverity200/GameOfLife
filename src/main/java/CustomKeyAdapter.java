import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CustomKeyAdapter extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent ke) {
        JTextField field = (JTextField) ke.getSource();
        if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
            field.setEditable(true);
        } else {
            field.setEditable(false);
            field.setBackground(Color.white);
            field.setText("");
        }
    }

}
