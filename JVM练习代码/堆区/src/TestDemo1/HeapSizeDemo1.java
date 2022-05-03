package TestDemo1;
//可以通过设置
//-XX:+PrintGCDetails 打印出内存分布的信息
public class HeapSizeDemo1 {
    public static void main(String[] args) {
        System.out.println("Start");
        //虚拟机栈中堆的内存总量
        long initialMemory=Runtime.getRuntime().totalMemory()/1024/1024;
        //虚拟机中堆的最大内存容量
        long maxMemory=Runtime.getRuntime().maxMemory()/1024/1024;
        System.out.println("堆空间的起始内存大小: "+initialMemory+"M");
        System.out.println("堆空间的最大内存容量: "+maxMemory+"M");
        System.out.println("END!");
    }
}
