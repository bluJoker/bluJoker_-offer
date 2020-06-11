import java.util.ArrayList;
import java.util.Collections;

public class Permutation {
    public ArrayList<String> permutation(String str) {
        ArrayList<String> res = new ArrayList<>();
        if (str == null) {
            return res;
        }

//        int n = str.length();
//        for (int i = 0; i < n; i++) {
//            for (int j = i; j < n; j++) {
//                StringBuffer strI = new StringBuffer(str);
//                char tmp = strI.charAt(i);
//                strI.setCharAt(i, strI.charAt(j));
//                strI.setCharAt(j, tmp);
//                res.add(strI.toString());
//            }
//            str =
//        }

        permutation(res, str, 0);
//        Collections.sort(res);
        return res;
    }

    private void permutation(ArrayList<String> list, String str, int begin) {
        int n = str.length();

        if (begin == n - 1) {
            list.add(str.toString());
        } else {
            for (int i = begin; i < n; i++) {
                StringBuffer strI = new StringBuffer(str);
                char tmp = strI.charAt(i);
                strI.setCharAt(i, strI.charAt(begin));
                strI.setCharAt(begin, tmp);
                str = strI.toString();

                permutation(list, str, begin + 1);

                strI = new StringBuffer(str);
                tmp = strI.charAt(i);
                strI.setCharAt(i, strI.charAt(begin));
                strI.setCharAt(begin, tmp);
                str = strI.toString();

            }
        }
    }


    public static void main(String[] args) {
        Permutation per = new Permutation();
        String s = "abc";
        System.out.println(per.permutation(s));
    }
}
