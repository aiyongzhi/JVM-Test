package 蓄水池算法;

/*
 * 假设有一个流，向外不断吐出球，这些球都带上了编号从1-N
 * 有一个大小为M的袋子，只能装下M个球
 * 问题:1-N号球依次来，请设计一个算法，保证每一个球都有相等的概率最终留在袋子中
 * 算法步骤:
 * 1:前1-M号球一定入袋子,M+1-N号球每一个球都以M/i的概率入袋子，i是当前球的编号
 * 2:i号球如果选中进入袋子，袋子中的球等概率扔掉一个
 * */
public class 蓄水池算法 {
    public static void main(String[] args) {
        //测试这个算法的正确性
        //假设袋子中只有10个容量
        //流中总共统计17个球
        int testTime = 1000000;
        int[] counts = new int[18];
        for (int i = 0; i < testTime; i++) {
            int[] bag = new int[10];
            int bagI = 0;
            for (int num = 1; num <= 17; num++) {
                if (num <= 10) {
                    bag[bagI++] = num;
                } else {
                    int random = (int) (Math.random() * num) + 1;
                    if (random <= 10) {
                        bagI = (int) (Math.random() * 10);
                        bag[bagI] = num;
                    }
                }
            }
            for (int num : bag) {
                counts[num]++;
            }
        }
        for (int i = 1; i < counts.length; i++) {
            System.out.println(i + "出现的次数: " + counts[i]);
        }
    }
}
