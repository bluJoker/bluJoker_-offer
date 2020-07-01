public class LeftRotateStringSolution {

    public String LeftRotateString(String str,int n) {
        if (str == null) {
            return str;
        }

        char[] chars = str.toCharArray();
        if (chars.length < n){
            return "";
        }
        reverseSubSentence(chars, 0, n - 1);
        reverseSubSentence(chars, n, str.length() - 1);
        reverseSubSentence(chars, 0, str.length() - 1);

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

    public static void main(String[] args) {
        LeftRotateStringSolution leftRotateStringSolution = new LeftRotateStringSolution();
        String str = "  ";

        System.out.println(leftRotateStringSolution.LeftRotateString(str, 6));
    }
}
