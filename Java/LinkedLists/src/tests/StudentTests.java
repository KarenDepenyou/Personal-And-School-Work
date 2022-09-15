package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;

import org.junit.Test;

import listClasses.*;
public class StudentTests {
	BasicLinkedList<String> aList= new BasicLinkedList<String>();
	SortedLinkedList<String> theList = new SortedLinkedList<String>(Comparator.naturalOrder());


	@Test
	public void testConstructor() {
		assertEquals(0,aList.getSize());
		assertEquals(null,aList.getFirst());
		assertEquals(null,aList.getLast());
	}
	@Test
	public void testAddToFrontOrEnd() {
		aList.addToFront("karen");
		aList.addToFront("honor");
		aList.addToEnd("leila");
		assertEquals("honor",aList.getFirst());
		assertEquals("leila", aList.getLast());
		assertEquals(3,aList.getSize());
	}
	@Test
	public void testRetrieveFirstElement(){
		aList.addToFront("karen");
		aList.addToFront("honor");
		aList.addToEnd("leila");
		aList.retrieveFirstElement();
		assertEquals("karen", aList.getFirst());
		assertEquals(2,aList.getSize());
	}
	@Test
	public void testRetrieveLastElement() {
		aList.addToFront("karen");
		aList.addToFront("honor");
		aList.addToEnd("leila");
		assertEquals("leila",aList.retrieveLastElement());
//		aList.retrieveLastElement();
		assertEquals("karen", aList.getLast());
		assertEquals(2,aList.getSize());
	}
	@Test
	public void testRemove() {
		aList.addToFront("k");
		aList.addToFront("e");
		aList.addToFront("r");
		aList.addToFront("a");
		aList.addToFront("a");
		aList.remove("a", Comparator.naturalOrder());
		assertEquals("r",aList.getFirst());
		assertEquals("k",aList.getLast());
		assertEquals(3,aList.getSize());
		assertEquals("k",aList.retrieveLastElement());
		assertEquals("r",aList.retrieveFirstElement());
		assertEquals("e",aList.retrieveFirstElement());
		assertEquals(0,aList.getSize());




	}
	
	@Test
	public void testReverseList() {
		aList.addToFront("karen");
		aList.addToFront("honor");
		aList.addToFront("leila");
		aList.addToFront("grace");
		aList.getReverseList();
		assertEquals("grace",aList.getLast());
		assertEquals("karen",aList.getFirst());
	}
	@Test
	public void testReverseArrayList() {
		ArrayList<String> ans= new ArrayList<String>();
		ans.add("karen");//first element of array
		ans.add("honor");
		ans.add("leila");
		ans.add("grace");//last element of array
		aList.addToFront("karen");//last element of linked list
		aList.addToFront("honor");
		aList.addToFront("leila");
		aList.addToFront("grace");//first element of linked list
		assertEquals(aList.getReverseArrayList(),ans);
		
	}
	@Test
	public void testAddSorted() {
		theList.add("banana");
		theList.add("apple");
		theList.add("carrot");
		theList.add("banana");
//		theList.retrieveFirstElement();
		assertEquals("apple",theList.getFirst());

		assertEquals(4,theList.getSize());
		assertEquals("apple",theList.getFirst());
		assertEquals("carrot",theList.getLast());

	}
	@Test
	public void testRemoveSorted() {
		theList.add("banana");
		theList.add("apple");
		theList.add("carrot");
		theList.add("banana");
		theList.remove("banana");
		assertEquals(2,theList.getSize());
	}
	@Test
	public void testIterator() {
		aList.addToFront("karen");
		aList.addToFront("honor");
		aList.addToFront("leila");
		aList.addToFront("grace");
		for(String s: aList) {
			System.out.println(s);
		}
	}

}
