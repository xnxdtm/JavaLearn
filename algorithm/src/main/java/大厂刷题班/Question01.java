package 大厂刷题班;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
 * @author wujing
 * @version V1.0
 * @Description 给定一个有序数组arr，代表坐落在X轴上的点，给定一个正数K，代表绳子的长度，返回绳子最多压中几个点？即使绳子边缘处盖住点也算盖住
 */
public class Question01 {
    public static void main(String[] args) {
        ArrayList<Integer> sortedArr = sortArrayList(generateArrayList());
        int[] arr = sortedArr.toArray();
    }

    public static int test01(int[] arr, int k) {


        return 0;
    }

    public static ArrayList<Integer> generateArrayList() {
        Random random = new Random();
        int length = random.nextInt(1000);
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            int num = random.nextInt(10000);
            arr.add(num);
        }
        return arr;
    }

    public static ArrayList<Integer> sortArrayList(ArrayList<Integer> arr) {
        arr.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        return arr;
    }
}
