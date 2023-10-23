import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LotoGame extends JFrame {
    private Board board;
    private List<Player> players;
    private int currentPlayerIndex;
    private final int boardSize = 5;
    private Timer gameTimer;

    public LotoGame() {
        board = new Board(boardSize);
        players = new ArrayList<>();
        players.add(new Player("Красный", Color.RED, board));
        players.add(new Player("Синий", Color.BLUE, board));
        players.add(new Player("Зеленый", Color.GREEN, board));
        players.add(new Player("Желтый", Color.YELLOW, board));
        currentPlayerIndex = 0;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(boardSize, boardSize));
        setTitle("Игра Лото");

        for (int i = 0; i < boardSize * boardSize; i++) {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(50, 50));
            add(button);
            board.addTile(new LotoTile(i + 1));
        }

        gameTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player currentPlayer = players.get(currentPlayerIndex);
                if (!currentPlayer.isFinished()) {
                    currentPlayer.placeTile();
                    updateBoard();
                } else {
                    currentPlayerIndex++;
                }

                if (currentPlayerIndex >= players.size()) {
                    currentPlayerIndex = 0;
                }

                if (board.isFull()) {
                    gameTimer.stop();
                    announceWinner();
                }
            }
        });

        gameTimer.start();
    }

    private void updateBoard() {
        for (int i = 0; i < board.getSize(); i++) {
            LotoTile tile = board.getTile(i);
            JButton button = (JButton) getContentPane().getComponent(i);
            if (tile.getColor() != null) {
                button.setBackground(tile.getColor());
                button.setText(String.valueOf(tile.getNumber()));
            }
        }
    }

    private void announceWinner() {
        int maxTilesPlaced = -1;
        String winner = "";

        for (Player player : players) {
            int tilesPlaced = player.getTilesPlaced();
            if (tilesPlaced > maxTilesPlaced) {
                maxTilesPlaced = tilesPlaced;
                winner = player.getName();
            }
        }

        JOptionPane.showMessageDialog(this, "Игра окончена! Победил игрок " + winner, "Победитель", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LotoGame game = new LotoGame();
            game.setVisible(true);
        });
    }
}

class Player {
    private String name;
    private Color color;
    private Board board;
    private int tilesPlaced;
    private Random random;

    public Player(String name, Color color, Board board) {
        this.name = name;
        this.color = color;
        this.board = board;
        this.tilesPlaced = 0;
        this.random = new Random();
    }

    public String getName() {
        return name;
    }

    public boolean isFinished() {
        return tilesPlaced >= board.getSize();
    }

    public int getTilesPlaced() {
        return tilesPlaced;
    }

    public void placeTile() {
        int tileNumber;
        boolean tilePlaced;
        do {
            tileNumber = random.nextInt(board.getSize()) + 1;
            tilePlaced = board.placeTile(tileNumber, color);
        } while (!tilePlaced);

        tilesPlaced++;
        System.out.println(name + ", фишка " + tileNumber);
    }
}

class Board {
    private int size;
    private List<LotoTile> tiles;

    public Board(int size) {
        this.size = size;
        tiles = new ArrayList<>();
    }

    public int getSize() {
        return size * size;
    }

    public synchronized boolean placeTile(int tileNumber, Color color) {
        for (LotoTile tile : tiles) {
            if (tile.getNumber() == tileNumber && tile.getColor() == null) {
                tile.setColor(color);
                return true;
            }
        }
        return false;
    }

    public synchronized void addTile(LotoTile tile) {
        tiles.add(tile);
    }

    public LotoTile getTile(int index) {
        return tiles.get(index);
    }

    public boolean isFull() {
        for (LotoTile tile : tiles) {
            if (tile.getColor() == null) {
                return false;
            }
        }
        return true;
    }
}

class LotoTile {
    private int number;
    private Color color;

    public LotoTile(int number) {
        this.number = number;
        this.color = null;
    }

    public int getNumber() {
        return number;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
