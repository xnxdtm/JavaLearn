import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {
    public static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;
    private Tank tank = new Tank(200, 200, Dir.UP, 10);
    private List<Bullet> bullets = new ArrayList<>();

    public TankFrame () {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        addKeyListener(new MyKeyListener());
    }

    /**
     * 使用画笔进行绘画
     * @param g 画笔
     */
    @Override
    public void paint(Graphics g) {
        tank.paint(g);
        for (Bullet bullet : bullets) {
            bullet.paint(g);
        }
        bullets.removeIf(bullet -> !bullet.isLive());
        Color color = g.getColor();
        g.setColor(Color.yellow);
        g.drawString("子弹数量" + bullets.size(), 20, 50);
        g.setColor(color);
    }

    Image offScreenImage = null;

    /**
     * 防止页面闪动方法
     * @param g 画笔
     */
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    private class MyKeyListener extends KeyAdapter {
        boolean bU = false;
        boolean bD = false;
        boolean bL = false;
        boolean bR = false;

        @Override
        public void keyTyped(KeyEvent keyEvent) {

        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {
            int keyCode = keyEvent.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {
            int keyCode = keyEvent.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_SPACE:
                    Bullet bullet = new Bullet(tank.getX(), tank.getY(), tank.getDir(), 10);
                    bullets.add(bullet);
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if (bU || bD || bL || bR) {
                tank.setMoving(true);
                if (bU) {
                    tank.setDir(Dir.UP);
                }
                if (bD) {
                    tank.setDir(Dir.DOWN);
                }
                if (bL) {
                    tank.setDir(Dir.LEFT);
                }
                if (bR) {
                    tank.setDir(Dir.RIGHT);
                }
            } else {
                tank.setMoving(false);
            }
        }
    }
}
