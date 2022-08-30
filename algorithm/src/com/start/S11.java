package com.start;

import static com.Tool.*;

/**
 * 题目: 有序数组中找到num
 * 思路:
 */
public class S11 {
    public static void main(String[] args) {
        int times = 10000;
        for (int i = 0; i < times; i++) {
            int[] arr = randomArr(30, 20);
            printArr(arr);
        }
    }

    public static int f(int[] arr, int num) {
        int N = arr.length;
        int l = 0;
        int r = N - 1;
        
        int m = (r - l) / 2;  // 3,4 => 3  3.5  4,5 => 4   3,5 => 4  3,3 => 3
        if (num > arr[m]) {
            l = m;
        } else if (num == arr[m]) {
            return m;
        } else {
            r = m;
        }
        if (r - l == 1 && arr[r] == num) {
            return r;
        } else {
            return -1;
        }
        
        
    }

}
