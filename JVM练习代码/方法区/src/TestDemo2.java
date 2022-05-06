public class TestDemo2 {
    public static class Point{
        public static int x=1;
        public int y=2;
    }
    public static void main(String[] args) {
        Point point=null;
        System.out.println(point.x);
        System.out.println(Point.x);
    }
}
