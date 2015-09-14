package interviews;

import interviews.Implement_Hashtable_Using_Arrays.Entry;

public class Implement_Hashtable_Using_Arrays {
	/**
	 * Implement a hashtable using arrays
	 */
	private double loadFactor = 0.75;

	Entry[] table;

	private int elemCount;
	
	private static int TABLE_SIZE;
	
	public class Entry {
		K key;
		V value;
		Entry next;

		// constructors, getters and setters below
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public Entry next() {
			return this.next;
		}

		public Object getKey() {
			return this.key;
		}

		public void setNext(Entry entry) {
			this.next = entry;
		}

		public void setValue(V value) {
			this.value = value;
		}

		public V getValue() {
			return this.value;
		}
	}

	/**
	 * Insert your super-mega-hash-function below :)
	 * 
	 * The fuction was taken from series of experiments which gave good results
	 * with default load factor. We won’t re-invent a bicycle and take this
	 * function :)
	 * 
	 * What if hash(key, hashCode()) will be the same for several elements? In
	 * an array the i-th element will contain an entry with the first saved
	 * element. It will store a link to the next element. This means that we
	 * store an array of linked lists.
	 * 
	 * The worst case of get() operation has complexity O(n). But in practice
	 * the complexity is near O(1).
	 * 
	 * Algorithm of adding an alement to hash table:
	 * 
	 * Check the load of an array. If it is bigger than load factor - call
	 * resize() Calclate hash; if in array there aren’t any elements with this
	 * hash-index - insert id. Unless we come through a linked list of elements
	 * with his hash and check whether an entry with the same key exists. If so,
	 * change its value. If a list doesn’t contain it - we add the Entry to the
	 * end of this list.
	 */
	
	public static class HashTable{
		Entry[] table = new Entry[TABLE_SIZE];
		for(int i = 0; i < TABLE_SIZE; i++){
			table[i] = null;
		}
	}
	
	public static int hash(int h) {
		// return a great hashCode
		h ^= (h >>> 20) ^ (h >>> 12);
		return h ^ (h >>> 7) ^ (h >>> 4);
	}

	public void put(K key, V value) {
		//check whether the addition will get the table full
		if (elemCount > table.length * loadFactor)
			resize();
		// hash to the right index of the table
		int index = hash(key.hashCode()) % table.length;
		// if empty spot found
		if (table[index] == null){
			table[index] = new Entry(key, value);
		}else{
			// the index is occupied
			Entry cur = table[index];
			while (true) {
				if (cur.getKey().equals(key)) {
					cur.setValue(value);
					break;
				}
				if (cur.next() == null) // find an empty spot
					break;
				// not empty, we move to the next position in the array
				cur = cur.next();
			}
			cur.setNext(new Entry(key, value));
		}
	}

	public V get(K key) {
		// find the spot
		int index = hash(key.hashCode()) % table.length;
		if (table[index] == null)
			return null;
		else {
			Entry cur = table[index];
			while (true) {
				// since we deal the conflict using moving to the next spot
				if (cur.getKey().equals(key)) {
					return cur.getValue();
				}
				if (cur.next() == null)
					break;
				cur = cur.next();
			}
			return null;
		}
	}
	
	public void remove(K key){
		// hash to the right index of the table
		int index = hash(key.hashCode()) % table.length;
		// if spot found, nullify the spot
		if (table[index] != null) {
			table[index] = null;
		}
	}
	
	public void resize() {
		int newSize = (int) (table.length * 1.5);
		Entry[] newTable = new Entry[newSize];
		// move to the new array
		for (int i = 0; i < table.length; i++)
			newTable[i] = table[i];
		table = newTable;
	}

	/**
	 * Follow up: How to distribute it? 
	 * 
	 * We can use distributed hash tables where each node in the distributed system manages 
	 * some set of predefined keys.
	 * 
	 * Along with hash table, functionality exists to route hashing/retrieval
	 * request to the other nodes in the DHT system.
	 * 
	 */
}
class K{
	
}
class V{
	
}

class HashMap {
    private final static int TABLE_SIZE = 128;

    Entry[] table;

    HashMap() {
          table = new Entry[TABLE_SIZE];
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