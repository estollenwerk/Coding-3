public class Main {
    public static void main(String[] args) {
        HTProbing ht = new HTProbing(5);

        ht.Insert(10);
        ht.Insert(15);
        ht.Insert(20);
        ht.Insert(25);
        ht.Insert(30); // triggers resize automatically
        ht.Delete(20);
        System.out.println(ht.Search(15)); // true
        System.out.println(ht.Search(99)); // false
    }
}

class HTProbing {
    private Integer[] table;   // store values directly
    private int size;
    private int count;         // number of elements
    private final double LOAD_FACTOR = 0.75; // threshold

    public HTProbing(int size) {
        this.size = size;
        table = new Integer[size];
        count = 0;
    }

    private int Hashing(int value) {
        return value % size;
    }

    // Insert with automatic resize
    public void Insert(int value) {
        // Check threshold
        if ((double) count / size > LOAD_FACTOR) {
            Resize(size * 2); // double the table size
        }

        int index = Hashing(value);
        int startIndex = index;

        while (table[index] != null && table[index] != -1) {
            index = (index + 1) % size;
            if (index == startIndex) {
                System.out.println("Table is full, cannot insert " + value);
                return;
            }
        }
        table[index] = value;
        count++;
    }

    public boolean Search(int value) {
        int index = Hashing(value);
        int startIndex = index;

        while (table[index] != null) {
            if (table[index] != -1 && table[index] == value) {
                return true;
            }
            index = (index + 1) % size;
            if (index == startIndex) break;
        }
        return false;
    }

    public void Delete(int value) {
        int index = Hashing(value);
        int startIndex = index;

        while (table[index] != null) {
            if (table[index] != -1 && table[index] == value) {
                table[index] = -1; // tombstone
                count--;
                return;
            }
            index = (index + 1) % size;
            if (index == startIndex) break;
        }
    }

    // Resize with rehashing
    private void Resize(int newSize) {
        Integer[] oldTable = table;
        this.size = newSize;
        this.table = new Integer[newSize];
        this.count = 0;

        for (Integer val : oldTable) {
            if (val != null && val != -1) {
                this.Insert(val); // rehash into new table
            }
        }
    }
}
