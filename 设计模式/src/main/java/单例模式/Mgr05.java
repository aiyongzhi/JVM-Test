package 单例模式;

//我们可以通过减少同步代码块的方法来提高效率，但是失败了
//此时线程又变成不安全的了
public class Mgr05 {
    private static Mgr05 INSTANCE;

    private Mgr05() {
    }

    ;

    public static Mgr05 getInstance() {
        if (INSTANCE == null) {
            synchronized (Mgr05.class) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                INSTANCE = new Mgr05();
            }
        }
        return INSTANCE;
    }

    //测试线程是否安全
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() ->
                    System.out.println(Mgr05.getInstance().hashCode())).start();
        }
    }
}
