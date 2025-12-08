// Main class: entry point of the program
public class Main {
    public static void main(String[] args) {
        System.out.println("hello world");

        // Create a single node with value 1
        Node node = new Node(1);
        System.out.println(node.toString()); // prints "1"

        // Create a new linked list
        SinglyLinkedList list = new SinglyLinkedList();

        // Create several nodes with different values
        Node n1 = new Node(10);
        Node n2 = new Node(20);
        Node n3 = new Node(30);
        Node n4 = new Node(10);
        Node n5 = new Node(20);
        Node n6 = new Node(30);
        Node n7 = new Node(3);
        Node n8 = new Node(4);
        Node n9 = new Node(5);

        // Append nodes to the list (adds to the end)
        list.append(n1);
        list.append(n2);
        list.append(n3);
        list.append(n4);
        list.append(n5);
        list.append(n6);
        System.out.println("List after appends: " + list);

        // Prepend a node (adds to the front)
        list.prepend(n7);
        System.out.println("List after prepend: " + list);

        // Remove the last node (tail)
        list.removeTail();
        System.out.println("List after removeTail: " + list);

        // Insert node n8 after node n3
        list.insertAfter(n3, n8);
        System.out.println("List after insertAfter(n3, n8): " + list);

        // Insert node n9 before node n2
        list.insertBefore(n2, n9);
        System.out.println("List after insertBefore(n2, n9): " + list);

        // Delete node with value 4 (removes n8)
        list.delete(4);
        System.out.println("List after delete(4): " + list);

        // Sort the list using selection sort
        list.sort();
        System.out.println("List after sort: " + list);
    }
}

// Node class: represents a single element in the linked list
class Node {
    public int value;       // data stored in the node
    public Node nextNode;   // pointer to the next node

    // Default constructor: creates a node with value 0
    public Node() {
        this.value = 0;
        this.nextNode = null;
    }

    // Constructor: creates a node with a given value
    public Node(int value) {
        this.value = value;
        this.nextNode = null;
    }

    // toString: returns the value as a string
    @Override
    public String toString() {
        return "" + this.value;
    }
}

// SinglyLinkedList class: manages the linked list
class SinglyLinkedList {
    public Node Header; // first node in the list
    public Node Tail;   // last node in the list

    // Constructor: creates an empty list
    public SinglyLinkedList() {
        this.Header = null;
        this.Tail = null;
    }

    // Prepend: add a node at the beginning
    public boolean prepend(Node newNode) {
        if (newNode == null) return false;
        if (this.Header == null) {
            // If list is empty, newNode is both head and tail
            this.Header = newNode;
            this.Tail = newNode;
            return true;
        }
        // Otherwise, link newNode before the current head
        newNode.nextNode = this.Header;
        this.Header = newNode;
        return true;
    }

    // Append: add a node at the end
    public boolean append(Node n) {
        if (n == null) return false;
        if (this.Header == null) {
            // If list is empty, newNode is both head and tail
            this.Header = n;
            this.Tail = n;
        } else {
            // Link current tail to new node, then update tail
            this.Tail.nextNode = n;
            this.Tail = n;
        }
        return true;
    }

    // RemoveTail: delete the last node
    public void removeTail() {
        if (this.Header == null) return; // empty list
        if (this.Header.nextNode == null) {
            // Only one node → clear list
            this.Header = null;
            this.Tail = null;
            return;
        }
        // Traverse until the node before the tail
        Node current = this.Header;
        while (current.nextNode != null && current.nextNode.nextNode != null) {
            current = current.nextNode;
        }
        // Remove tail
        current.nextNode = null;
        this.Tail = current;
    }

    // InsertAfter: insert newNode after preNode
    public boolean insertAfter(Node preNode, Node newNode) {
        if (preNode == null || newNode == null) return false;
        newNode.nextNode = preNode.nextNode;
        preNode.nextNode = newNode;
        if (preNode == this.Tail) this.Tail = newNode; // update tail if needed
        return true;
    }

    // InsertBefore: insert newNode before targetNode
    public boolean insertBefore(Node targetNode, Node newNode) {
        if (targetNode == null || newNode == null) return false;
        if (this.Header == null) return false;
        if (targetNode == this.Header) {
            // Special case: inserting before head
            newNode.nextNode = this.Header;
            this.Header = newNode;
            return true;
        }
        // Traverse until the node before targetNode
        Node current = this.Header;
        while (current.nextNode != null && current.nextNode != targetNode) {
            current = current.nextNode;
        }
        if (current.nextNode == targetNode) {
            newNode.nextNode = targetNode;
            current.nextNode = newNode;
            return true;
        }
        return false; // target not found
    }

    // Delete: remove the first node with a given value
    public boolean delete(int value) {
        if (this.Header == null) return false;
        if (this.Header.value == value) {
            // Special case: deleting head
            this.Header = this.Header.nextNode;
            if (this.Header == null) this.Tail = null; // list became empty
            return true;
        }
        // Traverse until the node before the one to delete
        Node current = this.Header;
        while (current.nextNode != null && current.nextNode.value != value) {
            current = current.nextNode;
        }
        if (current.nextNode != null) {
            Node nodeToDelete = current.nextNode;
            current.nextNode = nodeToDelete.nextNode;
            if (nodeToDelete == this.Tail) this.Tail = current; // update tail
            return true;
        }
        return false; // value not found
    }

    // toString: print the list as "value -> value -> ... -> null"
    @Override
    public String toString() {
        if (this.Header == null) return "empty";
        StringBuilder sb = new StringBuilder();
        Node current = this.Header;
        while (current != null) {
            sb.append(current.value).append(" -> ");
            current = current.nextNode;
        }
        sb.append("null");
        return sb.toString();
    }

    // Sort: selection sort by swapping node values
    public void sort() {
        if (this.Header == null || this.Header.nextNode == null) return;
        for (Node current = this.Header; current != null; current = current.nextNode) {
            Node minNode = current;
            for (Node next = current.nextNode; next != null; next = next.nextNode) {
                if (next.value < minNode.value) minNode = next;
            }
            // Swap values between current and minNode
            if (minNode != current) {
                int temp = current.value;
                current.value = minNode.value;
                minNode.value = temp;
            }
        }
    }
}
