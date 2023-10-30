import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LotoGame extends JFrame {
    private Board board;
    private List<Player> players;
    private final int boardSize = 5;
    private Timer gameTimer;

    private Timer restartTimer;
    private boolean restartPending = false;

    public LotoGame() {
        board = new Board(boardSize);
        players = new ArrayList<>();
        players.add(new Player("Красный", Color.RED, board));
        players.add(new Player("Синий", Color.BLUE, board));
        players.add(new Player("Зеленый", Color.GREEN, board));
        players.add(new Player("Желтый", Color.YELLOW, board));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(boardSize, boardSize));
        setTitle("Лото");

        for (int i = 0; i < boardSize * boardSize; i++) {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(50, 50));
            add(button);
            board.addTile(new LotoTile(i + 1));

            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        if (!restartPending) {
                            restartPending = true;
                            restartTimer.start();
                        }
                    }
                }
            });
        }

        gameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random random = new Random();
                int randomIndex;
                do {
                    randomIndex = random.nextInt(players.size());
                } while (players.get(randomIndex).isFinished());

                Player currentPlayer = players.get(randomIndex);
                currentPlayer.placeTile();
                updateBoard();

                if (board.isFull()) {
                    gameTimer.stop();
                    announceWinner();
                }
            }
        });

        gameTimer.start();

        restartTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartPending = false;
                restartTimer.stop();
                resetGame();
            }
        });
    }

    private void updateBoard() {
        for (int i = 0; i < board.getSize(); i++) {
            LotoTile tile = board.getTile(i);
            JButton button = (JButton) getContentPane().getComponent(i);
            if (tile.getColor() != null) {
                button.setBackground(tile.getColor());
                button.setText(String.valueOf(tile.getNumber()));
                button.setOpaque(true);
                button.setBorderPainted(false);
            }
            else {
                button.setBackground(null);
                button.setText("");
                button.setOpaque(false);
                button.setBorderPainted(true);
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

    private void resetGame() {
        board.clear();
        for (Player player : players) {
            player.reset();
        }
        gameTimer.start();
        updateBoard();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LotoGame game = new LotoGame();
            game.setVisible(true);
        });
    }
}
