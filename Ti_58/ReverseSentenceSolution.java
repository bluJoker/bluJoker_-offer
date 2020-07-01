public class ReverseSentenceSolution {

    // 方法1：先反转整个字符串，再反转每个单词
    public String ReverseSentence(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }

        char[] chars = str.toCharArray();
        reverseSubSentence(chars, 0, chars.length - 1);

        int l = -1;
        int r = -1;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                // 标志新单词开始处：(1) i=0
                //               (2) 如果i的左边是空格
                l = (i == 0 || chars[i - 1] == ' ') ? i : l;
                // 标志新单词结束处：(1) i=chars.length - 1
                //               (2) 如果i的右边是空格
                r = (i == chars.length - 1 || chars[i + 1] == ' ') ? i : r;
            }
            if (l != -1 && r != -1) {
                reverseSubSentence(chars, l, r);
                l = -1;
                r = -1;
            }
        }
        return String.valueOf(chars);
    }

    private void reverseSubSentence(char[] chars, int start, int end) {
        while (start < end) {
            char tmp = chars[start];
            chars[start] = chars[end];
            chars[end] = tmp;
            start++;
            end--;
        }
    }


    // 方法2：从后遍历每个单词输出
    public String ReverseSentence2(String str) {
        if (str == null) {
            return null;
        }

        // 对于仅包含空格的字符串，原样输出
        String trim = str.trim();
        if ("".equals(trim)) {
            return str;
        }

        String[] strings = str.split(" ");
        String res = "";
        for (int i = strings.length - 1; i >= 0; i--) {
            res += strings[i] + " ";
        }

        // 去掉字符串首尾空白
        return res.trim();
    }

    public static void main(String[] args) {
        ReverseSentenceSolution reverseSentenceSolution = new ReverseSentenceSolution();
        String str = "I am a Student.";
        String str1 = "I am ";
        String str2 = "I  ";
        String str3 = " ";

        System.out.println(reverseSentenceSolution.ReverseSentence2(str));
        System.out.println(reverseSentenceSolution.ReverseSentence2(str1));
        System.out.println(reverseSentenceSolution.ReverseSentence2(str2));
        System.out.println("-" + reverseSentenceSolution.ReverseSentence2(str3) + "-");

        System.out.println(reverseSentenceSolution.ReverseSentence(str));
        System.out.println(reverseSentenceSolution.ReverseSentence(str1));
        System.out.println(reverseSentenceSolution.ReverseSentence(str2));
        System.out.println("-" + reverseSentenceSolution.ReverseSentence(str3) + "-");
    }
}
