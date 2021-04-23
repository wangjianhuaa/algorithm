package basic.stackandqueue.queue;

import java.util.Iterator;

/**
 * 数组实现栈
 * @author wangjianhua
 * @date 2021-04-23 17:30
 */
public class ListQueue<Item> implements MyQueue<Item> {

    private Node first;

    private Node last;

    int N = 0;

    private class Node{
        Item item;
        Node next;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public boolean isEmpty() {
        return N==0;
    }

    /**
     * 队列增加
     * @param item 元素
     * @return 新队列
     */
    @Override
    public MyQueue<Item> add(Item item) {
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = null;

        if(isEmpty()){
            last=newNode;
            first=newNode;
        }
        else {
            last.next=newNode;
            last=newNode;
        }
        N++;
        return this;
    }

    /**
     * 队列移除元素
     * @return 移除的元素
     * @throws Exception 异常
     */
    @Override
    public Item remove() throws Exception {
        if(isEmpty()){
            throw new Exception("queue is Empty");
        }
        Node node = first;
        first = first.next;
        N--;
        if(isEmpty()){
            last=null;
        }
        return node.item;
    }

    /**
     * 迭代器实现
     * @return 迭代器
     */
    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {

            Node cur = first;

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
