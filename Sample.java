import java.util.Stack;

// Time Complexity : O(1/(1-(n/m)))
// Space Complexity : 
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach


/*1. Design a HashMap without using any built-in hash table libraries.

To be specific, your design should include these functions:

put(key, value) : Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.
get(key): Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
remove(key) : Remove the mapping for the value key if this map contains the mapping for the key.

*/

class MyHashMap {
    Node[] nodes;
    class Node{
        int key;
        int value;
        Node previous;
        Node next;
        
        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
        
    }
    
    private int indexHashing(int key){
        return Integer.hashCode(key) % nodes.length;
    }

    /** Initialize your data structure here. */
    public MyHashMap() {
        nodes = new Node[10000];
    }
    
    private Node find(Node head, int key){
        Node current = head;
        Node previous = null;
        while(current != null && current.key != key){
            previous = current;
            current = current.next;
        }
        return previous;
        
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        int i = indexHashing(key);
        //To create the head node with -1,-1 as the key, value
        if(nodes[i] == null){
            nodes[i] = new Node(-1, -1);
        }
        Node previous = find(nodes[i], key);
        //When key is not present add the node with the new key, value
        if(previous.next == null){
            previous.next = new Node(key, value);
        }
        //When key is present update the node with the new value
        else{
            previous.next.value = value;
        }
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int i = indexHashing(key);
        //To check whether the linked list is present
        if(nodes[i] == null) return -1;
        //If link list is present then findthe node with the key
        Node previous = find(nodes[i], key);
        //To check when the linkedlist is present but not able to find the node
        if(previous.next == null) return -1;
        return previous.next.value;
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int i = indexHashing(key);
        //To check whether the linked list is present
        if(nodes[i] == null) return;
        //If link list is present then findthe node with the key
        Node previous = find(nodes[i], key);
        //To check when the linkedlist is present but not able to find the node
        if(previous.next == null) return;
        previous.next = previous.next.next;
        
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */

/*
2. Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
*/

class MinStack {
    Stack<Integer> stack;
    Stack<Integer> minStack;
    Integer minimum;
    
    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
        minimum = Integer.MAX_VALUE;
        minStack.push(minimum);
    }
    
    public void push(int x) {
        minimum = Math.min(minimum,x);
        stack.push(x);
        minStack.push(minimum);
        
    }
    
    public void pop() {
        stack.pop();
        minStack.pop();
        minimum = minStack.peek();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minimum;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */