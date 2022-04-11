package 单例模式;

/*
 * 完美单例写法
 * 枚举单例的方式
 *既可以保证线程安全
 *也可以解决同步问题
 * */
public enum Mgr08 {
    INSTANCE;

    public void f() {
        /*
         * 业务逻辑
         * */
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() ->
                    System.out.println(Mgr08.INSTANCE.hashCode())).start();
        }
    }
}
