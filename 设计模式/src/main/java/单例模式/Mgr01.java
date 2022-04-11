package 单例模式;

//饿汉式的单例模式
//在类加载的时候就被加载到内存中去
//JVM保证了其内存安全，因为一个类只会加载一次
//缺点：不管是否使用都会创建对象，但是一般情况都要使用
//推荐使用的方法，难度低，易懂。
public class Mgr01 {
    private static final Mgr01 INSTANCE = new Mgr01();

    private Mgr01() {
    }

    ;

    public static Mgr01 getInstance() {
        return INSTANCE;
    }
}
