import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {
    private static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;
    private int x = 200, y = 200;

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
        Color color = g.getColor();
        g.setColor(Color.red);
        g.fillRect(x, y, 50, 50);
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

    private class MyKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent keyEvent) {

        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {
            int keyCode = keyEvent.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_UP:
                    y -= 10;
                    break;
                case KeyEvent.VK_DOWN:
                    y += 10;
                    break;
                case KeyEvent.VK_LEFT:
                    x -= 10;
                    break;
                case KeyEvent.VK_RIGHT:
                    x += 10;
                    break;
                default:
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {

        }
    }
}
