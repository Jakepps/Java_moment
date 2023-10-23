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
        setTitle("Лото");

        for (int i = 0; i < boardSize * boardSize; i++) {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(50, 50));
            add(button);
            board.addTile(new LotoTile(i + 1));
        }

        gameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player currentPlayer = players.get(currentPlayerIndex);
                if (!currentPlayer.isFinished()) {
                    currentPlayer.placeTile();
                    updateBoard();
                }

                currentPlayerIndex++;

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
