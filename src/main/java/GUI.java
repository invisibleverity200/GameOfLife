import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GUI extends JFrame {
    JButton start;
    PaintCanvas canvas;
    JPanel canvasPanel;
    public volatile Game game;


    GUI() {
        this.setSize(1920, 1080);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("GameOfLife");

        JPanel setting = new JPanel();
        GridLayout gridLayout = new GridLayout(2, 12);
        setting.setLayout(gridLayout);

        setting.setSize(1920, 40);

        JTextField size = new JTextField("Size");
        size.setHorizontalAlignment(JTextField.CENTER);
        size.addKeyListener(new CustomKeyAdapter());

        JTextField speedJText = new JTextField("Speed");
        speedJText.setHorizontalAlignment(JTextField.CENTER);
        speedJText.addKeyListener(new CustomKeyAdapter());

        JTextField seedJText = new JTextField("Seed");
        seedJText.setHorizontalAlignment(JTextField.CENTER);
        seedJText.addKeyListener(new CustomKeyAdapter());

        JTextField bigger = new JTextField("bigger");
        bigger.setHorizontalAlignment(JTextField.CENTER);
        bigger.addKeyListener(new CustomKeyAdapter());

        JTextField notBigger = new JTextField("not bigger");
        notBigger.setHorizontalAlignment(JTextField.CENTER);
        notBigger.addKeyListener(new CustomKeyAdapter());

        JTextField equal = new JTextField("equal");
        equal.setHorizontalAlignment(JTextField.CENTER);
        equal.addKeyListener(new CustomKeyAdapter());


        start = new JButton("Start");

        setting.add(start);
        setting.add(size);
        setting.add(speedJText);
        setting.add(seedJText);
        setting.add(notBigger);
        setting.add(bigger);
        setting.add(equal);
        canvasPanel = new JPanel();

        this.add(setting);
        this.add(canvasPanel);
        Flag flag = new Flag();
        game = new Game(1, 1, 1, flag);
        canvas = new PaintCanvas(canvasPanel, game, flag);

        start.addActionListener((ActionEvent e) -> {
                    if (start.getText().equals("Start")) {
                        game.update(Integer.parseInt(size.getText()), Integer.parseInt(speedJText.getText()),
                                Integer.parseInt(seedJText.getText()), Integer.parseInt(bigger.getText()),
                                Integer.parseInt(notBigger.getText()), Integer.parseInt(equal.getText()));
                        new Thread(game).start();
                        new Thread(canvas).start();
                        start.setText("Stop");
                    } else {
                        flag.flag = false;
                        start.setText("Start");
                    }
                }
        );
        this.setSize(1920, 10);
        this.setSize(1920, 1080);
    }
}
