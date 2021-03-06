package com;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {
    public static final int GAME_WIDTH = 1450, GAME_HEIGHT = 1000;
    private Tank tank = new Tank(200, 400, Dir.UP, 20, Group.GOOD, false, this);
    private ArrayList<Tank> tankList = new ArrayList<>();
    private List<Bullet> bullets = new ArrayList<>();
    private List<Explode> explodes = new ArrayList<>();

    public TankFrame() {
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
     *
     * @param g 画笔
     */
    @Override
    public void paint(Graphics g) {
        for (Bullet bullet : bullets) {
            bullet.paint(g);
        }

        for (Tank tank : tankList) {
            tank.paint(g);
        }

        for (Tank value : tankList) {
            for (Bullet bullet : bullets) {
                value.collide(bullet);
            }
        }

        for (Explode explode : explodes) {
            explode.paint(g);
        }

        tank.paint(g);
        bullets.removeIf(bullet -> !bullet.isLiving());
        tankList.removeIf(tank -> !tank.isLiving());
        explodes.removeIf(explode-> explode.getStep() == 0);
        Color color = g.getColor();
        g.setColor(Color.yellow);
        g.drawString("子弹数量" + bullets.size(), 20, 50);
        g.drawString("敌方坦克" + tankList.size(), 20, 80);
        g.drawString("爆炸数量" + explodes.size(), 20, 110);
        g.setColor(color);
    }

    Image offScreenImage = null;

    /**
     * 防止页面闪动方法
     *
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
                    tank.fire();
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

    public List<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(List<Bullet> bullets) {
        this.bullets = bullets;
    }

    public ArrayList<Tank> getTankList() {
        return tankList;
    }

    public void setTankList(ArrayList<Tank> tankList) {
        this.tankList = tankList;
    }

    public List<Explode> getExplodes() {
        return explodes;
    }

    public void setExplodes(List<Explode> explodes) {
        this.explodes = explodes;
    }
}
