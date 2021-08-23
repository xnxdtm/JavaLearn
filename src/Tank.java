import java.awt.*;

public class Tank {
    private int x = 200, y = 200;
    private Dir dir = Dir.DOWN;
    private int SPEED;
    private boolean moving = false;

    public Tank() {
    }

    public Tank(int x, int y, Dir dir, int SPEED) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.SPEED = SPEED;
    }

    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.red);
        g.fillRect(x, y, 50, 50);
        if (moving) {
            switch (dir) {
                case UP:
                    y -= SPEED;
                    break;
                case DOWN:
                    y += SPEED;
                    break;
                case LEFT:
                    x -= SPEED;
                    break;
                case RIGHT:
                    x += SPEED;
                    break;
                default:
                    break;
            }
        }
        g.setColor(color);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public int getSPEED() {
        return SPEED;
    }

    public void setSPEED(int SPEED) {
        this.SPEED = SPEED;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
}
