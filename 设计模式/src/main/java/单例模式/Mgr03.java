package 单例模式;

//懒汉式：在真正要用到的时候再创建对象
//但这个方法会有线程不安全的问题，多个线程创建多个对象，违背了单例模式的要求
public class Mgr03 {
    public static Mgr03 INSTANCE;

    private Mgr03() {
    }

    ;

    public static Mgr03 getInstance() {
        if (INSTANCE == null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new Mgr03();
        }
        return INSTANCE;
    }

    //测试线程不安全问题
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() ->
                    System.out.println(Mgr03.getInstance().hashCode())).start();

        }
    }
}
