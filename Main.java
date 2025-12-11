public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> arr = new MyArrayList<>(5);

        arr.Append(10);   // add 10 at the end
        arr.Append(20);   // add 20 at the end
        arr.Append(30);   // add 30 at the end
        arr.Append(40);   // add 40 at the end
        arr.Append(50);   // add 50 at the end
        arr.Append(60);   // triggers resize because capacity exceeded

        arr.Prepend(5);   // insert 5 at the beginning
        arr.RemoveAt(2);  // remove element at index 2
        arr.Sort(true);   // sort ascending

        System.out.println("Length: " + arr.GetLength());
        System.out.println("Index of 40: " + arr.Search(40));
        System.out.println("Element at index 3: " + arr.Get(3));
    }
}

class MyArrayList<T> {
    private T[] List;   // internal array to store elements
    private int length; // number of elements currently in the list
    private int size;   // capacity of the internal array

    // Constructor: initializes the list with a given capacity (minimum 10)
    public MyArrayList(int size) {
        this.size = Math.max(size, 10);
        this.List = (T[]) new Object[this.size];
        this.length = 0;
    }

    // Resize: doubles capacity if 70% full, halves capacity if <=30% full (but not below 10)
    private void Resize() {
        if (this.length >= this.size * 7 / 10) {
            this.size *= 2; // grow
        } else if (this.length <= this.size * 3 / 10 && this.size > 10) {
            this.size /= 2; // shrink
        } else {
            return; // no resize needed
        }
        // copy elements into new array
        T[] newList = (T[]) new Object[this.size];
        for (int i = 0; i < this.length; i++) {
            newList[i] = this.List[i];
        }
        this.List = newList;
    }

    // GetLength: returns the number of elements currently stored
    public int GetLength() { return this.length; }

    // Get: returns the element at a given index, or null if out of bounds
    public T Get(int index) {
        if (index < 0 || index >= this.length) return null;
        return this.List[index];
    }

    // Prepend: inserts an element at the beginning (shifts all elements right)
    public void Prepend(T data) {
        for (int i = this.length; i > 0; i--) {
            this.List[i] = this.List[i - 1];
        }
        this.List[0] = data;
        this.length++;
        this.Resize();
    }

    // Append: adds an element at the end
    public void Append(T data) {
        this.List[this.length] = data;
        this.length++;
        this.Resize();
    }

    // InsertAt: inserts an element at a specific index
    // - if index == length, behaves like append
    // - if index == 0, behaves like prepend
    // - otherwise shifts elements right from that index
    public boolean InsertAt(int index, T data) {
        if (index < 0 || index > this.length) return false;
        if (index == this.length) { Append(data); return true; }
        if (index == 0) { Prepend(data); return true; }
        for (int i = this.length; i > index; i--) {
            this.List[i] = this.List[i - 1];
        }
        this.List[index] = data;
        this.length++;
        this.Resize();
        return true;
    }

    // RemoveAt: removes the element at a specific index
    // shifts all elements left to fill the gap
    public boolean RemoveAt(int index) {
        if (index < 0 || index >= this.length) return false;
        for (int i = index; i < this.length - 1; i++) {
            this.List[i] = this.List[i + 1];
        }
        this.List[this.length - 1] = null; // clear last slot
        this.length--;
        this.Resize();
        return true;
    }

    // Search: returns the index of the first occurrence of data, or -1 if not found
    public int Search(T data) {
        for (int i = 0; i < this.length; i++) {
            if (this.List[i].equals(data)) return i;
        }
        return -1;
    }

    // Sort: bubble sort implementation
    // Ascending = true → sort smallest to largest
    // Ascending = false → sort largest to smallest
    public void Sort(boolean Ascending) {
        for (int i = 0; i < this.length - 1; i++) {
            for (int j = 0; j < this.length - i - 1; j++) {
                Comparable<T> a = (Comparable<T>) this.List[j];
                if ((Ascending && a.compareTo(this.List[j + 1]) > 0) ||
                    (!Ascending && a.compareTo(this.List[j + 1]) < 0)) {
                    T temp = this.List[j];
                    this.List[j] = this.List[j + 1];
                    this.List[j + 1] = temp;
                }
            }
        }
    }
}
