import java.util.Arrays;

public class 数组中有两个元素出现了奇数次其它元素出现了偶数次 {
    //数组中有一个元素出现了奇数次，其它元素出现了偶数次
    //返回这两个出现奇数次的元素
    public static int[] getNumber(int[] arr) {
        int eor = 0;
        //1:先把所有数异或起来，得到eor=a^b
        for (int num : arr) {
            eor ^= num;
        }
        //2:找到划分数组的依据，将数组划分成含义a的部分和含有b的部分
        //拿出eor二进制序列最右侧的1
        int mostRightOne = eor & (~eor + 1);//因为有两种出现奇数次的数字，所以eor!=0
        int[] ret = new int[2];
        for (int num : arr) {
            if ((num & mostRightOne) != 0) {
                ret[0] ^= num;
            } else {
                ret[1] ^= num;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 1, 1, 2, 3, 6, 6, 3, 3};
        System.out.println(Arrays.toString(getNumber(arr)));
    }
}
