public class Find2Array {

    public static boolean find(int[][] array, int target){

        if (array == null){
            return false;
        }

        // 二维数组行数：array.length
        // 二维数组列数：array[0].length
        int rows = 0;
        int columns = array[0].length - 1;

        while (rows < array.length && columns >= 0){
            if (array[rows][columns] == target){
                return true;
            }else if (array[rows][columns] > target){
                columns--;
            }else {
                rows++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{{1, 2, 8, 9}, {2, 4,9,12}, {4,7,10,13}, {6,8,11,15}};

        // 二维数组包含要查找的数字
        System.out.println(Find2Array.find(arr, 7));

        // 二维数组不包含要查找的数字
        System.out.println(Find2Array.find(arr, 5));

        // 输入空指针
        System.out.println(Find2Array.find(null, 5));
    }
}
