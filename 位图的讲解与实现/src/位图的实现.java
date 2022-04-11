public class 位图的实现 {
    //位图是一个集合，提供三个方法，加入一个整数到这个集合中
    //从这个集合中删除一个元素
    //查找某个数字是否在这个集合中


    //和HashSet相比位图的好处：可以极大的压缩空间
    //缺点：只能表示整数，而HashSet可以表示任何类型
    public static class BitMap {
        private long[] bits;//用长整型数组来表示某一个数有没有出现

        //例如bits[0]:表示0--63有没有出现
        public BitMap(int max) {
            bits = new long[(max >> 6) + 1];
        }

        public void add(int num) {
            //用num/64算出第几个长整型表示num
            //把这个长整型的第num%63位标记成1
            bits[num >> 6] |= (1L << (num & 63));
        }

        public void delete(int num) {
            bits[num >> 6] &= (~(1L << (num & 63)));
        }

        public boolean contains(int num) {
            return ((bits[num >> 6]) & (1L << (num & 63))) != 0;
        }
    }

    public static void main(String[] args) {
        BitMap bitMap = new BitMap(1023);
        bitMap.add(5);
        bitMap.add(7);
        bitMap.add(20);
        System.out.println(bitMap.contains(7));
        bitMap.delete(7);
        System.out.println(bitMap.contains(7));
        System.out.println(bitMap.contains(20));
    }
}
