import java.util.Random;
import java.util.stream.IntStream;

public class 苹果摆盘子的方法数 {
    //假设右m个苹果，每个苹果均一样
    //假设右n个盘子，每个盘子均一样
    //我们认为1,1,3和1,3,1和3,1,1是同一种摆放方式
    //也就是说我们只在乎苹果的分布情况


    //递归含义：我现在有m个苹果要摆到n个盘子里返回方法数
    public static int process1(int apples, int plates) {
        //base case
        if (plates == 0) {
            return 0;
        }
        //plates>0
        if (apples == 0) {
            return 1;//有一种所有盘子上均不放苹果
        }
        //plates>0  apples>0
        //如果plates>apples:要把多余的盘子全敲碎
        if (plates > apples) {
            return process1(apples, apples);
        }
        //apples>=plates
        int res = 0;
        for (int use = plates; use > 0; use--) {
            res += process1(apples - use, use);
        }//因为在使用不同个数盘子的情况下，分布情况一定不同，所以不需要考虑重复
        return res;
    }

    public static int process2(int apples, int plates) {
        //base case
        if (plates == 0) {
            return 0;
        }
        //plates>0
        if (apples == 0) {
            return 1;//有一种所有盘子上均不放苹果
        }
        //plates>0  apples>0
        //如果plates>apples:要把多余的盘子全敲碎
        if (plates > apples) {
            return process1(apples, apples);
        }
        //apples>=plates
        //1) 所有盘子全都使用
        int a = process2(apples - plates, plates);
        //2)所有盘子不都使用
        int b = process2(apples, plates - 1);
        return a + b;
    }

    //改成动态规划
    //两个可变参数：是二维dp表
    public static int dpWays(int apples, int plates) {
        if (apples < 0 || plates < 0) {
            return 0;
        }
        int[][] dp = new int[apples + 1][plates + 1];
        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (j > i) {
                    dp[i][j] = dp[i][i];
                } else {
                    int a = dp[i - j][j];
                    int b = dp[i][j - 1];
                    dp[i][j] = a + b;
                }
            }
        }
        return dp[apples][plates];
    }

    public static void main(String[] args) {
        //写一个对数器
        int apples = 20;
        int plates = 10;
        System.out.println(process1(apples, plates));
        System.out.println(process2(apples, plates));
        System.out.println(dpWays(apples, plates));
/*        int testTime=5000;
        int maxApples=20;
        int maxPlates=20;
        Random random=new Random();
        while(testTime--!=0){
            int apples=random.nextInt(maxApples)+1;
            int plates=random.nextInt(maxPlates)+1;
            int result1=process1(apples,plates);
            int result2=process2(apples,plates);
            if(result1!=result2){
                System.out.println("apples=="+apples);
                System.out.println("plates=="+plates);
                System.out.println("Fuck!Fail");
                break;
            }
        }*/
    }
}
