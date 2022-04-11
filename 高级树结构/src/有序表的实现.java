import java.util.TreeMap;

//有序表的定义：是一类接口：各接口在组织数据时，都能够保证数据是按key有序存储的，且增删查改的时间复杂度是
//O(logN)的一类接口称为有序表
//能实现有序表的数据结构
//红黑树，AVL树，SB树，跳表等操作
public class 有序表的实现 {
    //TreeMap:在Java中底层是用红黑树来实现的
    //相较于HashMap的优点：保证在容器中的key是有序的，有更多的功能：比如可以查找最大的key，最小的key
    //缺点：HashMap:所有操作时间复杂度都是O(1) 而TreeMap:所有操作时间复杂度都是O(logN)
    public static void main(String[] args) {
        TreeMap<Integer, String> map = new TreeMap<>();
        //增加元素
        map.put(4, "我是4");
        map.put(6, "我是6");
        map.put(3, "我是3");
        map.put(7, "我是7");
        map.put(1, "我是1");
        //通过key查找value
        System.out.println(map.get(4));
        map.remove(4);
        System.out.println(map.get(4));
        //查找最小的key
        System.out.println(map.firstKey());
        System.out.println(map.lastKey());
        //floorKey:找<=num的最大的key
        //ceiling：找>=num的最小的key
        System.out.println(map.floorKey(4));
        System.out.println(map.ceilingKey(4));
    }
}
