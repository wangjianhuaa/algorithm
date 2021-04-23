package basic.stackandqueue.queue;

/**
 * 自定义队列接口
 * @author wangjianhua
 * @date 2021-04-23 17:25
 */
public interface MyQueue<Item> extends Iterable<Item> {

    /**
     * 数量
     * @return 数量
     */
    int size();

    /**
     * 队列是否为空
     * @return boolean
     */
    boolean isEmpty();

    /**
     * 队列增加元素
     * @param item 元素
     * @return 新队列
     */
    MyQueue<Item> add(Item item);

    /**
     * 队列取出元素
     * @return 取出的元素
     * @throws Exception 异常
     */
    Item remove() throws Exception;
}
