import java.util.ArrayList;
import java.util.Random;

public class Game implements Runnable {
    private Cell[][] gameState;
    private long speed;
    private int y_Size;
    private int x_Size;
    private int size;
    private int seed;
    private Flag flag;
    private Random random = new Random();

    public int getSize() {
        return size;
    }

    public Cell[][] getGameState() {
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
        gameState = new Cell[1080 / size][1920 / size];
        this.speed = speed;
        this.size = size;
        this.flag = flag;
        init();
    }

    private void init() {
        for (int y = 0; y < y_Size; y++) {
            for (int x = 0; x < x_Size; x++) {
                gameState[y][x] = new Cell();
                gameState[y][x].alive = (random.nextInt(seed) < 10);
            }
        }
    }

    void step() {
        for (int y = 0; y < y_Size; y++) {
            for (int x = 0; x < x_Size; x++) {
                int neighbourCount = getNeighbourCount(new int[]{y, x});
                if (gameState[y][x].mutation > 9900) {
                    killNeighbours(new int[]{y, x});
                } else if (neighbourCount > 3 || neighbourCount < 2 || gameState[y][x].age > 10000) {
                    gameState[y][x].alive = false;
                    gameState[y][x].age = 0;
                } else if (!isAlive(new int[]{y, x}) && neighbourCount == 3) {
                    gameState[y][x].alive = true;
                    gameState[y][x].mutation = random.nextInt(1000);
                }
                if (isAlive(new int[]{y, x})) gameState[y][x].age++;
            }
        }

    }

    void update(int size, long speed, int seed) {
        this.x_Size = 1920 / size;
        this.y_Size = 1080 / size;
        this.seed = seed;
        gameState = new Cell[1080 / size][1920 / size];
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
                    if (gameState[pos[0] + y][pos[1] + x].alive) count++;
                }
            }
        }
        return count;
    }

    private void killNeighbours(int[] pos) {
        for (int y = -1; y < 2; y++) {
            for (int x = -1; x < 2; x++) {
                if (!(y == 0 & x == 0) && ((y + pos[0]) < y_Size && (x + pos[1]) < x_Size) && !((y + pos[0]) < 0 || (x + pos[1]) < 0)) {
                    gameState[pos[0] + y][pos[1] + x].alive = false;
                }
            }
        }
    }

    private boolean isAlive(int[] pos) {
        return gameState[pos[0]][pos[1]].alive;
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
