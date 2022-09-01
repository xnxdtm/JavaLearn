package com.start;

import static com.Tool.*;

/**
 * 题目: 有序数组中寻找指定值num第一次出现的位置,最后一次出现的位置
 * 思路:
 */
public class S12 {
    public static void main(String[] args) {
        int times = 10000;
        for (int i = 0; i < times; i++) {
            int[] arr = randomArr(30, 20);
            sortArr(arr);
            int num = (int)(Math.random() * 20);
            // 一个一个对比最左位置
            int exist1 = -1;
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == num) {
                    exist1 = j;
                    break;
                }
            }
            // 二分法对比最左位置
            int exist2 = existLeft(arr, num);
            if (exist2 != exist1) {
                System.out.print("");
                printArr(arr);
                System.out.println("num: " + num);
                System.out.println("index1: " + exist1);
                System.out.println("index2: " + exist2);
            }
            // 一个一个对比最右位置
            int exist3 = -1;
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == num) {
                    exist3 = j;
                }
            }
            // 二分法对比最左位置
            int exist4 = existRight(arr, num);
            if (exist4 != exist3) {
                System.out.print("");
                printArr(arr);
                System.out.println("num: " + num);
                System.out.println("index3: " + exist3);
                System.out.println("index4: " + exist4);
            }
        }
    }

    public static int existRight(int[] arr, int num) {
        int N = arr.length;
        int L = 0;
        int R = N - 1;
        if (N == 0) return -1;
        while (R >= L) {
            int mid = (R + L) / 2;  // 3,4 => 3  4,5 => 4   3,5 => 4  3,3 => 3
            if (num > arr[mid]) {
                L = mid + 1;
            } else if (num == arr[mid]) {
                while (mid + 1 < arr.length && num == arr[mid + 1]) {
                    mid++;
                }
                return mid;
            } else {
                R = mid - 1;
            }
        }
        return -1;
    }

    public static int existLeft(int[] arr, int num) {
        int N = arr.length;
        int l = 0;
        int r = N - 1;
        if (N == 0) return -1;
        while (r >= l) {
            int m = (r + l) / 2;  // 3,4 => 3  4,5 => 4   3,5 => 4  3,3 => 3
            if (num > arr[m]) {
                l = m + 1;
            } else if (num == arr[m]) {
                while (m - 1 >= 0 && num == arr[m - 1]) {
                    m--;
                }
                return m;
            } else {
                r = m - 1;
            }
        }
        return -1;
    }

}
