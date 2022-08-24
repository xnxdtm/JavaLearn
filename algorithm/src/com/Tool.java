package com;

import java.util.Random;

/**
 * 工具类
 */
public final class Tool {
    public static void swapArr(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static <T> void printArr(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1)
                System.out.print(arr[i] + "]");
            else
                System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }

    public static int[] randomArr(int maxLength, int maxValue) {
        int length = (int) (Math.random() * maxLength);
        int[] res = new int[length];
        for (int i = 0; i < maxLength; i++) {
            res[i] = (int) (Math.random() * maxValue);
        }
        return res;
    }

    public static boolean isSameArr(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) return false;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) return false;
        }
        return true;
    }

    public static void printByBoolean(Boolean b) {
        if (b)
            System.out.println("成功");
        else
            System.out.println("失败");
    }

    public static void sortArr(int[] arr) {
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            int maxIndex = 0;
            for (int j = 1; j < N - i; j++) {
                maxIndex = arr[j] > arr[maxIndex] ? j : maxIndex;
            }
            swapArr(arr, N - 1 - i, maxIndex);
        }
    }

    public static void main(String[] args) {
        int[] ints = randomArr(10, 100);
        printArr(ints);
    }
}
