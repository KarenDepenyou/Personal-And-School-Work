package tree;

import java.util.Collection;

/**
 * This class represents a non-empty search tree. An instance of this class
 * should contain:
 * <ul>
 * <li>A key
 * <li>A value (that the key maps to)
 * <li>A reference to a left Tree that contains key:value pairs such that the
 * keys in the left Tree are less than the key stored in this tree node.
 * <li>A reference to a right Tree that contains key:value pairs such that the
 * keys in the right Tree are greater than the key stored in this tree node.
 * </ul>
 * 
 */
public class NonEmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {

	/* Provide whatever instance variables you need */
	private K key;
	private V value;
	private Tree<K, V> left;
	private Tree<K, V> right;

	/**
	 * Only constructor we need.
	 * 
	 * @param key
	 * @param value
	 * @param left
	 * @param right
	 */
	public NonEmptyTree(K key, V value, Tree<K, V> left, Tree<K, V> right) {
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
	}

	public V search(K key) {

		if (this.key.compareTo(key) == 0) {
			return this.value;
		} else if (key.compareTo(this.key) > 0) {
			return this.right.search(key);
		}

		return this.left.search(key);

	}

	public NonEmptyTree<K, V> insert(K key, V value) {
		if (key.compareTo(this.key) > 0) {
			this.right = right.insert(key, value);
		} else if (key.compareTo(this.key) < 0) {
			this.left = left.insert(key, value);
		} else {
			this.value = value;
		}
		return this;
	}

	public Tree<K, V> delete(K key) {
		if (key.compareTo(this.key) < 0) {
			left = left.delete(key);
		} else if (key.compareTo(this.key) > 0) {
			right = right.delete(key);
		} else {
			try {
				K k;
				k=left.max();
				this.key=k;
				this.value = left.search(k);
				left = left.delete(k);

			} catch (TreeIsEmptyException e) {
				try {
					K k2;
					k2=right.min();
					this.key=k2;
					this.value=right.search(k2);
					right=right.delete(k2);
				}catch (TreeIsEmptyException j) {
					return EmptyTree.getInstance();
				}
				

			}

		}
		return this;

	}

	public K max() {
		try {
			K k = right.max();
			return k;
		} catch (TreeIsEmptyException e) {
			return key;
		}
	}

	public K min() {
		try {
			K k = left.min();
			return k;
		} catch (TreeIsEmptyException e) {
			return key;
		}
	}

	public int size() {
		int size = 0;
		size += left.size();
		size += right.size();
		return size + 1;
	}

	public void addKeysToCollection(Collection<K> c) {
		left.addKeysToCollection(c);
		c.add(key);
		right.addKeysToCollection(c);
	}

	public Tree<K, V> subTree(K fromKey, K toKey) {
		if (fromKey.compareTo(this.key) <= 0 && toKey.compareTo(this.key) >= 0) {
			Tree<K, V> t = new NonEmptyTree<K, V>(this.key, this.value, this.left.subTree(fromKey, toKey),
					this.right.subTree(fromKey, toKey));
			return t;
		} else if (fromKey.compareTo(this.key) > 0) {
			return right.subTree(fromKey, toKey);
		} else  {
			return left.subTree(fromKey, toKey);
		}
//		return this;
	}

	public int height() {
		int height = Math.max(left.height(), right.height());
		return height + 1;
	}

	public void inorderTraversal(TraversalTask<K, V> p) {
		left.inorderTraversal(p);
		p.performTask(key, value);
		right.inorderTraversal(p);
	}

	public void rightRootLeftTraversal(TraversalTask<K, V> p) {
		right.inorderTraversal(p);
		p.performTask(key, value);
		left.inorderTraversal(p);
	}
}