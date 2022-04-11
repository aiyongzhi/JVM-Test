package 单例模式;

//我们可以加入双检查来确保线程安全
//来确保减少同步代码块的方式可行
public class Mgr06 {
    private static Mgr06 INSTANCE;

    private Mgr06() {
    }

    ;

    public static Mgr06 getInstance() {
        if (INSTANCE == null) {
            synchronized (Mgr06.class) {
                if (INSTANCE == null) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new Mgr06();
                }
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() ->
                    System.out.println(Mgr06.getInstance().hashCode())).start();
        }
    }
}
