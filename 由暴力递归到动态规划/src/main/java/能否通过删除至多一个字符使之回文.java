public class 能否通过删除至多一个字符使之回文 {
    public static boolean getPalindrome(String str) {
        if (str == null || str.length() == 0) {//判断输入的字符串不为空
            return true;
        }
        char[] chs = str.toCharArray();//把str变成一个字符数组
        int i = 0;
        int j = chs.length - 1;
        while (i < j && chs[i] == chs[j]) {
            i++;
            j--;
        }
        //判断逻辑如果i==j则已经是回文不需要删除
        //如果i!=j则尝试删除左边或右边
        return i == j || isPalindrome(chs, i + 1, j) || isPalindrome(chs, i, j - 1);
    }

    //一个函数判断一个字符串是否是回文
    public static boolean isPalindrome(char[] str, int L, int R) {
        while (L < R) {
            if (str[L] != str[R]) {
                return false;
            }
            L++;
            R--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(getPalindrome("abca"));
    }
}
