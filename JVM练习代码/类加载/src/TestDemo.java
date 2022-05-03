public class TestDemo{
    public static void main(String[] args) {
        Runnable r=new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+": 开始了");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Student student = new Student();
                System.out.println(Thread.currentThread().getName()+": 结束了");
            }
        };
        Thread thread1=new Thread(r,"线程1");
        Thread thread2=new Thread(r,"线程2");
        thread1.start();
        thread2.start();
    }
}
class Student{
    static {
        if(true){
            System.out.println(Thread.currentThread().getName()+": 初始化了学生对象");
        }
    }
}

