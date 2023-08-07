public class LinkedListDeque <T>{
    /* Achieve circular sentinel topology*/
    public class Node{
        public Node prev;
        public T item;
        public Node next;
        public Node(){
            prev = null;
            next = null;
            item = null;
        }
        public Node(T x){
            item = x;
            this.prev = null;
            this.next = null;
        }
    }
    private Node sentinel;
    private int size;
    public LinkedListDeque(){
        sentinel = new Node();
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public LinkedListDeque(LinkedListDeque other)
    {
        size = other.size();
        int i = 0;
        sentinel = new Node();
        Node prev = sentinel;
        Node cur = null;
        while(i < size){
            T t = (T)other.get(i);
            cur = new Node(t);
            cur.prev = prev;
            prev.next = cur;
            prev = cur;
            ++i;
        }
        cur.next = sentinel;
        sentinel.prev = cur;
    }
    public void addFirst(T item)
    {
        Node newNode = new Node(item);
        newNode.next = sentinel.next;
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        newNode.prev = sentinel;
        size++;
    }
    public void addLast(T item)
    {
        Node newNode = new Node(item);
        newNode.prev = sentinel.prev;
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        newNode.next = sentinel;
        size++;
    }
    public boolean isEmpty()
    {
        return size == 0;
    }
    public int size()
    {
        return size;
    }
    public void printDeque(){
        System.out.print(get(0));
        int i = 1;
        while(i < size){
            System.out.print(" ");
            System.out.print(get(i));
            ++i;
        }
        System.out.println();
    }
    public T removeFirst()
    {
        T ret = sentinel.next.item;
        Node first = sentinel.next.next;
        sentinel.next = first;
        first.prev = sentinel;
        size--;
        return ret;
    }
    public T removeLast()
    {
        T ret = sentinel.prev.item;
        Node last = sentinel.prev.prev;
        sentinel.prev = last;
        last.next = sentinel;
        size--;
        return ret;
    }
    public T get(int index)
    {
        int i = 0;
        Node temp = sentinel;
        while(i <= index){
            temp = temp.next;
            ++i;
        }
        return temp.item;
    }
    private Node getNext(Node cur,int count){
        if(count == 0){
            return cur;
        }
        else{
            count--;
            return getNext(cur.next,count);
        }
    }
    public T getRecursive(int index){
        Node temp = sentinel.next;
        return getNext(temp,index).item;
    }
}

