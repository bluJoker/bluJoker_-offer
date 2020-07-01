import java.util.ArrayList;
import java.util.Collections;

public class Permutation {
//    public ArrayList<String> permutation(String str) {
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
//        permutation(res, str, 0);
////        Collections.sort(res);
//        return res;
//    }
//
//    private void permutation(ArrayList<String> list, String str, int begin) {
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
//                permutation(list, str, begin + 1);
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
     *
     * Date:2018-07-26
     *
     * 功能：字符串的全排列
     *
     * 基本思想：每次从字符数组中选取一个元素(从第一个开始到最后一个结束)，作为结果的第一元素，剩下的元素做全排列，
     * 很明显这是一个递归的过程，递归结束标志为所选取的元素为字符数组的最后一个元素
     * */

    public static void finishFullPermutation(char[] array) {
        permutation(array, 0, array.length);
    }

    public static void permutation(char[] array, int start, int end) {
        if(end < 0) { //字符数组中没有元素直接返回
            return;
        }
        //只剩最后一个字符时为出口
        if(start == end) {
            System.out.println(array);
        }else {
            for(int i = start; i < end; i++) {
                swap(array, i, start); //更换前缀
                permutation(array, start + 1, end); //递归将剩余元素全排列
                swap(array, start, i);  //将前缀换回，以便进行上一个前缀的全排列
            }
        }
    }

    public static void swap(char[] array, int i, int j) { //用来交换前缀
        char tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void main(String[] args) {
        char[] array = {'a','b','c'};
        finishFullPermutation(array);
    }
    // https://blog.csdn.net/Norte_L/article/details/81229226


//    public static void main(String[] args) {
//        Permutation per = new Permutation();
//        String s = "abc";
//        System.out.println(per.permutation(s));
//    }
}
