package com.start;

/**
 * 题目: 1~5随机生成1~7随机
 */
public class S09 {
    public static void main(String[] args) {
        int times = 1000000;
        int c1 = 0;
        int c2 = 0;
        int c3 = 0;
        int c4 = 0;
        int c5 = 0;
        int c6 = 0;
        int c7 = 0;
        for (int i = 0; i < times; i++) {
            int g = g();
            switch (g) {
                case 1:
                    c1++;
                    break;
                case 2:
                    c2++;
                    break;
                case 3:
                    c3++;
                    break;
                case 4:
                    c4++;
                    break;
                case 5:
                    c5++;
                    break;
                case 6:
                    c6++;
                    break;
                case 7:
                    c7++;
                    break;
            }
        }
        System.out.println((double)c1 / (double)times);
        System.out.println((double)c2 / (double)times);
        System.out.println((double)c3 / (double)times);
        System.out.println((double)c4 / (double)times);
        System.out.println((double)c5 / (double)times);
        System.out.println((double)c6 / (double)times);
        System.out.println((double)c7 / (double)times);
    }

    /**
     * 1~7概率发生器
     * @return
     */
    public static int g() {
        int res = 0;
        /*
        for (int i = 0; i < 3; i++) {
            res += p() * Math.pow(2, i);
        }
         */
        do {
            for (int i = 0; i < 3; i++) {
                res += p() << i;
            }
        } while (res == 0);
        return res;
    }

    /**
     * 根据1~5随机数 生成0-1概率发生器
     * @return 1 0 概率发生 50%
     */
    public static int p() {
        /*
        int f = f();
        if (f < 3) return 0;
        else if (f > 3) return 1;
        else {
            return p();
        }
         */

        int ans = 0;
        do {
            ans = f();
        } while (ans == 3);
        return ans > 3 ? 1 : 0;
    }

    /**
     * 默认函数
     * @return 返回1~5随机数
     */
    public static int f() {
        return (int)(Math.random() * 5 + 1);
    }
}
