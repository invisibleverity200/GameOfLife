import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GUI extends JFrame {
    JButton start;
    PaintCanvas canvas;
    JPanel canvasPanel;
    public volatile Game game;
    boolean flag = true;


    GUI() {
        this.setSize(1920, 1080);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel setting = new JPanel();
        GridLayout gridLayout = new GridLayout(1, 12);
        setting.setLayout(gridLayout);

        setting.setSize(1920, 100);

        JTextField size = new JTextField("Size");
        size.setHorizontalAlignment(JTextField.CENTER);
        size.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                JTextField field = (JTextField) ke.getSource();
                System.out.println(ke.getKeyChar());
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    field.setEditable(true);
                } else {
                    field.setEditable(false);
                    field.setBackground(Color.white);
                    field.setText("");
                }
            }
        });

        JTextField speedJText = new JTextField("Speed");
        speedJText.setHorizontalAlignment(JTextField.CENTER);
        speedJText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                JTextField field = (JTextField) ke.getSource();
                System.out.println(ke.getKeyChar());
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    field.setEditable(true);
                } else {
                    field.setEditable(false);
                    field.setBackground(Color.white);
                    field.setText("");
                }
            }
        });

        JTextField seedJText = new JTextField("Seed");
        seedJText.setHorizontalAlignment(JTextField.CENTER);
        seedJText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                JTextField field = (JTextField) ke.getSource();
                System.out.println(ke.getKeyChar());
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    field.setEditable(true);
                } else {
                    field.setEditable(false);
                    field.setBackground(Color.white);
                    field.setText("");
                }
            }
        });


        start = new JButton("Start");

        setting.add(start);
        setting.add(size);
        setting.add(speedJText);
        setting.add(seedJText);
        canvasPanel = new JPanel();

        this.add(setting);
        this.add(canvasPanel);
        Flag flag = new Flag();
        game = new Game(1, 1, 1, flag);
        canvas = new PaintCanvas(canvasPanel, game, flag);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (start.getText().equals("Start")) {
                    game.update(Integer.valueOf(size.getText()), Integer.valueOf(speedJText.getText()), Integer.valueOf(seedJText.getText()));
                    new Thread(game).start();
                    new Thread(canvas).start();
                    start.setText("Stop");
                } else {
                    flag.flag = false;
                    start.setText("Start");
                }


            }
        });


        this.setSize(1920, 5);
        this.setSize(1920, 1080); //FIXME without this buttons do not appear
    }
}
