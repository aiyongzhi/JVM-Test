public class noNameInnerClassDemo {
    public static void main(String[] args) {
        //一万行代码
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        //一万行代码
    }
}
