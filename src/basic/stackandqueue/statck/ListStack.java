package basic.stackandqueue.statck;

import java.util.Iterator;

/**
 * 数组实现栈
 * @author wangjianhua
 * @date 2021-04-23 17:10
 */
public class ListStack<Item> implements MyStack<Item> {

    /**
     *
     */
    private Node top = null;

    /**
     *
     */
    private int N = 0;

    /**
     * Node类
     */
    private class Node{
        Item item;
        Node next;
    }

    /**
     * 压栈
     * @param item 元素
     * @return 栈
     */
    @Override
    public MyStack<Item> push(Item item) {
        Node newTop = new Node();
        newTop.item = item;
        newTop.next=top;
        top = newTop;
        N++;
        return this;
    }

    /**
     * 出栈
     * @return item
     * @throws Exception 异常
     */
    @Override
    public Item pop() throws Exception {
        if(isEmpty()){
           throw new Exception("stack is Empty");
        }
        Item item = top.item;

        top = top.next;
        N--;
        return item;
    }

    @Override
    public boolean isEmpty() {
        return N==0;
    }

    @Override
    public int size() {
        return N;
    }

    /**
     * 迭代器实现
     * @return 迭代器
     */
    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            //定义指针
            private Node cur = top;
            @Override
            public boolean hasNext() {
                return cur!=null;
            }

            @Override
            public Item next() {
                Item item = cur.item;
                cur = cur.next;
                return item;
            }
        };
    }
}
