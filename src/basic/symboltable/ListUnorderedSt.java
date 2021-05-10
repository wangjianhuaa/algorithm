package basic.symboltable;

/**
 * 链表实现无序符号表
 * @author wangjianhua
 * @date 2021-05-08 16:06
 */
public class ListUnorderedSt<Key,Value> implements UnorderedST<Key,Value> {

    private Node first;

    private class Node{
        Key key;
        Value value;
        Node next;

        public Node(Key key,Value value,Node node){
            this.key=key;
            this.value=value;
            this.next = node;
        }
    }
    @Override
    public int size() {
        return 0;
    }

    @Override
    public Value get(Key key) {
        Node cur = first;
        while (cur!=null){
            if(cur.key.equals(key))
                return cur.value;
                cur=cur.next;

        }
        return null;
    }

    @Override
    public void put(Key key, Value value) {

    }

    @Override
    public void delete(Key key) {
        if(first==null){
           return;
        }
        if(first.key.equals(key)){
            first=first.next;
        }
    }
}
