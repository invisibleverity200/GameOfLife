import java.util.ArrayList;
import java.util.Random;

public class Game implements Runnable {
    private boolean[][] gameState;
    private long speed;
    private int y_Size;
    private int x_Size;
    private int size;
    private int seed;
    private Flag flag;

    public int getSize() {
        return size;
    }

    public boolean[][] getGameState() {
        return gameState;
    }

    public int getY_Size() {
        return y_Size;
    }

    public int getX_Size() {
        return x_Size;
    }

    Game(int size, long speed, int seed, Flag flag) {
        this.x_Size = 1920 / size;
        this.y_Size = 1080 / size;
        this.seed = seed;
        gameState = new boolean[1080 / size][1920 / size];
        this.speed = speed;
        this.size = size;
        this.flag = flag;
        init();
    }

    private void init() {
        Random random = new Random();
        for (int y = 0; y < y_Size; y++) {
            for (int x = 0; x < x_Size; x++) {
                gameState[y][x] = (random.nextInt(seed) < 10);
            }
        }
    }

    void step() {
        for (int y = 0; y < y_Size; y++) {
            for (int x = 0; x < x_Size; x++) {
                int neighbourCount = getNeighbourCount(new int[]{y, x});
                if (neighbourCount > 3 || neighbourCount < 2) {
                    gameState[y][x] = false;
                } else if (!isAlive(new int[]{y, x}) && neighbourCount == 3) {
                    gameState[y][x] = true;
                }
            }
        }

    }

    void update(int size, long speed, int seed) {
        this.x_Size = 1920 / size;
        this.y_Size = 1080 / size;
        this.seed = seed;
        gameState = new boolean[1080 / size][1920 / size];
        this.speed = speed;
        this.size = size;
        init();
        flag.flag = true;
    }

    private int getNeighbourCount(int[] pos) {
        int count = 0;
        for (int y = -1; y < 2; y++) {
            for (int x = -1; x < 2; x++) {
                if (!(y == 0 & x == 0) && ((y + pos[0]) < y_Size && (x + pos[1]) < x_Size) && !((y + pos[0]) < 0 || (x + pos[1]) < 0)) {
                    if (gameState[pos[0] + y][pos[1] + x]) count++;
                }
            }
        }
        return count;
    }

    private boolean isAlive(int[] pos) {
        return gameState[pos[0]][pos[1]];
    }

    @Override
    public void run() {
        while (flag.flag) {
            try {
                Thread.sleep(speed);
                step();
            } catch (InterruptedException e) {

            }
        }
        System.out.println("Game Thread Closed!");
    }
}
