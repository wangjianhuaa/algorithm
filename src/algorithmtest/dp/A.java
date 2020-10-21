package algorithmtest.dp;

import java.util.Scanner;

public class A {
    //递归方法求阶乘
    public static void main(String[] args) {
        A m = new A();
        Scanner sc = new Scanner(System.in);
        System.out.print("输入数字n计算1到它的阶乘：");
        int n = sc.nextInt();
        int c = m.getC(n);
        System.out.println("1到n的阶乘为： " + c);
    }

    public int getC(int n) {
        int c1 = 1;
        if (n > 1) {
            c1 *= getC(n - 1) * n;
        } else {
            c1 *= n;
        }
        return c1;
    }
}
