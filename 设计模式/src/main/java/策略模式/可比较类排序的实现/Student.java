package 策略模式.可比较类排序的实现;

import java.util.Comparator;

public class Student {
    public int age;
    public String name;
    public double score;

    public Student(int age, String name, double score) {
        this.age = age;
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
