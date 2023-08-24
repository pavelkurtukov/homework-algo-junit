import java.util.Objects;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "{" + x + "," + y + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (this.getClass().equals(obj.getClass())) {
            return this.x == ((Coordinates) obj).x && this.y == ((Coordinates) obj).y;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
