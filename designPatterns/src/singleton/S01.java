package singleton;

/**
 * @author wujing
 * @version V1.0
 * @Description 饿汉式单例
 * 核心点:
 * 构造方法私有
 * 通过实例方法获取实例
 */
public class S01 {
    private static final S01 INSTANCE = new S01();

    private S01() {
    }

    public static S01 getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(S01.getInstance().hashCode());
            }).start();
        }
    }
}
