package leetcode.divideandconquer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjianhua
 * @Description 给定一个含有数字和运算表达式的字符串 为表达式添加括号并得到不同的结果
 * @date 2021/7/19/019 17:56
 */
public class Medium {


    public static void main(String[] args) {
        String input = "2+3*4-5";
        List<Integer> integers = diffWaysToCompute(input);
        for (Integer integer : integers) {
            System.out.println("["+integer+"]");
        }
    }

    public static List<Integer> diffWaysToCompute(String input){
        List<Integer> ways = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if(c=='+' || c=='-' || c=='*'){
                //左边
                List<Integer> left = diffWaysToCompute(input.substring(0, i));
                //右边
                List<Integer> right = diffWaysToCompute(input.substring(i+1, input.length()));
                for (int l : left) {
                    for (int r : right) {
                        switch (c){
                            case '+':
                              ways.add(l+r);
                              break;
                            case '-':
                                ways.add(l-r);
                                break;
                            case '*':
                                ways.add(l*r);
                                break;
                            default:
                                break;
                        }
                    }
                }

            }
        }
        if(ways.size()==0){
            ways.add(Integer.valueOf(input));
        }
        return ways;
    }
}
