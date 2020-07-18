import java.util.ArrayList;

/**
 * 双指针从左开始右移，不能夹逼，和FindNumbersWithSumSolution区别
 *
 * */
public class FindContinuousSequenceSolution {

    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        //存放结果
        ArrayList<ArrayList<Integer> > result = new ArrayList<>();
        //两个起点，相当于动态窗口的两边，根据其窗口内的值的和来确定窗口的位置和大小
        int plow = 1,phigh = 2;
        while(phigh > plow){
            //由于是连续的，差为1的一个序列，那么求和公式是(a0+an)*n/2
            int cur = (phigh + plow) * (phigh - plow + 1) / 2;
            //相等，那么就将窗口范围的所有数添加进结果集
            if(cur == sum){
                ArrayList<Integer> list = new ArrayList<>();
                for(int i=plow;i<=phigh;i++){
                    list.add(i);
                }
                result.add(list);

                // 等于结果集添加后，左边或右边右移都行
                plow++;
                //如果当前窗口内的值之和小于sum，那么右边窗口右移一下
            }else if(cur < sum){
                phigh++;
            }else{
                //如果当前窗口内的值之和大于sum，那么左边窗口右移一下
                plow++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        FindContinuousSequenceSolution findContinuousSequenceSolution = new FindContinuousSequenceSolution();
        System.out.println(findContinuousSequenceSolution.FindContinuousSequence(15));
    }
}
