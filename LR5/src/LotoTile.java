import java.awt.*;

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
