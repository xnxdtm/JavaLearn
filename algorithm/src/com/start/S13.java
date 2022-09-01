package com.start;

import static com.Tool.*;

/**
 * 题目: 局部最小值问题.
 *      在一个无序数组中相邻两个数不相同, 找到这个数组中的一个局部最小值位置
 *      局部最小:
 *          arr[0] < arr[1] => arr[0] 为一个局部最小
 *          arr[N-1] < arr[N-2] N = arr.length => arr[N-1] 为一个局部最小
 *          arr[i-1] > arr[i] < arr[i+1] => arr[i] 为一个局部最小
 * 思路:
 */
public class S13 {
    public static void main(String[] args) {
        int times = 1000;
        for (int i = 0; i < times; i++) {
            int[] arr = generateArr(20, 10);
            int min = f(arr);
            // 验证是否局部最小
            if (min == 0) {
                if (arr[min] > arr[min + 1]) {
                    System.out.println("错了");
                }
            } else if (min == arr.length - 1) {
                if (arr[min] > arr[min - 1]) {
                    System.out.println("错了");
                }
            } else {
                if (arr[min] > arr[min - 1] || arr[min] > arr[min + 1]){
                    System.out.println("错了");
                }
            }
        }
    }

    public static int[] generateArr(int maxValue, int maxLength) {
        int length;
        do {
            length = (int) (Math.random() * maxLength);
        } while (length < 2);
        int[] arr = new int[length];
        arr[0] = (int) (Math.random() * maxValue);
        for (int i = 1; i < length; i++) {
            do {
                arr[i] = (int) (Math.random() * maxValue);
            } while (arr[i] == arr[i - 1]);
        }
        return arr;
    }

    public static int f(int[] arr) {
        int N = arr.length, L = 0, R = N - 1;
        if (arr[0] < arr[1]) return 0;
        if (arr[N - 1] < arr[N - 2]) return N-1;
        while (L < R) {
            int mid = (R + L) >> 1;
            if (arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1]) {
                return mid;
            } else {
                if (arr[mid] > arr[mid - 1]) {
                    R = mid;
                } else {
                    L = mid;
                }
            }
        }
        return -1;
    }

}
