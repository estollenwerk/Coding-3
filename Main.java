public class Main {
    public static void main(String[] args) {
        HTChaining ht = new HTChaining(5);

        ht.Insert(10);
        ht.Insert(15);
        ht.Insert(20);
        ht.Insert(30);
        ht.Insert(40);
        ht.Insert(25);
        ht.Insert(35);

        System.out.println(ht.Search(15)); // true
        System.out.println(ht.Search(99)); // false

        ht.Delete(15);
        System.out.println(ht.Search(15)); // false
    }
}

// Hash table with separate chaining
class HTChaining {
    public LinkedList[] table;
    public int size;

    public HTChaining(int size) {
        this.size = size;
        table = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList();
        }
    }

    private int Hashing(int value) {
        return value % size;
    }
    
    public Integer Get(int value) {
        int index = Hashing(value);
        Node node = table[index].Search(value);
        if (node != null) {
            return node.Payload; // return the stored value
        }
        return null; // not found
    }



    public void Insert(int value) {
        int index = Hashing(value);
        table[index].Append(value);
    }

    public boolean Search(int value) {
        int index = Hashing(value);
        return table[index].Search(value) != null;
    }

    public void Delete(int value) {
        int index = Hashing(value);
        table[index].Delete(value);
    }
    
    public void Resize(int newSize) {
        LinkedList[] oldTable = table;
        this.size = newSize;
        this.table = new LinkedList[newSize];
        for (int i = 0; i < newSize; i++) {
            this.table[i] = new LinkedList();
        }

        // rehash all elements
        for (LinkedList bucket : oldTable) {
            Node node = bucket.Header;
            while (node != null) {
                this.Insert(node.Payload);
                node = node.NextNode;
            }
        }
    }

}

// Linked list for chaining
class LinkedList {
    public Node Header;

    public LinkedList() {
        this.Header = null;
    }

    public Node Search(int value) {
        Node node = Header;
        while (node != null) {
            if (node.Payload == value) return node;
            node = node.NextNode;
        }
        return null;
    }

    public void Append(int value) {
        if (Search(value) != null) return; // avoid duplicates
        if (Header == null) {
            Header = new Node(value);
        } else {
            Node node = Header;
            while (node.NextNode != null) {
                node = node.NextNode;
            }
            node.NextNode = new Node(value);
        }
    }

    public void Delete(int value) {
        if (Header == null) return;
        if (Header.Payload == value) {
            Header = Header.NextNode;
            return;
        }
        Node prev = Header;
        Node curr = Header.NextNode;
        while (curr != null) {
            if (curr.Payload == value) {
                prev.NextNode = curr.NextNode;
                return;
            }
            prev = curr;
            curr = curr.NextNode;
        }
    }
}

// Node for linked list
class Node {
    int Payload;
    Node NextNode;

    public Node(int value) {
        this.Payload = value;
        this.NextNode = null;
    }
}
