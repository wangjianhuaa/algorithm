package basic.stackandqueue.statck;

/**
 * 自定义栈接口
 * @author wangjianhua
 * @date 2021-04-23 16:47
 */
public interface MyStack<Item> extends Iterable<Item> {

    /**
     * 增加元素
     * @param item 元素
     * @return 增加后的栈
     */
     MyStack<Item> push(Item item);

    /**
     * 出栈
     * @return 取出来的元素
     * @throws Exception 异常
     */
     Item pop() throws Exception;

    /**
     * 栈是否为空
     * @return boolean
     */
     boolean isEmpty();

    /**
     * 栈数量
     * @return 数量
     */
     int size();
}
