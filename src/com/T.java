package com;

public class T {
    public static void main(String[] args) {
        TankFrame tankFrame = new TankFrame();
        for (int i = 0; i < 5; i++) {
            Tank tank = new Tank(200 + 80 * i, 200, Dir.DOWN, 1, Group.BAD, tankFrame);
            tankFrame.getTankList().add(tank);
        }

        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tankFrame.repaint();
        }
    }
}
