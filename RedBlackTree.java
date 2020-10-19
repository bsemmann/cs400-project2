import java.util.LinkedList;

/**
 * Binary Search Tree implementation with a Node inner class for representing
 * the nodes within a binary search tree. You can use this class' insert method
 * to build a binary search tree, and its toString method to display the level
 * order (breadth first) traversal of values in that tree.
 */
public class RedBlackTree<T extends Comparable<T>> {
	/**
	 * This class represents a node holding a single value within a binary tree the
	 * parent, left, and right child references are always be maintained.
	 */
	protected static class Node<T> {
		public T data;
		public boolean isBlack;
		public Node<T> parent; // null for root node
		public Node<T> leftChild;
		public Node<T> rightChild;

		public String name;
		public int year;
		public int exam1;
		public int exam2;
		public int exam3;

		public Node(T data) {
			this.data = data;
			isBlack = false;
		}

		public Node(T data, String name, int year, int exam1, int exam2, int exam3) {
			this.data = data;
			isBlack = false;
			this.name = name;
			this.year = year;
			this.exam1 = exam1;
			this.exam2 = exam2;
			this.exam3 = exam3;
		}

		/**
		 * @return true when this node has a parent and is the left child of that
		 *         parent, otherwise return false
		 */
		public boolean isLeftChild() {
			return parent != null && parent.leftChild == this;
		}

		/**
		 * This method performs a level order traversal of the tree rooted at the
		 * current node. The string representations of each data value within this tree
		 * are assembled into a comma separated string within brackets (similar to many
		 * implementations of java.util.Collection).
		 * 
		 * @return string containing the values of this tree in level order
		 */
		@Override
		public String toString() { // display subtree in order traversal
			String output = "";
			LinkedList<Node<T>> q = new LinkedList<>();
			q.add(this);
			while (!q.isEmpty()) {
				Node<T> next = q.removeFirst();
				if (next.leftChild != null)
					q.add(next.leftChild);
				if (next.rightChild != null)
					q.add(next.rightChild);
				output += next.data.toString();
				if (!q.isEmpty())
					output += ", ";
			}
			return output;
		}
	}

	protected Node<T> root; // reference to root node of tree, null when empty

	/**
	 * Performs a naive insertion into a binary search tree: adding the input data
	 * value to a new node in a leaf position within the tree. After this insertion,
	 * no attempt is made to restructure or balance the tree. This tree will not
	 * hold null references, nor duplicate data values.
	 * 
	 * @param data to be added into this binary search tree
	 * @throws NullPointerException     when the provided data argument is null
	 * @throws IllegalArgumentException when the tree already contains data
	 */

	public void insert(T data, String name, int year, int exam1, int exam2, int exam3)
			throws NullPointerException, IllegalArgumentException {
		// null references cannot be stored within this tree
		if (data == null)
			throw new NullPointerException("This RedBlackTree cannot store null references.");
		Node<T> newNode = new Node<>(data, name, year, exam1, exam2, exam3);
		newNode.name = name;
		newNode.year = year;
		newNode.exam1 = exam1;
		newNode.exam2 = exam2;
		newNode.exam3 = exam3;
		if (root == null) {
			root = newNode;
		} // add first node to an empty tree
		else
			insertHelper(newNode, root); // recursively insert into subtree
		this.root.isBlack = true;
	}

	/**
	 * Recursive helper method to find the subtree with a null reference in the
	 * position that the newNode should be inserted, and then extend this tree by
	 * the newNode in that position.
	 * 
	 * @param newNode is the new node that is being added to this tree
	 * @param subtree is the reference to a node within this tree which the newNode
	 *                should be inserted as a descenedent beneath
	 * @throws IllegalArgumentException when the newNode and subtree contain equal
	 *                                  data references (as defined by
	 *                                  Comparable.compareTo())
	 */
	private void insertHelper(Node<T> newNode, Node<T> subtree) {
		int compare = newNode.data.compareTo(subtree.data);
		// do not allow duplicate values to be stored within this tree
		if (compare == 0)
			throw new IllegalArgumentException("This RedBlackTree already contains that value.");
		// store newNode within left subtree of subtree
		else if (compare < 0) {
			if (subtree.leftChild == null) { // left subtree empty, add here
				subtree.leftChild = newNode;
				newNode.parent = subtree;
				enforceRBTreePropertiesAfterInsert(newNode);
				// otherwise continue recursive search for location to insert
			} else
				insertHelper(newNode, subtree.leftChild);
		}
		// store newNode within the right subtree of subtree
		else {
			if (subtree.rightChild == null) { // right subtree empty, add here
				subtree.rightChild = newNode;
				newNode.parent = subtree;
				enforceRBTreePropertiesAfterInsert(newNode);
				// otherwise continue recursive search for location to insert
			} else
				insertHelper(newNode, subtree.rightChild);
		}
	}

	private void enforceRBTreePropertiesAfterInsert(Node<T> newNode) {
		if (!newNode.isBlack && !newNode.parent.isBlack) { // checks for a double red
			if (newNode.parent.isLeftChild()) { // if the parent is a left child
				if (newNode.isLeftChild()) { // if the newNode is a left child
					if (newNode.parent.parent.rightChild == null // If the uncle node is null
							|| newNode.parent.parent.rightChild.isBlack) {// OR if the uncle node is black
						newNode.parent.isBlack = true;
						newNode.parent.parent.isBlack = false;
						rotate(newNode.parent, newNode.parent.parent);
						return;
					}
					if (!newNode.parent.parent.rightChild.isBlack) { // if the uncle node is red
						newNode.parent.isBlack = true;
						newNode.parent.parent.rightChild.isBlack = true;
						if (newNode.parent.parent != root) { // if the grandparent isn't the root it is recolored
							newNode.parent.parent.isBlack = false;
						}
						return;
					}
				} else if (newNode == newNode.parent.rightChild) { // if the newNode is a right child
					if (newNode.parent.parent.rightChild == null // If the uncle node is null
							|| newNode.parent.parent.rightChild.isBlack) {// OR if the uncle node is black
						newNode.isBlack = true;
						newNode.parent.parent.isBlack = false;
						// rotate(newNode, newNode.parent);
						rotate(newNode.parent, newNode.parent.parent);
						return;
					}
					if (!newNode.parent.parent.rightChild.isBlack) { // if the uncle node is red
						newNode.parent.isBlack = true;
						newNode.parent.parent.rightChild.isBlack = true;
						if (newNode.parent.parent != root) { // if the grandparent isn't the root it is recolored
							newNode.parent.parent.isBlack = false;
						}
						return;
					}
				}
			} else if (newNode.parent == newNode.parent.parent.rightChild) { // if the parent is a right child
				if (newNode.isLeftChild()) { // if newNode is the left child
					if (newNode.parent.parent.leftChild == null // If the uncle node is null
							|| newNode.parent.parent.leftChild.isBlack) {// OR if the uncle node is black
						newNode.isBlack = true;
						newNode.parent.parent.isBlack = false;
						// rotate (newNode, newNode.parent);
						rotate(newNode.parent, newNode.parent.parent);
						return;
					}
					if (!newNode.parent.parent.leftChild.isBlack) { // if the uncle node is red
						newNode.parent.isBlack = true;
						newNode.parent.parent.leftChild.isBlack = true;
						if (newNode.parent.parent != root) { // if the grandparent isn't the root it is recolored
							newNode.parent.parent.isBlack = false;
						}
						return;
					}
				} else if (newNode == newNode.parent.rightChild) { // if newNode is the right child
					if (newNode.parent.parent.leftChild == null // If the uncle node is null
							|| newNode.parent.parent.leftChild.isBlack) {// OR if the uncle node is black
						newNode.parent.isBlack = true;
						newNode.parent.parent.isBlack = false;
						rotate(newNode.parent, newNode.parent.parent);
						return;
					}
					if (!newNode.parent.parent.leftChild.isBlack) { // if the uncle node is red
						newNode.parent.isBlack = true;
						newNode.parent.parent.leftChild.isBlack = true;
						if (newNode.parent.parent != root) { // if the grandparent isn't the root it is recolored
							newNode.parent.parent.isBlack = false;
						}
						return;
					}
				}
			}
		}
	}

	/**
	 * This method performs a level order traversal of the tree. The string
	 * representations of each data value within this tree are assembled into a
	 * comma separated string within brackets (similar to many implementations of
	 * java.util.Collection, like java.util.ArrayList, LinkedList, etc).
	 * 
	 * @return string containing the values of this tree in level order
	 */
	@Override
	public String toString() {
		return root.toString();
	}

	private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {
		if (child.isLeftChild()) {
			parent.leftChild = child.rightChild;
			if (parent.parent == null) {
				root = child;
			} else if (parent.parent.leftChild == parent) {
				parent.parent.leftChild = child;
			} else {
				parent.parent.rightChild = child;
			}
			child.rightChild = parent;
		} else if (child == parent.rightChild) {
			parent.rightChild = child.leftChild;
			if (parent.parent == null) {
				root = child;
			} else if (parent.parent.leftChild == parent) {
				parent.parent.leftChild = child;
			} else {
				parent.parent.rightChild = child;
			}
			child.leftChild = parent;
		} else {
			throw new IllegalArgumentException("These two nodes are not initially related!");
		}
	}

	public String getName(T data) {
		if (data == null)
			throw new IllegalArgumentException("Argument cannot be null!!");
		return getName(root, data);
	}

	public int getYear(T data) {
		if (data == null)
			throw new IllegalArgumentException("Argument cannot be null");
		return getYear(root, data);
	}

	public int getExam1(T data) {
		if (data == null)
			throw new IllegalArgumentException("Argument cannot be null");
		return getExam1(root, data);
	}

	public int getExam2(T data) {
		if (data == null)
			throw new IllegalArgumentException("Argument cannot be null");
		return getExam2(root, data);
	}

	public int getExam3(T data) {
		if (data == null)
			throw new IllegalArgumentException("Argument cannot be null");
		return getExam3(root, data);
	}

	private String getName(Node node, T data) {
		if (data == null)
			throw new IllegalArgumentException("Argument cannot be null");
		while (node != null) {
			int c = data.compareTo((T) node.data);
			if (c < 0)
				node = node.leftChild;
			else if (c > 0)
				node = node.rightChild;
			else
				return node.name;
		}
		return "Invalid ID number";
	}

	private int getYear(Node node, T data) {
		while (node != null) {
			int c = data.compareTo((T) node.data);
			if (c < 0)
				node = node.leftChild;
			else if (c > 0)
				node = node.rightChild;
			else
				return node.year;
		}
		return -1;
	}

	private int getExam1(Node node, T data) {
		while (node != null) {
			int c = data.compareTo((T) node.data);
			if (c < 0)
				node = node.leftChild;
			else if (c > 0)
				node = node.rightChild;
			else
				return node.exam1;
		}
		return -1;
	}

	private int getExam2(Node node, T data) {
		while (node != null) {
			int c = data.compareTo((T) node.data);
			if (c < 0)
				node = node.leftChild;
			else if (c > 0)
				node = node.rightChild;
			else
				return node.exam2;
		}
		return -1;
	}

	private int getExam3(Node node, T data) {
		while (node != null) {
			int c = data.compareTo((T) node.data);
			if (c < 0)
				node = node.leftChild;
			else if (c > 0)
				node = node.rightChild;
			else
				return node.exam3;
		}
		return -1;
	}
}
