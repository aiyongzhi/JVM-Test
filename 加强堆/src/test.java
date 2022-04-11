import javax.naming.ldap.PagedResultsControl;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class test {
    public static class Student {
        private String name;
        private int age;
        private int height;

        public Student(String name, int age, int height) {
            this.name = name;
            this.age = age;
            this.height = height;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", height=" + height +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || this.getClass() != o.getClass()) return false;
            Student student = (Student) o;
            return age == student.age && height == student.height && Objects.equals(name, student.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age, height);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> ans1 = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        ans1.add(list1);
        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        List<List<Integer>> ans2 = new ArrayList<>();
        ans2.add(list2);
        System.out.println(list1.equals(list2));
    }
}
