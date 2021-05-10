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
        int cnt = 0;
        Node cur = first;
        while (cur!=null){
            cnt++;
            cur = cur.next;
        }
        return cnt;
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
        Node cur = first;
        //如果在链表中找到节点的键等于key就更新节点的值为value
        while(cur!=null){
            if(cur.key.equals(key)){
                cur.value = value;
                return;
            }
            cur=cur.next;
        }
        //否则使用头插法 插入键值对
        first = new Node(key,value,first);
    }

    @Override
    public void delete(Key key) {
        if(first==null){
           return;
        }
        if(first.key.equals(key)){
            first=first.next;
        }
        Node pre = first,cur = first.next;
        while (cur!=null){
            if(cur.key.equals(key)){
                pre.next = cur.next;
                return;
            }
            pre=pre.next;
            cur=cur.next;
        }
    }
}
