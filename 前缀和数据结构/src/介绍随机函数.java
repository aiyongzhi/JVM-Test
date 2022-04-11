public class 介绍随机函数 {
    //随机函数Math.random:可以等概率返回[0,1)double类型的小数
    //验证
    public static void main(String[] args) {
/*      int testTime=10000000;
        int count=0;
        for(int i=0;i<testTime;i++){
            if(Math.random()<0.3){
                count++;
            }
        }
        System.out.println((double)count/testTime);*/
/*         int testTime=10000000;
         int[] count=new int[9];
         for(int i=0;i<testTime;i++){
             int random=(int)(Math.random()*9);//等概率返回[0...8]
             count[random]++;
         }
         for(int i=0;i<9;i++){
             System.out.println("数值"+i+"_"+"概率:"+((double)count[i])/testTime);
         }
         System.out.println((double)1/9);*/
        int testTime = 1000000;
        int count = 0;
        for (int i = 0; i < testTime; i++) {
            if (xToPower2() < 0.3) {
                count++;
            }
        }
        System.out.println((double) count / testTime);
        System.out.println(Math.pow(0.3, 2));
    }

    //很有意思的一个问题，请用Math.random解决一个问题
    //任给一个x(0<=x<1),原本Math.random返回[0..x)数的概率为p=x;
    //现在我要将这个概率调整为x^2
    //智力题：
    public static double xToPower2() {
        return Math.max(Math.random(), Math.random());
    }
}
