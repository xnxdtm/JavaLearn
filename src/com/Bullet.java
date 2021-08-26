package com;

import java.awt.*;

public class Bullet {
    private int x, y;
    private Dir dir = Dir.DOWN;
    private int SPEED;
    public static int WIDTH = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();
    private boolean living = true;
    private Group group = Group.BAD;
    private Rectangle rectangle = new Rectangle();

    public Bullet() {
    }

    public Bullet(int x, int y, Dir dir, int SPEED, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.SPEED = SPEED;
        this.group = group;
        this.rectangle.x = this.x;
        this.rectangle.y = this.y;
        this.rectangle.width = WIDTH;
        this.rectangle.height = HEIGHT;
    }

    public void paint(Graphics g) {
        move();
        drawBullet(g);
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            this.living = false;
        }
    }

    private void drawBullet(Graphics g) {
        switch (dir) {
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
        }
    }

    private void move() {
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
        updateRectangle();
    }

    private void updateRectangle() {
        this.rectangle.x = this.x;
        this.rectangle.y = this.y;
    }

    public void die() {
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
