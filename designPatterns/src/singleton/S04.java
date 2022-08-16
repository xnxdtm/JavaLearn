package singleton;
/**
 * @author wujing
 * @version V1.0
 * @Description 枚举单例
 */
public enum S04 {
    INSTANCE;

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(S04.INSTANCE.hashCode());
            }).start();
        }
    }
}
