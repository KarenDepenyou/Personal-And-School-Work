package listClasses;

import java.util.Comparator;

public class SortedLinkedList<T> extends BasicLinkedList<T> {
	protected Comparator<T> comparator;

	public SortedLinkedList(Comparator<T> comparator) {
		super();
		this.comparator = comparator;

	}

	/**
	 * added elements to the linked list so that they are sorted. 
	 * use comparator to make sure you insert elements at the correct position
	 * @param T element
	 * @return SortedlinkedList*/
	public SortedLinkedList<T> add(T element) {
		Node toAdd = new Node(element);
		
		/*list is empty so set head to new node*/
		if (head == null) {	
			head = toAdd;
			tail = head;
			size++;
			return this;
		}
		
		/*adds to the front of the list */
		else if (comparator.compare(element, head.data) < 0) {
			toAdd.next = head;
			head = toAdd;
			size++;
			return this;
		}
		Node current = head;
		Node aft = head.next;
		/*Adds to middle of list*/
		while (current.next != null) {
			if (comparator.compare(element, current.next.data) < 0) {
				break;
			}
			current = aft;
			aft = aft.next;
		}
		/*adds to end of list */
		toAdd.next = current.next;
		current.next = toAdd;
		tail = toAdd;
		size++;
		return this;
	}

	public SortedLinkedList<T> remove(T targetData) {
		super.remove(targetData, comparator);
		return this;
	}

	public BasicLinkedList<T> addToEnd(T data) {
		throw new UnsupportedOperationException
		("Invalid operation for sorted list.");
	}

	public BasicLinkedList<T> addToFront(T data) {
		throw new UnsupportedOperationException
		("Invalid operation for sorted list.");

	}


}
