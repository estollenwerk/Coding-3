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

    public int Hashing(int value) {
        return value % size;
    }

    // Existing chaining insert
    public void Insert(int value) {
        int index = this.Hashing(value);
        this.table[index].Append(value);
    }

    // 🔥 New probing insert (linear probing)
    public void InsertProbing(int value, int[] probeTable) {
        int index = this.Hashing(value);

        // Linear probing: move forward until empty slot found
        while (probeTable[index] != 0) {
            index = (index + 1) % size; // wrap around
        }
        probeTable[index] = value;
    }

    // Search with probing
    public boolean SearchProbing(int value, int[] probeTable) {
        int index = this.Hashing(value);

        // Linear probing search
        int startIndex = index;
        while (probeTable[index] != 0) {
            if (probeTable[index] == value) {
                return true;
            }
            index = (index + 1) % size;
            if (index == startIndex) break; // full loop
        }
        return false;
    }

    // Delete with probing (simple version: mark as -1)
    public void DeleteProbing(int value, int[] probeTable) {
        int index = this.Hashing(value);
        int startIndex = index;
        while (probeTable[index] != 0) {
            if (probeTable[index] == value) {
                probeTable[index] = -1; // tombstone marker
                return;
            }
            index = (index + 1) % size;
            if (index == startIndex) break;
        }
    }
}
