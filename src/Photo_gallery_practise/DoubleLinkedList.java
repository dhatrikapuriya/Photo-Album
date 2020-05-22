package Photo_gallery_practise;

/*
@author : Nancy Radadia
@date : / /2019
@Description :
 */
public class DoubleLinkedList {

    Node head;
    static class Node {
        int data;
        Node prev;
        Node next;
        Node(int d) {
            data = d;
        }
        Node() {
        }
    }

    void insertAfter(Node prev_Node, int new_data) {
        if (prev_Node == null) {
            System.out.println("The given previous node cannot be NULL ");
            return;
        }
        Node new_node = new Node(new_data);
        new_node.next = prev_Node.next;
        prev_Node.next = new_node;
        new_node.prev = prev_Node;
        if (new_node.next != null) {
            new_node.next.prev = new_node;
        }
    }

    public int getNth(int index) {
        Node current = head;
        int count = 0;
        while (current != null) {
            if (count == index) {
                return current.data;
            }
            count++;
            current = current.next;
        }
        assert (false);
        return 0;
    }

    int findSize(Node node) {
        int res = 0;
        while (node != null) {
            res++;
            node = node.next;
        }
        return res;
    }

    void append(int new_data) {
        Node new_node = new Node(new_data);
        Node last = head;
        new_node.next = null;
        if (head == null) {
            new_node.prev = null;
            head = new_node;
            return;
        }
        while (last.next != null) {
            last = last.next;
        }
        last.next = new_node;
        new_node.prev = last;
    }

    Node nextnode(Node node) {
        return node.next;
    }

    static void deleteNodeAtGivenPos(Node head, int n) {
        if (head == null || n <= 0) {
            return;
        }
        Node current = head;
        int i;
        for (i = 1; current != null && i < n; i++) {
            current = current.next;
        }
        if (current == null) {
            return;
        }
        deleteNode(head, current);
    }

    static Node deleteNode(Node head, Node del) {

        if (head == null || del == null) {
            return null;
        }
        if (head == del) {
            head = del.next;
        }
        if (del.next != null) {
            del.next.prev = del.prev;
        }
        if (del.prev != null) {
            del.prev.next = del.next;
        }
        del = null;
        return head;
    }

    void deleteNode1(Node head_ref, Node del) {
        if (head == null || del == null) {
            return;
        }
        if (head == del) {
            head = del.next;
        }
        if (del.next != null) {
            del.next.prev = del.prev;
        }
        if (del.prev != null) {
            del.prev.next = del.next;
        }
        return;
    }

    static Node getNode(int data) {
        Node newNode = new Node();
        newNode.data = data;
        newNode.next = null;
        return newNode;
    }

    static void insertAfterNthNode(Node head, int n, int x) {

        if (head == null) {
            return;
        }
        Node newNode = getNode(x);
        Node slow_ptr = head;
        Node fast_ptr = head;
        for (int i = 1; i <= n - 1; i++) {
            fast_ptr = fast_ptr.next;
        }
        while (fast_ptr.next != null) {

            slow_ptr = slow_ptr.next;
            fast_ptr = fast_ptr.next;
        }
        newNode.next = slow_ptr.next;
        slow_ptr.next = newNode;
    }

    static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
    }

    static int largestInDLL(Node head_ref) 
    { 
        Node max, temp; 
  
        temp = max = head_ref; 
        while (temp != null) { 
            if (temp.data > max.data) 
                max = temp; 
  
            temp = temp.next; 
        } 
        return max.data; 
    } 
    
}

/*
Program Output :
 */
