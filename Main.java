import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        D_List list = new D_List();

        D_Node n1 = new D_Node(10);
        D_Node n2 = new D_Node(20);
        D_Node n3 = new D_Node(30);
        D_Node n4 = new D_Node(40);
        D_Node n5 = new D_Node(50);
        D_Node n6 = new D_Node(60);
        D_Node n7 = new D_Node(4);
        D_Node n8 = new D_Node(5);
        D_Node n9 = new D_Node(6);
        list.Append(n1);
        list.Append(n2);
        list.Append(n3);
        list.Append(n4);
        list.Append(n5);
        list.Append(n6);
        list.Prepend(n7);
        list.InsertAfter(n3, n8);
        list.InsertBefore(n8, n9);
        list.Search(6);
        list.RemoveAfter(n8);
        list.RemoveBefore(n3);
        list.Sort();

        System.out.println(Arrays.toString(list.Traverse()));
    }
}

class D_Node {
    private int Payload;
    private D_Node PreNode;
    private D_Node NextNode;

    public int getPayload() { return Payload; }
    public void setPayload(int payload) { Payload = payload; }

    public D_Node getPreNode() { return PreNode; }
    public void setPreNode(D_Node preNode) { PreNode = preNode; }

    public D_Node getNextNode() { return NextNode; }
    public void setNextNode(D_Node nextNode) { NextNode = nextNode; }

    public D_Node() {
        this.Payload = 0;
        this.NextNode = null;
        this.PreNode = null;
    }

    public D_Node(int payload) {
        this.Payload = payload;
        this.NextNode = null;
        this.PreNode = null;
    }
}

class D_List {
    private D_Node Header;
    private D_Node Tail;

    public D_List() {
        this.Header = null;
        this.Tail = null;
    }

    // getters and setters for Header/Tail
    public D_Node getHeader() { return Header; }
    public void setHeader(D_Node header) { this.Header = header; }

    public D_Node getTail() { return Tail; }
    public void setTail(D_Node tail) { this.Tail = tail; }

    // Append
    public void Append(D_Node node) {
        if (this.Header == null || this.Tail == null) {
            this.Header = node;
            this.Tail = node;
        } else {
            this.Tail.setNextNode(node);
            node.setPreNode(this.Tail);
            this.Tail = node;
        }
    }

    // Prepend
    public void Prepend(D_Node node) {
        if (this.Header == null) {
            this.Header = node;
            this.Tail = node;
        } else {
            this.Header.setPreNode(node);
            node.setNextNode(this.Header);
            this.Header = node;
        }
    }

    // RemoveAfter
    public boolean RemoveAfter(D_Node grandmaNode) {
        if (grandmaNode == null) return false;
        if (grandmaNode.getNextNode() == null) return true;

        D_Node sonNode = grandmaNode.getNextNode();
        D_Node grandsonNode = sonNode.getNextNode();

        if (grandsonNode == null) {
            grandmaNode.setNextNode(null);
            sonNode.setPreNode(null);
            this.Tail = grandmaNode;
        } else {
            grandmaNode.setNextNode(grandsonNode);
            grandsonNode.setPreNode(grandmaNode);
            sonNode.setNextNode(null);
            sonNode.setPreNode(null);
        }
        return true;
    }

    // RemoveBefore
    public boolean RemoveBefore(D_Node currentNode) {
        if (currentNode == null) return false;
        if (currentNode.getPreNode() == null) return false;

        D_Node target = currentNode.getPreNode();
        if (target.getPreNode() == null) {
            this.Header = currentNode;
            currentNode.setPreNode(null);
            target.setNextNode(null);
        } else {
            D_Node grandmaNode = target.getPreNode();
            grandmaNode.setNextNode(currentNode);
            currentNode.setPreNode(grandmaNode);
            target.setNextNode(null);
            target.setPreNode(null);
        }
        return true;
    }

    // InsertAfter
    public boolean InsertAfter(D_Node currentNode, D_Node newNode) {
        if (currentNode == null) return false;

        if (currentNode.getNextNode() == null) {
            currentNode.setNextNode(newNode);
            newNode.setPreNode(currentNode);
            this.Tail = newNode;
        } else {
            D_Node nextNode = currentNode.getNextNode();
            newNode.setNextNode(nextNode);
            newNode.setPreNode(currentNode);
            currentNode.setNextNode(newNode);
            nextNode.setPreNode(newNode);
        }
        return true;
    }

    // InsertBefore
    public boolean InsertBefore(D_Node currentNode, D_Node newNode) {
        if (currentNode == null) return false;

        if (currentNode.getPreNode() == null) {
            this.Header = newNode;
            newNode.setNextNode(currentNode);
            currentNode.setPreNode(newNode);
        } else {
            D_Node preNode = currentNode.getPreNode();
            newNode.setNextNode(currentNode);
            newNode.setPreNode(preNode);
            preNode.setNextNode(newNode);
            currentNode.setPreNode(newNode);
        }
        return true;
    }

    // Search
    public D_Node Search(int value) {
        D_Node current = this.Header;
        while (current != null) {
            if (current.getPayload() == value) return current;
            current = current.getNextNode();
        }
        return null;
    }

    // Traverse
    public int[] Traverse() {
        int total = 0;
        D_Node node = this.Header;
        while (node != null) {
            total++;
            node = node.getNextNode();
        }
        int[] result = new int[total];
        int counter = 0;
        node = this.Header;
        while (node != null) {
            result[counter++] = node.getPayload();
            node = node.getNextNode();
        }
        return result;
    }

    // Merge Sort
    public void Sort() {
        this.Header = mergeSort(this.Header);
        D_Node temp = this.Header;
        while (temp != null && temp.getNextNode() != null) {
            temp = temp.getNextNode();
        }
        this.Tail = temp;
    }

    private D_Node mergeSort(D_Node head) {
        if (head == null || head.getNextNode() == null) return head;

        D_Node middle = getMiddle(head);
        D_Node nextOfMiddle = middle.getNextNode();

        middle.setNextNode(null);
        if (nextOfMiddle != null) nextOfMiddle.setPreNode(null);

        D_Node left = mergeSort(head);
        D_Node right = mergeSort(nextOfMiddle);

        return sortedMerge(left, right);
    }

    private D_Node sortedMerge(D_Node left, D_Node right) {
        if (left == null) return right;
        if (right == null) return left;

        D_Node result;
        if (left.getPayload() <= right.getPayload()) {
            result = left;
            result.setNextNode(sortedMerge(left.getNextNode(), right));
            if (result.getNextNode() != null) result.getNextNode().setPreNode(result);
        } else {
            result = right;
            result.setNextNode(sortedMerge(left, right.getNextNode()));
            if (result.getNextNode() != null) result.getNextNode().setPreNode(result);
        }
        return result;
    }

    private D_Node getMiddle(D_Node head) {
        if (head == null) return head;
        D_Node slow = head;
        D_Node fast = head;
        while (fast.getNextNode() != null && fast.getNextNode().getNextNode() != null) {
            slow = slow.getNextNode();
            fast = fast.getNextNode().getNextNode();
        }
        return slow;
    }
}
