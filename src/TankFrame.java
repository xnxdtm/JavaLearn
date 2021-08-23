import java.awt.*;
import java.awt.event.*;

public class TankFrame extends Frame {
    private static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;
    private Tank tank = new Tank(200, 200, Dir.UP, 10);

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
            setMainTankDir(true);
        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {
            int keyCode = keyEvent.getKeyCode();
            switch (keyCode) {
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
            setMainTankDir(false);
        }

        private void setMainTankDir(boolean pressDirKey) {
            if (pressDirKey){
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
            }
            tank.setMoving(pressDirKey);
        }
    }
}
