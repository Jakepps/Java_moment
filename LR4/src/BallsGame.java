import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class BallsGame extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private static final int CELL_SIZE = 40;
    private static final int NUM_ROWS = HEIGHT / CELL_SIZE;
    private static final int NUM_COLS = WIDTH / CELL_SIZE;
    private static final Color[] BALL_COLORS = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE};

    private final Color[][] grid;
    private final Random random = new Random();
    private int selectedRow = -1;
    private int selectedCol = -1;

    public BallsGame() {
        grid = new Color[NUM_ROWS][NUM_COLS];
        initGrid();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clickX = e.getX() / CELL_SIZE;
                int clickY = e.getY() / CELL_SIZE;
                if (isValidSelection(clickX, clickY)) {
                    if (selectedRow == -1) {
                        selectedRow = clickY;
                        selectedCol = clickX;
                    } else {
                        swapCells(selectedRow, selectedCol, clickY, clickX);
                        selectedRow = -1;
                        selectedCol = -1;
                    }
                    repaint();
                }
            }
        });

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveBallsDown();
                checkMatches();
                repaint();
            }
        });
        timer.start();

        setTitle("Balls Game");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initGrid() {
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                grid[row][col] = getRandomColor();
            }
        }
    }

    private Color getRandomColor() {
        return BALL_COLORS[random.nextInt(BALL_COLORS.length)];
    }

    private boolean isValidSelection(int row, int col) {
        return row >= 0 && row < NUM_ROWS && col >= 0 && col < NUM_COLS;
    }

    private void swapCells(int row1, int col1, int row2, int col2) {
        Color temp = grid[row1][col1];
        grid[row1][col1] = grid[row2][col2];
        grid[row2][col2] = temp;
    }

    private void moveBallsDown() {
        for (int col = 0; col < NUM_COLS; col++) {
            for (int row = NUM_ROWS - 1; row >= 0; row--) {
                if (grid[row][col] == null) {
                    for (int aboveRow = row - 1; aboveRow >= 0; aboveRow--) {
                        if (grid[aboveRow][col] != null) {
                            grid[row][col] = grid[aboveRow][col];
                            grid[aboveRow][col] = null;
                            break;
                        }
                    }
                }
            }
        }
    }

    private void checkMatches() {
     

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                Color ballColor = grid[row][col];
                if (ballColor != null) {
                    g.setColor(ballColor);
                    g.fillOval(col * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                BallsGame game = new BallsGame();
                game.setVisible(true);
            }
        });
    }
}
