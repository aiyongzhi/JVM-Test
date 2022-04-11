public class InnerClassDemo {
    private int id;
    private String name;

    public void show() {
        System.out.println("show");
        new InnerClass().show();
    }

    public void test() {
        InnerClass innerClass = new InnerClass();
        System.out.println(innerClass.age);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public class InnerClass {
        private int age;

        public void testDemo() {
            InnerClassDemo.this.show();
        }

        public void show() {
            System.out.println("id=" + id);
            System.out.println("name=" + name);
        }
    }
}
//一个类中可以包含多个class文件，但只能有一个public class文件
//我们把一个类定义在另一个类的内部称为内部类
//内部类的实例化需要借助外部类的辅助
//我们在内部类的内部可以轻松的访问外部类的私有属性
//但是我们在外部类中不能直接访问内部类的私有属性
//但我们可以通过
