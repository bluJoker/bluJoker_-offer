import java.util.ArrayList;

public class PermutationSolution {
//    public ArrayList<String> permutation1(String str) {
//        ArrayList<String> res = new ArrayList<>();
//        if (str == null) {
//            return res;
//        }
//
////        int n = str.length();
////        for (int i = 0; i < n; i++) {
////            for (int j = i; j < n; j++) {
////                StringBuffer strI = new StringBuffer(str);
////                char tmp = strI.charAt(i);
////                strI.setCharAt(i, strI.charAt(j));
////                strI.setCharAt(j, tmp);
////                res.add(strI.toString());
////            }
////            str =
////        }
//
//        permutation1(res, str, 0);
////        Collections.sort(res);
//        return res;
//    }
//
//    private void permutation1(ArrayList<String> list, String str, int begin) {
//        int n = str.length();
//
//        if (begin == n - 1) {
//            list.add(str.toString());
//        } else {
//            for (int i = begin; i < n; i++) {
//                StringBuffer strI = new StringBuffer(str);
//                char tmp = strI.charAt(i);
//                strI.setCharAt(i, strI.charAt(begin));
//                strI.setCharAt(begin, tmp);
//                str = strI.toString();
//
//                permutation1(list, str, begin + 1);
//
//                strI = new StringBuffer(str);
//                tmp = strI.charAt(i);
//                strI.setCharAt(i, strI.charAt(begin));
//                strI.setCharAt(begin, tmp);
//                str = strI.toString();
//
//            }
//        }
//    }


    /**
     * @author Norte
     * <p>
     * Date:2018-07-26
     * <p>
     * 功能：字符串的全排列
     * <p>
     * 基本思想：每次从字符数组中选取一个元素(从第一个开始到最后一个结束)，作为结果的第一元素，剩下的元素做全排列，
     * 很明显这是一个递归的过程，递归结束标志为所选取的元素为字符数组的最后一个元素
     */
    // https://blog.csdn.net/Norte_L/article/details/81229226
    public static void finishFullPermutation(char[] array) {
        permutation1(array, 0, array.length);
    }

    public static void permutation1(char[] array, int start, int end) {
        if (end < 0) { //字符数组中没有元素直接返回
            return;
        }
        //只剩最后一个字符时为出口
        if (start == end) {
            System.out.println(array);
        } else {
            for (int i = start; i < end; i++) {
                swap(array, i, start); //更换前缀
                permutation1(array, start + 1, end); //递归将剩余元素全排列
                swap(array, start, i);  //将前缀换回，以便进行上一个前缀的全排列
            }
        }
    }

    public static void swap(char[] array, int i, int j) { //用来交换前缀
        char tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }


    public void permutation(String str) {
        permutation(str, "");
    }

    public void permutation(String str, String prefix) {
        if (str.length() == 0) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < str.length(); i++) {
                String rem = str.substring(0, i) + str.substring(i + 1);
                permutation(rem, prefix + str.charAt(i));
            }
        }
    }

    // 金典方法1：p298
    ArrayList<String> getPerms1(String str) {
        if (str == null) {
            return null;
        }
        ArrayList<String> permutations = new ArrayList<>();
        if (str.length() == 0) { // 基线条件
            permutations.add("");
            return permutations;
        }

        char first = str.charAt(0); // 获取第一个字符
        String remainder = str.substring(1); // 移除第一个字符
        ArrayList<String> words = getPerms1(remainder);
        for (String word : words) {
            // Tips1：j <= word.length(); 尾元素之后插入也是排列之一
            for (int j = 0; j <= word.length(); j++) {
                String s = insertCharAt(word, first, j);
                permutations.add(s);
            }
        }
        return permutations;
    }

    /* 在word的i位置插入字符c */
    String insertCharAt(String word, char c, int i) {
        String start = word.substring(0, i);
        String end = word.substring(i);
        return start + c + end;
    }


    // 金典方法2：p299
    ArrayList<String> getPerms2(String str) {
        if (str == null) {
            return null;
        }
        ArrayList<String> permutations = new ArrayList<>();
        if (str.length() == 0) { // 基线条件
            permutations.add("");
            return permutations;
        }

        char first = str.charAt(0); // 获取第一个字符
        String remainder = str.substring(1); // 移除第一个字符
        ArrayList<String> words = getPerms2(remainder);
        for (String word : words) {
            // Tips1：j <= word.length(); 尾元素之后插入也是排列之一
            for (int j = 0; j <= word.length(); j++) {
                String s = insertCharAt(word, first, j);
                permutations.add(s);
            }
        }
        return permutations;
    }


    public static void main(String[] args) {
        char[] array = {'a', 'b', 'c'};
        // 解法1
//        finishFullPermutation(array);
        PermutationSolution permutationSolution = new PermutationSolution();
//        permutationSolution.permutation("abc");

        System.out.println(permutationSolution.getPerms1("abc"));
    }


//    public static void main(String[] args) {
//        PermutationSolution per = new PermutationSolution();
//        String s = "abc";
//        System.out.println(per.permutation1(s));
//    }
}
