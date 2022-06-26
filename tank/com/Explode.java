package com;

import java.awt.*;

public class Explode {
    private int x, y;
    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();
    private boolean living = true;
    private int step = 0;

    public Explode() {
    }

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics g) {
        drawExplodVideo(g);
        playExplodAudio();
    }

    private void playExplodAudio() {
    }

    private void drawExplodVideo(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        if (step == ResourceMgr.explodes.length) {
            step = 0;
        }
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

    public boolean isLiving() {
        return living;
    }

    public void setLiving(boolean living) {
        this.living = living;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }
}
