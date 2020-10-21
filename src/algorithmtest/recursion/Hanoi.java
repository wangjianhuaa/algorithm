package algorithmtest.recursion;

import java.util.Scanner;

/**
 * 汉诺塔->递归算法
 * 1.把n-1个圆盘从A柱移动到B柱->递归
 * 2.把第n个圆盘从A柱移动到C柱
 * 3.把n-1个圆盘从B柱移动到C柱->递归
 */
public class Hanoi {
    public static int m = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入想要计算的汉诺塔圆盘数:");
        int n = sc.nextInt();
        hanoi(n,'A','B','C');
        if(n>0){
            System.out.println(n + "个圆盘所需要的移动次数为:" + m);
        }

    }
    /**
     * 移动第n个圆盘
     * @param n 第n个圆盘
     * @param a 源柱a
     * @param b 辅助柱b
     * @param c 目标柱c
     */
    public static void hanoi(int n,char a,char b,char c){
        if(n==1){
            System.out.println("第" + n + "个圆盘" + a + "--->" + c);
            return;
        }
        else  if (n <=0){
            System.out.println("???????????????????????");
            return;
        }
        //把n-1个圆盘从a移动到b
        hanoi(n-1,a,c,b);
        System.out.println("第" + n + "个圆盘:" + a + "--->" + c);//把第n号圆盘从a移动到c
        m++;
        hanoi(n-1,b,a,c);
        m++;


    }
}
