public class RegularExp {

    public static boolean match1(char[] str, char[] pattern) {
        int i = 0, j = 0;
        while (i < str.length && j < pattern.length) {

            if (pattern[j] == '.') {
                i++;
                j++;
            } else if (j + 1 < pattern.length && pattern[j + 1] != '*' && pattern[j] == str[i]) {
                i++;
                j++;
            } else if (j + 1 < pattern.length && pattern[j + 1] == '*' && pattern[j] == str[i]) {
                i++;
            } else if (j + 1 < pattern.length && pattern[j + 1] == '*' && pattern[j] != str[i]) {
                j += 2;
            } else if (i == str.length - 1 && j == pattern.length - 1) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public static boolean match2(char[] str, char[] pattern) {
        if (str == null || pattern == null) {
            return false;
        }
        return matchRecu(str, 0, pattern, 0);
    }

    private static boolean matchRecu(char[] str, int sIndex, char[] pattern, int pIndex) {
        if (sIndex == str.length && pIndex == pattern.length) {
            return true;
        }

        // pattern先到尾，匹配失败
        if (sIndex != str.length && pIndex == pattern.length) {
            return false;
        }

        // 模式第2个是*，且字符串第1个跟模式第1个匹配,分3种匹配模式；如不匹配，模式后移2位
        if ((pIndex + 1) < pattern.length && pattern[pIndex + 1] == '*') {
            if (sIndex != str.length && (pattern[pIndex] == str[sIndex] || pattern[pIndex] == '.')) {
                return matchRecu(str, sIndex + 1, pattern, pIndex) ||
                        matchRecu(str, sIndex, pattern, pIndex + 2);
            } else {
                return matchRecu(str, sIndex, pattern, pIndex + 2);
            }
        }

        if (sIndex != str.length && (pattern[pIndex] == str[sIndex] || pattern[pIndex] == '.')) {
            return matchRecu(str, sIndex + 1, pattern, pIndex + 1);
        }
        return false;
    }

    public static void main(String[] args) {
        char[] str1 = {'a', 'a', 'a'};
        char[] pattern1 = {'a', '.', 'a'};

        char[] str2 = {'a', 'a', 'a'};
        char[] pattern2 = {'a', 'b', '*', 'a', 'c', '*', 'a'};

        char[] str3 = {'a', 'a', 'a'};
        char[] pattern3 = {'a', 'b', '*', 'a'};

        char[] str4 = {'a', 'a', 'a'};
        char[] pattern4 = {'.', '*'};

        char[] str5 = {'a', 'a', 'a'};
        char[] pattern5 = {'a', '*', 'a'};

        System.out.println(RegularExp.match2(str1, pattern1));
        System.out.println(RegularExp.match2(str2, pattern2));
        System.out.println(RegularExp.match2(str3, pattern3));
        System.out.println(RegularExp.match2(str4, pattern4));
        System.out.println(RegularExp.match2(str5, pattern5));
    }
}
