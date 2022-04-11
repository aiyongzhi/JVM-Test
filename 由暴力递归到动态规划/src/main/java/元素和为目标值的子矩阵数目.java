import java.util.HashMap;

public class 元素和为目标值的子矩阵数目 {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int res = 0;
        int row = matrix.length;
        int col = matrix[0].length;
        //key代表和为sum出现value次
        HashMap<Integer, Integer> sumSet = new HashMap<>();
        for (int start = 0; start < row; start++) {
            int[] colSum = new int[col];
            for (int end = start; end < row; end++) {
                sumSet.put(0, 1);
                int sum = 0;
                for (int k = 0; k < col; k++) {
                    colSum[k] += matrix[end][k];
                    sum += colSum[k];
                    if (sumSet.containsKey(sum - target)) {
                        res += sumSet.get(sum - target);
                    }
                    sumSet.put(sum, sumSet.getOrDefault(sum, 0) + 1);
                }
                sumSet.clear();
            }
        }
        return res;
    }
}
