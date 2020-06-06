public class ReplaceSpaceDemo {

    /**
     * 替换空格：
     * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
     * <p>
     * 需求：是否需要原址，如果没有该条件，则创建另一StringBuffer对象，复制即可
     * <p>
     * 解法：原址+时间复杂度为O(n)条件下，则需要先扩容，然后从后往前复制。所有字符都只复制（移动）一次，因此时间复杂度为O(n)
     */
    public String replaceSpace(StringBuffer str) {

        if (str == null) {
            return null;
        }

//        int oldLength = str.length();
//        int spaceCount = 0;
//
//        for (int i = 0; i < str.length(); i++) {
//            if (str.charAt(i) == ' '){
//                spaceCount++;
//            }
//        }
//
//        int newLength = oldLength + 2*spaceCount;
//        str.setLength(newLength);
//
//
//        System.out.println("newLength = " + newLength);
//
//        int p = oldLength-1;
//        int q = newLength-1;
//        while (p != q){
//
//            if (str.charAt(p) != ' '){
//                // 错误写法：
//                // You cannot set a new value to a position with text.chatAt(i), with this you can only read values.
//                // str.charAt(q--) = str.charAt(p);
//                str.setCharAt(q, str.charAt(p));
//            }else {
//
//                // q+1是因为replace为左开右闭区间
//                str.replace(q-2, q+1, "%20");
//                q = q-2;
//            }
//
//            p--;
//            q--;
//        }
//
//        return str.toString();


        // 有意思的写法：
        // i不会跳跃增加，但str会扩容!
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == ' ') {
                System.out.println("before replace = " + System.identityHashCode(str));

                // *****
                str.replace(i, i + 1, "%20");
                System.out.println("after replace = " + System.identityHashCode(str));
            }
        }
        String newstr = str.toString();
        return newstr;
    }

    public static void main(String[] args) {

        StringBuffer str = new StringBuffer();
        str.append("We");
        str.append(" ");
        str.append("Are");
        str.append(" ");
        str.append("Happy.");

        ReplaceSpaceDemo demo = new ReplaceSpaceDemo();
        demo.replaceSpace(str);

        System.out.println("str = " + str);
    }
}
