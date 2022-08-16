package singleton;

/**
 * @author wujing
 * @version V1.0
 * @Description 内部类单例
 */
public class S03 {


    private S03() {
    }

    private static class S03Holder {
        private static final S03 INSTANCE = new S03();
    }

    public static S03 getInstance() {
        return S03Holder.INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(S03.getInstance().hashCode());
            }).start();
        }
    }
}
