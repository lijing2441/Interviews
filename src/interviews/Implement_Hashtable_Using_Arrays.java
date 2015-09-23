package interviews;

public class Implement_Hashtable_Using_Arrays {
	/**
	 * Implement a hashtable using arrays
	 * 
	 * Follow up: How to distribute it? 
	 * 
	 * We can use distributed hash tables where each node in the distributed system manages 
	 * some set of predefined keys.
	 * 
	 * Along with hash table, functionality exists to route hashing/retrieval
	 * request to the other nodes in the DHT system.
	 */	
}

class HashTable {
	private double loadFactor = 0.75;

	public static HashEntry[] table;

	private int elemCount;
	
	private static int TABLE_SIZE;
	
	public HashTable() {
		table = new HashEntry[TABLE_SIZE];
		for(int i = 0; i < TABLE_SIZE; i++){
			table[i] = null;
		}
	}
	// insertion
	public void put(int key, int value) {
		//check whether the addition will get the table full
		if (elemCount > table.length * loadFactor)
			resize();
		// hash to the right index of the table
		int index = hash(hash(key)) % table.length;
		// if empty spot found
		if (table[index] == null){
			table[index] = new HashEntry(key, value);
		}else{
			// the index is occupied
			HashEntry cur = table[index];
			while (true) {
				if (cur.getKey() == key) {
					cur.setValue(value);
					break;
				}
				if (cur.next() == null) // find an empty spot
					break;
				// not empty, we move to the next position in the array
				cur = cur.next();
			}
			cur.setNext(new HashEntry(key, value));
		}
	}
	// search
	public int get(int key) {
		// find the spot
		int index = hash(hash(key)) % table.length;
		if (table[index] == null)
			return -1;
		else {
			HashEntry cur = table[index];
			while (true) {
				// since we deal the conflict using moving to the next spot
				if (cur.getKey() == key) {
					return cur.getValue();
				}
				if (cur.next() == null)
					break;
				cur = cur.next();
			}
			return -1;
		}
	}
	// deletion
	public void remove(int key){
		// hash the hashCode of the key to the correct index of the table
		int index = hash(key) % table.length;
		// if spot found, nullify the spot
		if (table[index] != null) {
			table[index] = null;
		}
	}
	
	public void resize() {
		int newSize = (int) (table.length * 1.5);
		HashEntry[] newTable = new HashEntry[newSize];
		// move to the new array
		for (int i = 0; i < table.length; i++)
			newTable[i] = table[i];
		table = newTable;
	}
	
	// helper function
	public static int hash(int h) {
		// return a great hashCode
		h ^= (h >>> 20) ^ (h >>> 12);
		return h ^ (h >>> 7) ^ (h >>> 4);
	}
}

class HashMap {
    private final static int TABLE_SIZE = 128;

    HashEntry[] table;

    HashMap() {
          table = new HashEntry[TABLE_SIZE];
          for (int i = 0; i < TABLE_SIZE; i++)
                table[i] = null;
    }

    public int get(int key) {
          int hash = (key % TABLE_SIZE);
          while (table[hash] != null && table[hash].getKey() != key)
                hash = (hash + 1) % TABLE_SIZE;
          if (table[hash] == null)
                return -1;
          else
                return table[hash].getValue();
    }

    public void put(int key, int value) {
          int hash = (key % TABLE_SIZE);
          while (table[hash] != null && table[hash].getKey() != key)
                hash = (hash + 1) % TABLE_SIZE;
          table[hash] = new HashEntry(key, value);
    }
}

class HashEntry {
	int key;
	int value;
	HashEntry next;

	// constructors, getters and setters below
	public HashEntry(int key, int value) {
		this.key = key;
		this.value = value;
	}

	public HashEntry next() {
		return this.next;
	}

	public int getKey() {
		return this.key;
	}

	public void setNext(HashEntry HashEntry) {
		this.next = HashEntry;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}
}
