import java.awt.*;

public class Bullet {
    private int x, y;
    private Dir dir = Dir.DOWN;
    private int SPEED;
    private int WIDTH = 10, HEIGHT = 10;
    private boolean live = true;

    public Bullet() {
    }

    public Bullet(int x, int y, Dir dir, int SPEED) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.SPEED = SPEED;
    }

    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.yellow);
        g.fillOval(x, y, WIDTH, HEIGHT);
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
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            this.live = false;
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

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }
}
