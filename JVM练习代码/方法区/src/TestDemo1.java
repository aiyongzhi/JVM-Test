public class TestDemo1 {
    public static void main(String[] args) {
        for(int i=0;i<10000;i++){
            int finalI = i;
            new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("执行"+ finalI);
                }
            };
            try {
                Thread.sleep(1000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
