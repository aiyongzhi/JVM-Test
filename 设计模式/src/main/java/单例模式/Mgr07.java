package 单例模式;

/*
 * 通过静态内部类的形式
 * JVM保证线程安全
 * 不需要我们自己处理
 * */
public class Mgr07 {
    private Mgr07() {
    }

    ;

    private static class Mgr07Holder {
        private static final Mgr07 INSTANCE = new Mgr07();
    }

    public static Mgr07 getInstance() {
        return Mgr07Holder.INSTANCE;
    }
}
