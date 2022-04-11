public class 最大路径和 {
    //对于一个n*n的正方形矩阵 每一个位置都有一个非负数的分数
    //选手经过每一个位置时，都可以获得该位置的分数
    //若该选手从改矩阵的左上角出发(每次只能向右或者向下走)，走到右下角
    //再从右下角走回右上角，(每次只能向左或者向上走)。
    //注意：选手不能重复获得同一位置的分数，请设计一个算法返回选手可以获得的最大分数


    //x1..y1是第一个人的位置
    //x2...x1+y1-x2是第二个人的位置
    public static int process(int[][] grid, int x1, int y1, int x2) {
        //base case
        if (x1 == grid.length || x1 < 0 || y1 < 0 || y1 == grid.length ||
                x2 == grid.length || x2 < 0 || x1 + y1 - x2 < 0 || x1 + y1 - x2 == grid.length) {
            return -1;
        }
        if (x1 == grid.length - 1 && y1 == grid.length - 1) {
            return grid[grid.length - 1][grid.length - 1];
        }
        //有四种情况
        //1:x1向右 x2向右
        //2:x1向右 x2向下
        //3:x1向下 x2向右
        //4:x1向下 x2向下
        int next = Integer.MIN_VALUE;
        next = Math.max(next, process(grid, x1, y1 + 1, x2));
        next = Math.max(next, process(grid, x1, y1 + 1, x2 + 1));
        next = Math.max(next, process(grid, x1 + 1, y1, x2));
        next = Math.max(next, process(grid, x1 + 1, y1, x2 + 1));

        //刷掉越界的数据 定义越界无效为-1
        if (grid[x1][y1] == -1 || grid[x2][x1 + y1 - x2] == -1 || next == -1) {
            return -1;
        }
        //在同样的位置
        if (x1 == x2) {
            return next + grid[x1][y1];
        }
        return grid[x1][y1] + grid[x2][x1 + y1 - x2] +
                next;
    }

}
