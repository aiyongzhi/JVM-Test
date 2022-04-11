public class Test {
    public int id;
    public static int sid;

    public void eat() {
        System.out.println(id);
        System.out.println(sid);
    }

    public static void fly() {
        //System.out.println(id);//在静态方法中不能访问非静态属性
        System.out.println(sid);
    }

    public static void main(String[] args) {
        Test test1 = new Test();
        Test.fly();
        test1.eat();
    }
}
