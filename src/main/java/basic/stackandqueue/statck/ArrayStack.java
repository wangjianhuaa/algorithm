package basic.stackandqueue.statck;

import java.util.Iterator;

/**
 * 链表实现栈
 * @author wangjianhua
 * @date 2021-04-23 16:53
 */
@SuppressWarnings("unchecked")
public class ArrayStack<Item> implements MyStack<Item> {
    /**
     *
     */
    private Item[] a = (Item[]) new Object[1];
    /**
     *
     */
    private int N  = 0;



    /**
     * 压栈
     * @param item 元素
     * @return 栈
     */
    @Override
    public MyStack<Item> push(Item item) {
        check();
        a[N++]=item;
        return this;
    }

    /**
     * 出栈
     * @return 元素
     * @throws Exception 异常
     */
    @Override
    public Item pop() throws Exception {
        if(isEmpty()){
            throw new Exception("stack is Empty");
        }
        Item item = a[--N];
        check();
        //避免对象游离
        a[N] = null;
        return item;
    }

    /**
     *
     */
    private void check(){
        if(N >=a.length){
            resize(2*a.length);
        }
        else if(N>0 && N<=a.length/4){
            resize(a.length/2);
        }
    }

    /**
     *
     */
    private void resize(int size){
        Item[] tmp = (Item[]) new Object[size];
        for (int i = 0; i <N ; i++) {
            tmp[i] = a[i];
        }
        a = tmp;
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
            private int i = N;

            @Override
            public boolean hasNext() {
                return i>0;
            }

            @Override
            public Item next() {
                return a[--i];
            }
        };
    }


}
