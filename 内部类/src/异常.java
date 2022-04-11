import java.util.InputMismatchException;
import java.util.Scanner;

public class 异常 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] program = {"c#", "java", "python"};
        System.out.println("请输入1~3之间的数字");
        int index = 0;
        try {
            index = scanner.nextInt();
            System.out.println(program[index - 1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("您输入的数据不在1~3内，请输入达标的数据");
            e.printStackTrace();
        } catch (InputMismatchException e) {
            System.out.println("您输入的数据不是整数，请输入整数！");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("欢迎提出建议！");
        }

    }
}
