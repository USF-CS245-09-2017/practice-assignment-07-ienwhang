import java.util.ArrayList;
//import java.util.LinkedList;

public class Hashtable {
	
	private ArrayList<HashNode> bucketArr;
	private int numBuckets;
	private int size;
	
	// Subclass for HashNode
	private class HashNode {
		String key, value;
		HashNode next;
		HashNode(String key, String value) {
			this.key = key;
			this.value = value;
			next = null;
		}
	}
	
	public Hashtable() {
		bucketArr = new ArrayList<HashNode>();
		numBuckets = 314527;
//		numBuckets = 6;
		size = 0;
		for (int i=0; i<numBuckets; i++) {
			bucketArr.add(null);
		}
	}
	
	// Get index in arraylist using hashcode
	public int getIndex(String key) {
		int h = Math.abs(key.hashCode());
		int index = h%numBuckets;
		return index;
	}
	
	// Return true if hashtable contains key
	public boolean containsKey(String key) {
		int index = getIndex(key);
		HashNode head = bucketArr.get(index);  // Get head of linked list at index of key
		while (head != null) {  
			if (head.key.equals(key)) 
				return true;  // Return true if found
			head = head.next;
		}
		return false;
	}
	
	// Get value of key in hashtable
	public String get(String key) {
		int index = getIndex(key);
		HashNode head = bucketArr.get(index);  // Get head of linked list at index of key
		while (head != null) {  // Find if key exists in linked list
			if (head.key.equals(key)) 
				return head.value;  // Return value of head if found
			head = head.next;
		}
		return null;  // Else return null
	}
	
	// Insert key-value pair into hashtable
	public void put(String key, String value) {
		int index = getIndex(key);
		HashNode head = bucketArr.get(index);  // Get head of linked list at index of key
		HashNode end = null;
		while (head != null) {  // If key exists in linked list
			if (head.key.equals(key)) {
				head.value = value;
				size++;  // Increment size
				return;
			}
			else 
				if (head.next == null)  // Measure to prevent head == null
					end = head;
				head = head.next;
		}
		if (end == null) {
			end = new HashNode(key, value);  // Inserting as head
			bucketArr.set(index, end);
		}
		else
			end.next = new HashNode(key, value);  // Inserting into positions other than head
		size++;  // Increment size
	}
	
	public String remove(String key) {
		int index = getIndex(key);
		HashNode head = bucketArr.get(index);  // Get head of linked list at index of key
		String value = null;
		if (containsKey(key)) {  
			if (head.key.equals(key)) {  // If head == key
				value = head.value;
				HashNode next = head.next;
				bucketArr.set(index, head.next);
			}
			else {
				while (head != null) {  // If key is at position other than head
					if (head.next.key.equals(key)) {
						value = head.value;
						HashNode removed = head.next;
						head.next = removed.next;
					}
					head = head.next;
				}
			}
			size--;  // Decrement size
			return value;
		}
		else  // If key not in hashtable
			return value;
	}
	
}
