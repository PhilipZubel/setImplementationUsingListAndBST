package assessedExercise;

import java.lang.reflect.Array;
import java.util.Arrays;

public class BSTSet<Item extends Comparable<Item>> extends Set<Item> {
	private Node<Item> root; // root of BST

	private class Node<Item> {
		private Item key; // sorted by key
		private Node<Item> left, right, p;

		public Node(Item key) {
			this.key = key;
			this.left = null;
			this.right = null;
			this.p = null;
		}
	}

	// adds an item to the set
	// return true if the value is added, otherwise return false
	public boolean add(Item item) {
		Node<Item> y = null;
		Node<Item> x = root;
		while (x != null) {
			y = x;
			if (item.compareTo(x.key) < 0) {
				x = x.left;
			} else if (item.compareTo(x.key) > 0) {
				x = x.right;
			} else {
				// element is already in the set
				return false;
			}
		}
		// add the value to the set
		Node<Item> n = new Node(item);
		if (y == null) {
			root = n;
		} else if (item.compareTo(y.key) < 0) {
			y.left = n;
			n.p = y;
		} else {
			y.right = n;
			n.p = y;
		}
		return true;
	}

	// this method is used in remove(Item item)
	// returns the smallest child of the node c
	private Node getMinimum(Node<Item> x) {
		Node<Item> temp = x;
		while (temp.left != null) {
			temp = temp.left;
		}
		return temp;
	}

	// removes a value from the set
	// returns true if the value is successfully removed from the set,
	// otherwise returns false
	public boolean remove(Item item) {
		// find element in the tree
		Node<Item> x = root;
		while (x != null) {
			if (item.equals(x.key)) {
				// element is found
				// delete element at node x
				if (x.left == null) {
					transplant(x, x.right);
				} else if (x.right == null) {
					transplant(x, x.left);
				} else {
					Node y = getMinimum(x.right);
					if (y.p != x) {
						transplant(y, y.right);
						y.right = x.right;
						y.right.p = y;
					}
					transplant(x, y);
					y.left = x.left;
					y.left.p = y;
				}
				return true;
			} else if (item.compareTo(x.key) < 0) {
				x = x.left;
			} else {
				x = x.right;
			}
		}
		// element not found
		return false;
	}

	// this method is used in remove(Item item)
	// the references are chenged in order to skip the node u
	private void transplant(Node u, Node v) {
		if (u.p == null) {
			root = v;
		} else if (u == u.p.left) {
			u.p.left = v;
		} else {
			u.p.right = v;
		}
		if (v != null) {
			v.p = u.p;
		}
	}

	// checks if the method is in the set
	// returns true if the item is in the set, otherwise returns false
	public boolean isElement(Item item) {
		Node<Item> temp = root;
		while (temp != null) {
			if (item.compareTo(temp.key) < 0) {
				temp = temp.left;
			} else if (item.compareTo(temp.key) > 0) {
				temp = temp.right;
			} else {
				return true;
			}
		}
		return false;
	}

	// returns if the set is empty
	public boolean setEmpty() {
		return root == null;
	}

	// returns the number of elements in the set
	public int setSize() {
		return setSizeHelper(root);
	}

	// returns the number of elements in the tree assuming that root of the tree is
	// node n
	private int setSizeHelper(Node n) {
		if (n == null) {
			return 0;
		} else {
			return 1 + setSizeHelper(n.left) + setSizeHelper(n.right);
		}
	}

	// prints the inorder traversal of the binary search tree
	public void print() {
		printHelper(root);
	}

	private void printHelper(Node n) {
		if (n != null) {
			printHelper(n.left);
			System.out.println(n.key);
			printHelper(n.right);
		}
	}

	// returns the union of this set and the set passed
	public BSTSet UNION(BSTSet set) {
		BSTSet<Item> union = new BSTSet();
		// insert all values from set to union
		copyNodes(union, root);
		// insert all values from this set to union
		copyNodes(union, set.root);
		return union;
	}

	// returns the difference of this set and the set passed
	public BSTSet DIFFERENCE(BSTSet set) {
		BSTSet<Item> difference = new BSTSet();
		// insert all values from this set to difference
		copyNodes(difference, root);
		// delete all values of the set passed from the difference set
		removeNodes(difference, set.root);
		return difference;
	}

	// returns the difference of the set and the set passed
	public BSTSet INTERSECTION(BSTSet set) {
		BSTSet intersection = this.UNION(set);
		removeNodes(intersection, this.DIFFERENCE(set).root);
		removeNodes(intersection, set.DIFFERENCE(this).root);
		return intersection;
	}

	// checks if this set is a subset of the set passed
	public boolean SUBSET(BSTSet set) {
		return set.checkExistanceNodes(root);
	}

	// recursively checks if the node and the child nodes are in this set
	private boolean checkExistanceNodes(Node<Item> n) {
		if (n == null) {
			return true;
		}
		return this.isElement(n.key) && this.checkExistanceNodes(n.left) && this.checkExistanceNodes(n.right);
	}

	// copies the value of the node and the value of the children's node to
	// copoToSet
	private void copyNodes(BSTSet<Item> copyToSet, Node<Item> copyNode) {
		if (copyNode != null) {
			copyToSet.add(copyNode.key);
			copyNodes(copyToSet, copyNode.left);
			copyNodes(copyToSet, copyNode.right);
		}
	}

	// removes the value of the node and the value of the children's node from
	// deleteFromSet
	private void removeNodes(BSTSet<Item> deleteFromSet, Node<Item> deleteNode) {
		if (deleteNode != null) {
			deleteFromSet.remove(deleteNode.key);
			removeNodes(deleteFromSet, deleteNode.left);
			removeNodes(deleteFromSet, deleteNode.right);
		}
	}

	// returns the height of the tree
	public int getHeight() {
		return getHeightHelper(root);
	}

	private int getHeightHelper(Node<Item> n) {
		if (n == null)
			return 0;
		if (n.left == null && n.right == null) {
			return 0;
		}
		return 1 + Math.max(getHeightHelper(n.left), getHeightHelper(n.right));
	}

	public BSTSet<Item> UNION2(BSTSet set2) {
		// if one of the sets is empty, return the other set
		if (this.root == null) {
			return set2;
		}
		if (set2.root == null) {
			return this;
		}

		// Step1: Store binary search trees in sorted arrays aa1, aa2

		// the setSize() algorithm takes O(n) to complete
		int size1 = this.setSize();
		Item[] aa1 = (Item[]) Array.newInstance(this.root.key.getClass(), size1);
		// fills the array with time complexity O(n)

		fillArray(aa1, this);

		int size2 = set2.setSize();
		Item[] aa2 = (Item[]) Array.newInstance(this.root.key.getClass(), size2);
		fillArray(aa2, set2);

		// Step 2: Merge both arrays into a single sorted array

		Item[] merged = mergeArrays(aa1, aa2);

		// Step 3: Convert the array into a balanced binary search tree

		BSTSet<Item> set = new BSTSet<Item>();
		System.out.println(Arrays.toString(merged));
		convertToBST(set, merged);
		System.out.println(set.root.key);
		
		return set;
	}

	private void convertToBST(BSTSet<Item> set, Item[] array) {
		if(array.length>0) {
			// add head value of the tree
			int middle = array.length/2;
			set.root = new Node<Item>(array[middle]);
			// call the left and right branches of the tree
			set.root.left = convertToBST(root.left, array, 0, middle-1, root);
			set.root.right = convertToBST(root.right, array, middle+1, array.length-1, root);
		}
	}

	private Node<Item> convertToBST(Node<Item> node, Item[] array, int start, int end, Node<Item> parent) {
		if (start <= end) {
			int middle = (start + end)/2;
			// add middle value as the value to the current node
			node = new Node(array[middle]);
			node.p = parent;
			// insert left branch
			node.left = convertToBST(node.left, array, start, middle-1, node);
			// insert right branch
			node.right = convertToBST(node.right, array, middle+1, end, node);
			return node;
		}
		return null;
	}

	
	// merge aa1 and aa2 into a new array
	private Item[] mergeArrays(Item[] aa1, Item[] aa2) {
		Item[] merged = (Item[]) Array.newInstance(this.root.key.getClass(), aa1.length + aa2.length);
		int idx1 = 0, idx2 = 0, idx = 0;
		// insert the smallest values first
		while (idx1 < aa1.length && idx2 < aa2.length) {
			if (aa1[idx1].equals(aa2[idx2])) {
				// ignore duplicates
				idx1++;
			} else {
				if (aa1[idx1].compareTo(aa2[idx2]) < 0) {
					merged[idx++] = aa1[idx1++];
				} else {
					merged[idx++] = aa2[idx2++];
				}
			}
		}
		// when one array is inserted but the other still has elements which are not inserted yet
		// insert those elements to the new array
		if (idx1 == aa1.length) {
			for (int i = idx2; i < aa2.length; i++) {
				merged[idx++] = aa2[idx2];
			}
		}
		if (idx2 == aa2.length) {
			for (int i = idx1; i < aa1.length; i++) {
				merged[idx++] = aa1[idx1];
			}
		}

		// decrease the size of the array since duplicates are not considered
		Item[] result = (Item[]) Array.newInstance(this.root.key.getClass(), idx);
		System.arraycopy(merged, 0, result, 0, idx);
		return result;
	}

	// returns an array using inorder traversal
	// each node is of the tree is visited exactly once, so time complexity is O(n)
	private void fillArray(Item[] aa, BSTSet<Item> bst) {
		fillArrayHelper(aa, bst.root, 0);
	}

	// i tracks the first available index of the array
	private int fillArrayHelper(Item[] aa, BSTSet<Item>.Node<Item> node, int i) {
		// if node does not have any children, add the node to the array
		if (node.left == null && node.right == null) {

			aa[i] = node.key;
			return i + 1;
		}
		// if node has children greater than the node
		if (node.left == null) {
			// add the node to the array
			aa[i] = node.key;
			// get the next available index of the array while calling the right branch
			return fillArrayHelper(aa, node.right, i + 1);
		}
		// if node has children smaller than the node
		if (node.right == null) {
			// get the next index
			int n = fillArrayHelper(aa, node.left, i);
			// add the node to the array
			aa[n] = node.key;
			return n + 1;
		}
		// otherwise get the next available index
		int n = fillArrayHelper(aa, node.left, i);
		// add the value to the array
		aa[n] = node.key;
		// get the next index for the parent while calling the right branch
		int max = fillArrayHelper(aa, node.right, n + 1);
		// return the parent's index
		return max;
	}

}
