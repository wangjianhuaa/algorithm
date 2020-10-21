package origintest;

import java.math.BigInteger;

public class Test001 {
    /*
    计算9999的阶乘的二进制一共有多少位
     */
    public static void main(String[] args) {

        BigInteger result = BigInteger.valueOf(1);
        for (int i = 1; i <=9999 ; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        //System.out.println(result);
        System.out.println(result.bitLength());



    }

}
