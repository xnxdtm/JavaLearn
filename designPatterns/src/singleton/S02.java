package singleton;

/**
 * @author wujing
 * @version V1.0
 * @Description 懒汉式单例
 */
public class S02 {
    private static S02 INSTANCE;

    private S02() {
    }

    public static S02 getInstance() {
        if (INSTANCE == null) {
            synchronized (S02.class) {
                if (INSTANCE == null) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new S02();
                }
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(S02.getInstance().hashCode());
            }).start();
        }
    }
}
