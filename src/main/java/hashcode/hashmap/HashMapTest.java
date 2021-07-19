package hashcode.hashmap;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjianhua
 * @Description hashmap简单实现
 * @date 2021/7/6/006 20:33
 **/
public class HashMapTest {
    public static void main(String[] args) {
        //初始化一组字符串
        List<String> list = new ArrayList<>();
        list.add("jikk");
        list.add("lopi");
        list.add("老王");
        list.add("老曹");
        list.add("老张");
        list.add("yhjk");
        list.add("plop");
        //定义要存放的数组
        String[] tab = new String[8];

        for (String key : list) {
            //计算索引位置
            int idx = key.hashCode() & (tab.length-1);
            System.out.println(String.format("key值=%s Idx=%d",key,idx));
            if(null==tab[idx]){
                tab[idx] = key;
                continue;
            }
            tab[idx] =  tab[idx]+ "->" + key;
        }
        //输出测试结果
        System.out.println(JSON.toJSONString(tab));

    }
    /*
     简单用散列实现一个hashmap 现在算是一个数据存放的雏形
     问题:
     1.所有的元素存放都需要获取一个索引位置  如果元素的位置不够散列 碰撞严重
     那么就失去了散列表存放的意义 未达到预期的性能
     2.在获取索引ID的计算公式中 需要数组长度是2的倍数 怎么进行初始化这个数组的大小
     3.数组越小 碰撞越大 数组越大碰撞越小 时间和空间如何取舍
     4.目前存放七个元素 已经有两个位置都存放了2个字符串 那么链表越长怎么优化
     5.随着元素的不断添加 数组长度不足扩容时 怎么把原有的元素 拆分到新的位置上去
     <==================扰动函数 初始化容量 负载因子 扩容方法 链表和红黑树转换的使用==================>
     */
}
