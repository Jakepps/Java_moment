import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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

    public void clear() {
        for (LotoTile tile : tiles) {
            tile.setColor(null);
        }
    }
}
