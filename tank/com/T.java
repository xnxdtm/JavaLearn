package com;

import java.util.Objects;

public class T {
    public static void main(String[] args) {
        TankFrame tankFrame = new TankFrame();
        int initTankCount = Integer.parseInt((String) Objects.requireNonNull(PropertyMgr.get("initCount")));
        for (int i = 0; i < initTankCount; i++) {
            Tank tank = new Tank(200 + 80 * i, 200, Dir.DOWN, 1, Group.BAD, true, tankFrame);
            tankFrame.getTankList().add(tank);
        }

        // new Thread(() -> new Audio("audio/war1.wav").loop()).start();

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
