public class 照亮所有居民区所需最小灯盏数 {
    //给你一个字符串其中’x‘字符表示这里有一堵墙，’.‘字符表示这里是居民区
    //所有的墙不能放灯，但可以点亮也可以不点亮
    //所有的居民区必须要点亮，可以在居民区放灯也可以不放灯
    //在i位置放灯,可以照亮i位置，i-1位置和i+1位置


    //这题本身就是从左到右的尝试模型，一定要自己写，主动的去尝试
    //str[0...i-1]的区域已经照亮了，现在要把str[i....]区域照亮
    //分类讨论(1)如果i位置是'x',直接跳过，去照亮str[i+1.....]
    //灯数不变
    //(2)如果i位置是'.'
    //灯数++
    //2.1:如果i+1位置是'x'，则i位置必须放灯，去照亮str[i+2....]
    //2.2:如果i+1位置是'.',小贪心，在i+1位置放灯，去照亮str[i+3....]
    public static int theMinLights(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chs = str.toCharArray();
        int i = 0;
        int lights = 0;
        while (i < chs.length) {
            if (chs[i] == 'x') {
                i++;
            } else {
                lights++;
                if (i + 1 == str.length()) {
                    break;
                }
                //i+1<str.length()
                if (chs[i + 1] == 'x') {
                    i += 2;
                } else {
                    i += 3;
                }
            }
        }
        return lights;
    }
}
