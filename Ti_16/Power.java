public class Power {

    public double power(double base, int exponent) {
        if (base == 0 && exponent <= 0) {
            throw new IllegalArgumentException("base and exponent invalid!");

        }
        if (base != 0 && exponent == 0) {
            return 1.0;
        }

        int absExponent = Math.abs(exponent);

        double result = powerWithUnsignedExponent(base, absExponent);
        if (exponent < 0) {
            result = 1.0 / result;
        }
        return result;
    }

    double powerWithUnsignedExponent1(double base, int exponent){

        double result = 1.0;

        for (int i = 1; i <= exponent; i++) {
            result = result * base;
        }
        return result;
    }

    // 递归，时间复杂度O(logn)
    // a^n = a^(n/2) * a^(n/2); n为偶数
    // a^n = a^(n-1/2) * a^(n-1/2) * a; n为奇数
    double powerWithUnsignedExponent(double base, int exponent){

        if (exponent == 0){
            return 1.0;
        }
        if (exponent == 1){
            return base;
        }

        // Tips：n为偶数，右移1位为n/2；n为奇数，右移1位为(n-1)/2
        double result = powerWithUnsignedExponent(base, exponent >> 1);
        result *=result;

        // 如果exponent为奇，结果最后还要乘个a
        if ((exponent & 0x1) == 1){
            result *= base;
        }
        return result;
    }


    public static void main(String[] args) {
        Power powerClazz = new Power();

        double base1 = 1.3;
        int exponent1 = 2;
        System.out.println("base1:" + powerClazz.power(base1, exponent1));

        double base2 = 1.3;
        int exponent2 = -2;
        System.out.println("base2:" + powerClazz.power(base2, exponent2));

        double base4 = 1.3;
        int exponent4 = 0;
        System.out.println("base4:" + powerClazz.power(base4, exponent4));

        System.out.println("1.0 / (1.3 * 1.3) = " + 1.0 / (1.3 * 1.3));

        double base5 = 0;
        int exponent5 = -2;
        System.out.println("base5:" + powerClazz.power(base5, exponent5));

        double base3 = 0;
        int exponent3 = 0;
        System.out.println("base3:" + powerClazz.power(base3, exponent3));

    }
}
