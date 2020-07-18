/**
 *
 * 判断两个字符串是否互为变形词
 *
 * */
public class IsDeformationSolution {
    public boolean isDeformation(String str1, String str2){
        if (str1 == null || str2 == null || str1.length() != str2.length()){
            return false;
        }

        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i < chars1.length; i++) {
            map[chars1[i]]++;
        }

        for (int i = 0; i < chars2.length; i++) {
            // Tips: 在length相等的基础上，如果在1上某个字符比2多了，则一定会出现少的情况
            // 在减之前已经是0则返回false
            if (map[chars2[i]]-- == 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        IsDeformationSolution isDeformationSolution = new IsDeformationSolution();
        System.out.println(isDeformationSolution.isDeformation("cdrcc", "rcdc"));
    }
}
