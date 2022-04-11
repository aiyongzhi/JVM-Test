public class TestDemo {

    public static void main(String[] args) {
/*        InnerClassDemo innerClassDemo=new InnerClassDemo();
        System.out.println(innerClassDemo.getId());
        innerClassDemo.show();*/
        InnerClassDemo.InnerClass innerClass = new InnerClassDemo().new InnerClass();
        innerClass.testDemo();
    }
}
