public class Clone {
    /*  clone 原型模式*/
    public static class Student implements Cloneable{
        private String name;
        private int id;
        public Student(String name, int id) {
            this.name = name;
            this.id = id;
        }
        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", id=" + id +
                    '}';
        }
        @Override
        protected Student clone() throws CloneNotSupportedException {
            return (Student) super.clone();
        }
        public static void main(String[] args) {
            Student student1=new Student("张三",1);
            Student student2;
            try {
                student2=student1.clone();
                System.out.println(student1);
                System.out.println(student2);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }
}
