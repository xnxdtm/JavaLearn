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
            if (this.group == Group.BAD && random.nextInt(10) > 8) {
                fire();
            }
        }
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
        Rectangle bulletRec = new Rectangle(bullet.getX(), bullet.getY(), Bullet.WIDTH, Bullet.HEIGHT);
        Rectangle tankRec = new Rectangle(this.x, this.y, Tank.WIDTH, Tank.HEIGHT);
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
}
