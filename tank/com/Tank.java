package com;

import java.awt.*;
import java.util.Random;

public class Tank {
    private int x, y;
    private Dir dir = Dir.DOWN;
    private int SPEED;
    private boolean moving = true;
    public static int WIDTH = ResourceMgr.tankD.getWidth();
    public static int HEIGHT = ResourceMgr.tankD.getHeight();
    private TankFrame tf;
    private boolean living = true;
    private Random random = new Random();
    private Group group = Group.BAD;
    private Rectangle rectangle = new Rectangle();

    public Tank() {
    }

    public Tank(int x, int y, Dir dir, int SPEED, Group group, boolean moving, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.SPEED = SPEED;
        this.tf = tf;
        this.group = group;
        this.moving = moving;
        this.rectangle.x = this.x;
        this.rectangle.y = this.y;
        this.rectangle.width = WIDTH;
        this.rectangle.height = HEIGHT;
    }

    public void paint(Graphics g) {
        move();
        drawTankImage(g);
    }

    private void drawTankImage(Graphics g) {
        if (!living) {
            tf.getTankList().remove(this);
        }
        switch (dir) {
            case UP:
                g.drawImage(ResourceMgr.tankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD, x, y, null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.tankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR, x, y, null);
                break;
        }
    }

    private void move() {
        if (!moving) return;
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
        if (this.group == Group.BAD && random.nextInt(100) > 95) {
            fire();
        }
        if (this.group == Group.BAD && random.nextInt(100) > 95) {
            randomDir();
        }
        boundCheck();
        updateRectangle();
    }

    private void updateRectangle() {
        this.rectangle.x = this.x;
        this.rectangle.y = this.y;
    }

    /**
     * 碰撞检测
     */
    private void boundCheck() {
        if (this.x < 0) x = 0;
        if (this.y < 30) y = 30;
        if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH) x = TankFrame.GAME_WIDTH - Tank.WIDTH;
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT) y = TankFrame.GAME_HEIGHT - Tank.HEIGHT;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        Bullet bullet = new Bullet(
                this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2,
                this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2,
                this.dir, 15, group);
        tf.getBullets().add(bullet);
    }

    public void collide(Bullet bullet) {
        if (bullet.getGroup() == group) return;
        Rectangle bulletRec = bullet.getRectangle();
        Rectangle tankRec = this.rectangle;
        if (bulletRec.intersects(tankRec)) {
            this.die();
            bullet.die();
            Explode explode = new Explode(bullet.getX(), bullet.getY());
            tf.getExplodes().add(explode);
        }
    }

    private void die() {
        this.living = false;
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

    public boolean isLiving() {
        return living;
    }

    public void setLiving(boolean living) {
        this.living = living;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}
