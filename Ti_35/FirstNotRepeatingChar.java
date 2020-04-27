public class FirstNotRepeatingChar {

    /**
     * 使用hash表。
     *  ASCII码：a-z(97-122); A-Z(65-90)
     * 用很小的空间消耗换来时间效率的提升
     *
     * 时间复杂度：
     *  第一次扫描时，在哈希表中更新一个字符出现的次数的时间是O(1)。如果字符串长度为n，那么第一次扫描的时间复杂度是O(n)。
     *  第二次扫描时，同样O(1)能读出一个字符出现的次数，所以时间复杂度仍然是O(n)。
     *  这样算来，总的时间复杂度是O(n)。
     * */
    public int FirstNotRepeatingChar(String str) {
        int[] hash = new int[60];

        // 遍历字符串，统计出现次数
        for (int i = 0; i < str.length(); i++) {
            hash[str.charAt(i)-'A']++;
        }

        // 再次遍历字符串，看第一个hash值为1的字符即为所求，复杂度O(1)
        for (int i = 0; i < str.length(); i++) {
            if (hash[str.charAt(i)-'A'] == 1){
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args){
        FirstNotRepeatingChar firstNotRepeatingChar = new FirstNotRepeatingChar();
        String s = "abaCbdeff";
//        String s = "abaccbff";
        int index = firstNotRepeatingChar.FirstNotRepeatingChar(s);
        System.out.println("index = " + index);
    }
}
