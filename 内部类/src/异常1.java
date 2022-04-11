public class 异常1 {
    public static void f() throws ArithmeticException {
        System.out.println(1 / 0);
    }

    public static void main(String[] args) throws ArithmeticException {
/*        try {
            f();
        }catch (Exception e){
            e.printStackTrace();
        }*/
        f();
    }
}
