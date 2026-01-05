class Solution {
    public long maxMatrixSum(int[][] matrix) {

        long answer = 0;
        long maxMinus = Integer.MIN_VALUE;
        int minusCnt = 0;
        int minNumber = Integer.MAX_VALUE;
        int len = matrix.length;

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (matrix[i][j] < 0) {
                    minusCnt++;
                    maxMinus = Math.max(maxMinus, matrix[i][j]);
                }

                answer += Math.abs(matrix[i][j]);
            }
        }

        if (minusCnt % 2 == 0) {

            return answer;
        } else {
            long tmpMin = maxMinus * -1;

            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    if (Math.abs(matrix[i][j]) < maxMinus * -1) {
                        tmpMin = Math.min(tmpMin, Math.abs(matrix[i][j]));
                    }
                }
            }

            return answer - tmpMin * 2;
        }
    }
}