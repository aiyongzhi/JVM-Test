import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class comparator {
    public static class Student {
        private String name;
        private int id;
        private int age;

        public Student(String a, int b, int c) {
            name = a;
            id = b;
            age = c;
        }
    }

    //比较器的规则：
    //1；返回负数表明第一个参数排前面
    //2；返回正数表面第二个参数排前面
    //3:返回0则无所谓
    public static class studentComparator implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            return o1.id - o2.id;
        }
    }

    public static void printStudents(Student[] students) {
        for (int i = 0; i < students.length; i++) {
            System.out.println(students[i].name + " ," + students[i].id + " ," + students
                    [i].age);
        }
    }

    public static void main(String[] args) {
        Student s1 = new Student("zhangSan", 3, 23);
        Student s2 = new Student("liSi", 1, 19);
        Student s3 = new Student("lili", 2, 32);
        Student[] students = {s1, s2, s3};
        printStudents(students);
        Arrays.sort(students);
        printStudents(students);
    }
}
