public class TestDemo1 {
    public static void main(String[] args) {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);

        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println(extClassLoader);
        ClassLoader bootStrapClassLoader = extClassLoader.getParent();
        System.out.println(bootStrapClassLoader);

        //用户自定义类的类加载器
        System.out.println(TestDemo1.class.getClassLoader());

        //工具类的类加载器
        ClassLoader classLoader = String.class.getClassLoader();
        System.out.println(classLoader);
    }
}
