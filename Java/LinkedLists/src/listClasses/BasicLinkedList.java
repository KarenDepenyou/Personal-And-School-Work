package listClasses;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class BasicLinkedList<T> extends Object implements Iterable<T> {
	protected class Node {
		protected T data;
		protected Node next;

		protected Node(T data) {
			this.data = data;
			next = null;
		}
	}

	/* List head pointer and tail */
	protected Node head;
	protected Node tail;
	protected int size;

	/* Constructor creates empty linked list */
	public BasicLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

	/* return size of linked list */
	public int getSize() {
		return size;
	}

	/**
	 * adds the data to the front of Linked List must set data to a node then set
	 * head to that data and increase size
	 * 
	 * @param T data
	 * @return Linked List
	 */
	public BasicLinkedList<T> addToFront(T data) {
		Node toAdd = new Node(data);
		toAdd.next = head;
		head = toAdd;
		size++;
		return this;
	}

	/**
	 * adds the data to the end of Linked List must first check if head is null if
	 * so set data as head if head isn't null set a new node to head and while the
	 * node following that node isn't null then keep checking once it is null then
	 * set that node to tail and increase size
	 * 
	 * @param T data
	 * @return Linked List
	 */
	public BasicLinkedList<T> addToEnd(T data) {

		if (head == null) {
			head = new Node(data);
			size++;
		} else {
			Node current = head;
			while (current.next != null) {
				current = current.next;
			}
			size++;
			current.next = new Node(data);
			tail = current;
		}
		return this;
	}

	/* return first element */
	public T getFirst() {

		if (head == null) {
			return null;
		}
		return head.data;
	}

	/* return last element */
	public T getLast() {
		if (head == null) {
			return null;
		}
		Node current = head;
		while (current.next != null) {
			current = current.next;
		}
		tail = current;
		return tail.data;
	}

	/* deletes the first element in linked List(head) and returns it */
	public T retrieveFirstElement() {

		if (head == null) {
			return null;
		}
		T first = getFirst();
		head = head.next;
		size--;
		return first;
	}

	/* deletes the last element in linked List and returns it */
	public T retrieveLastElement() {

		if (head == null) {
			return null;
		}
		T last = getLast();
		Node current = head;
		Node prev = null;
		while (current.next != null) {
			prev = current;
			current = current.next;
		}
		prev.next = null;
		tail = prev;
		size--;

		return last;
	}

	/*
	 * Removes the given data from the linked list use the comparator to make sure
	 * the given data is a equal to a node once removed update the size of linked
	 * list
	 */
	public BasicLinkedList<T> remove(T targetData, Comparator<T> comparator) {
		Node current = head;
		Node remove = null;
		while (current != null) {

			if (comparator.compare(current.data, targetData) == 0) {
				if (current == head) {
					head = head.next;
					current = head;
					size--;
				} else if (current.data == tail.data) {
					tail = remove;
					remove.next = null;
//					size--;
				} else {
					remove.next = current.next;
					current = remove.next;
					size--;

				}

			} else {
				remove = current;
				current = current.next;

			}

		}
		tail = current;
		return this;
	}

	/*
	 * Reverse the linked list and returns the elements as an array rather than a
	 * linked list metod must be done recursively
	 */
	public ArrayList<T> getReverseArrayList() {
		ArrayList<T> answ = new ArrayList<T>();
		return getReverseArrayList(head, answ);
	}

	/*
	 * Helper method: takes in a node parameter and an array list check if current
	 * node is null if so return it the arraylist bc there is nothing to reverse. If
	 * the current node is not empty then add it to the front of the array and
	 * return a recursive call to the method
	 */
	private ArrayList<T> getReverseArrayList(Node current, ArrayList<T> list) {
		if (current == null) {
			return list;
		} else {
			list.add(0, current.data);
		}

		return getReverseArrayList(current.next, list);
	}

	/* returns a reverse of the linked list */
	public BasicLinkedList<T> getReverseList() {
		return getReverseList(head);
	}

	/*
	 * Helper method: takes in a node parameter check if current node is null if so
	 * return it the basiclinkedlist bc there is nothing to reverse. If the current
	 * node is not empty then check for curren.next if so switch .next and head, set
	 * tail to null, and return this then make a recursive call to method
	 */
	private BasicLinkedList<T> getReverseList(Node current) {
		if (current == null) {
			return this;
		}
		if (current.next == null) {
			head = current;
			tail = null;
			return this;
		}
		getReverseList(current.next);
		current.next.next = current;
		current.next = null;
		return this;

	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new Iterator<T>() {
			Node current = head;

			@Override
			// if current is null there is no .next else there is
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return current != null;
			}

			@Override
			// if there is a .next then return it
			public T next() {
				// TODO Auto-generated method stub
				if (hasNext()) {
					T data = current.data;
					current = current.next;
					return data;
				}
				return null;
			}

			public void remove() {
				throw new UnsupportedOperationException
				("There is no remove method");
			}

		};
	}


	public String toString() {
		Node current = head;
		String asnw = "";
		while (current != null) {
			asnw += current.data + ";";
			current = current.next;
		}
		return asnw;
	}

}
