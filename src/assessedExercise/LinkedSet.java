package assessedExercise;

public class LinkedSet<Item> extends Set<Item> {
	private Node<Item> head;
	private Node<Item> tail;

	private static class Node<Item> {
		private Item key;
		private Node<Item> next;
		private Node<Item> previous;
	}

	public LinkedSet() {
		head = null;
		tail = head;
	}

	// checks if set is empty
	public boolean setEmpty() {
		return head == null;
	}

	// returns the length of the set
	public int setSize() {
		int count = 0;
		Node<Item> temp = head;
		while (temp != null) {
			count++;
			temp = temp.next;
		}
		return count;
	}

	// checks if item is present in the set
	public boolean isElement(Item item) {
		Node<Item> temp = head;
		while (temp != null) {
			if (temp.key.equals(item)) {
				// if element does not exist return false
				return true;
			}
			temp = temp.next;
		}
		return false;
	}

	// this method adds the item to the set
	// returns true if added successfully,
	// returns false if item is already in the set
	public boolean add(Item item) {
		// if item is in the set, return false
		if (isElement(item))
			return false;

		Node<Item> n = new Node<>();
		n.key = item;
		// if head is empty, set node to head and tail
		if (head == null) {
			head = n;
			tail = n;
		}
		// if head is full, set the node to tail and link it to the previous node
		else {
			n.previous = tail;
			tail.next = n;
			tail = n;
		}
		return true;
	}

	// this method deletes the item from the set
	// returns true if deleted successfully
	// returns false if is not in the set
	public boolean remove(Item item) {
		// logic for deleting head
		if (head != null && head.key.equals(item)) {
			head = head.next;
			head.previous = null;
			return true;
		}
		// logic for deleting tail
		if (tail != null && tail.key.equals(item)) {
			tail = tail.previous;
			tail.next = null;
			return true;
		}
		// logic for deleting a non-head node
		Node<Item> n = head;
		while (n != null) {
			if (n.key.equals(item)) {
				n.next.previous = n.previous;
				n.previous.next = n.next;
				return true;
			}
			n = n.next;
		}
		return false;
	}

	// returns a set which is the union of the two sets
	public LinkedSet<Item> UNION(LinkedSet<Item> set) {
		LinkedSet<Item> union = new LinkedSet<>();
		Node<Item> temp = head;
		// add all nodes from this set
		while (temp != null) {
			union.add(temp.key);
			temp = temp.next;
		}
		// add all nodes from the set passed as an argument
		temp = set.head;
		while (temp != null) {
			union.add(temp.key);
			temp = temp.next;
		}
		return union;
	}

	// returns a set which is the intersection of the two sets
	public LinkedSet<Item> INTERSECTION(LinkedSet<Item> set) {
		LinkedSet<Item> intersection = new LinkedSet<>();
		Node<Item> temp = head;
		// for all nodes in this set check if the key is in the set passed,
		// if so add it to the intersection set
		while (temp != null) {
			if (set.isElement(temp.key)) {
				intersection.add(temp.key);
			}
			temp = temp.next;
		}
		return intersection;

	}

	// returns a set which is the difference of the two sets
	public LinkedSet<Item> DIFFERENCE(LinkedSet<Item> set) {
		LinkedSet<Item> difference = new LinkedSet<>();
		Node<Item> temp = head;
		// for all nodes in this set check if the key is not in the set passed,
		// if it is not in the set passed, add the key to the difference set
		while (temp != null) {
			if (!set.isElement(temp.key)) {
				difference.add(temp.key);
			}
			temp = temp.next;
		}
		return difference;
	}

	// returns if this set is a subset of set passed
	public boolean SUBSET(LinkedSet<Item> set) {
		Node<Item> temp = head;
		// for all nodes in this set check if the key is not in the set passed,
		// if it is not in the set passed, this set is not a subset of the passed set
		while (temp != null) {
			if (!set.isElement(temp.key)) {
				return false;
			}
			temp = temp.next;
		}
		return true;
	}

	public void printList() {
		Node<Item> n = head;
		while (n != null) {
			System.out.println(n.key);
			n = n.next;
		}
	}

	public void printReverseList() {
		Node<Item> n = tail;
		while (n != null) {
			System.out.println(n.key);
			n = n.previous;
		}
	}

}
