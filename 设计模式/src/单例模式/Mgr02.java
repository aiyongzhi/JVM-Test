package 单例模式;

//和写法一本质上是一样的
public class Mgr02 {
    private static Mgr02 INSTANCE;

    static {
        INSTANCE = new Mgr02();
    }

    private Mgr02() {
    }

    ;

    public static Mgr02 getInstance() {
        return INSTANCE;
    }
}
