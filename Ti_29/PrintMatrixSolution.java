import java.util.ArrayList;

public class PrintMatrixSolution {

    public ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> arrayList = new ArrayList<>();

        if (matrix == null) {
            return arrayList;
        }

        int rows = matrix.length;
        int columns = matrix[0].length;

        int start = 0;
        while (rows > 2 * start && columns > 2 * start) {
            printMatrixCircle(matrix, rows, columns, start, arrayList);
            start++;
        }
        return arrayList;
    }

    private void printMatrixCircle(int[][] matrix, int rows, int columns, int start, ArrayList<Integer> arrayList) {
//        int endX = columns - 2 * start + 1;
//        int endY = rows - 2 * start + 1;
        int endX = columns - start - 1;
        int endY = rows - start - 1;

        // 左 --> 右
        // 易出错点1: 只有一个元素时，start=endX=0，直接打印即可
        //if (start < endX) {
            for (int i = start; i <= endX; i++) {

                //易出错点2：矩阵的行列与坐标的对应关系
//                arrayList.add(matrix[i][start]);
                arrayList.add(matrix[start][i]);
            }
        //}

        // 上 --> 下
        if (start < endY) {
            for (int i = start + 1; i <= endY; i++) {
                arrayList.add(matrix[i][endX]);
            }
        }

        // 右 --> 左
        // 1 2  只有当有上到下且列数>1才有从右往左
        // 3 4
        // 反例: 1
        //      3
        if (start < endX && start < endY) {
            for (int i = endX - 1; i >= start; i--) {
                arrayList.add(matrix[endY][i]);
            }
        }

        // 下 --> 上
//        if (start < endY - 1 && start < endX - 1) {
        if (start < endY - 1 && start < endX) {
            for (int i = endY - 1; i > start; i--) {
                arrayList.add(matrix[i][start]);
            }
        }
    }


    public static void main(String[] args) {
        int[][] matrix1 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int[][] matrix2 = {{1}};

        int[][] matrix3 ={{1,2},{3,4},{5,6},{7,8},{9,10}};
        PrintMatrixSolution printMatrixSolution = new PrintMatrixSolution();
        System.out.println(printMatrixSolution.printMatrix(matrix1));
        System.out.println(printMatrixSolution.printMatrix(matrix2));
        System.out.println(printMatrixSolution.printMatrix(matrix3));

    }
}
