package 单例模式;

//我们可以通过上锁来解决线程不安全的问题
//但是线程锁会降低代码的执行效率
public class Mgr04 {
    public static Mgr04 INSTANCE;

    private Mgr04() {
    }

    ;

    public static synchronized Mgr04 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Mgr04();
        }
        return INSTANCE;
    }

    //测试线程是否安全
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() ->
                    System.out.println(Mgr04.getInstance().hashCode())).start();
        }
    }
}
