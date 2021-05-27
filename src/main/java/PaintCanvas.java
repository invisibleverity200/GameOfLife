import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PaintCanvas extends Canvas implements Runnable {
    Game game;
    Flag flag;

    PaintCanvas(JPanel frame, Game game, Flag flag) {
        this.setSize(1920, 1080);
        this.setLocation(0, 0);
        frame.add(this);
        this.createBufferStrategy(4);
        this.flag = flag;
        this.game = game;
    }

    @Override
    public void run() {
        while (flag.flag) {
            try {
                draw();
            } catch (IOException e) {

            }
        }
        System.out.println("Canvas Thread closed!");
    }

    void draw() throws IOException {
        Graphics g = this.getBufferStrategy().getDrawGraphics();
        g.clearRect(0, 0, 1920, 1080);
        //g.drawImage(ImageIO.read(new File("C:\\Users\\airph\\Pictures\\Wiese.png")), 0, 910, 1920, 1090 - 910, null);

        for (int y = 0; y < game.getY_Size(); y++) {
            for (int x = 0; x < game.getX_Size(); x++) {
                if (game.getGameState()[y][x].mutation > 9900) g.setColor(Color.red);
                else if (game.getGameState()[y][x].alive) g.setColor(Color.green);
                else g.setColor(Color.white);
                g.fillRect(x * game.getSize(), y * game.getSize(), game.getSize(), game.getSize());

            }
        }
        g.dispose();

        this.getBufferStrategy().show();
    }
}
