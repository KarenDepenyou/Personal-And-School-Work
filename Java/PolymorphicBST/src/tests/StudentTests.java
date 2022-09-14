package tests;

import java.util.HashSet;
import java.util.Set;

//import static org.junit.Assert.fail;

import org.junit.Test;

import junit.framework.TestCase;
import tree.PlaceKeysValuesInArrayLists;
import tree.PolymorphicBST;

public class StudentTests extends TestCase {
	PolymorphicBST<Integer,String> theTree= new PolymorphicBST<Integer, String>();
	
	@Test
	public void testSearch() {
		theTree.put(1, "one");
		theTree.put(2, "two");
		theTree.put(3, "three");
		theTree.put(4, "four");
		assertEquals(4, theTree.size());
		assertEquals("three", theTree.get(3));
		assertEquals("four", theTree.get(4));
	}
	@Test
	public void testInsert() {
		theTree.put(1, "one");
		theTree.put(2, "two");
		theTree.put(3, "three");
		theTree.put(4, "four");
		assertEquals(Integer.valueOf(4), theTree.getMax());
		assertEquals(Integer.valueOf(1), theTree.getMin());


	}
	@Test
	public void testRemove() {
		theTree.put(4, "four");
		theTree.put(2, "two");
		theTree.put(5, "five");
		theTree.put(3, "three");
		theTree.put(6, "six");
		theTree.put(1, "one");
		assertEquals(6, theTree.size());
		assertEquals(Integer.valueOf(6), theTree.getMax());
		assertEquals(Integer.valueOf(1), theTree.getMin());
		theTree.remove(6);
		theTree.remove(1);
		assertEquals(4, theTree.size());
		assertEquals(Integer.valueOf(5), theTree.getMax());
		assertEquals(Integer.valueOf(2), theTree.getMin());



	}
	@Test
	public void testKeySet() {
		Set<Integer> theSet= new HashSet<Integer>();
		theSet.add(4); theSet.add(2); theSet.add(5);
		theSet.add(3); theSet.add(6); theSet.add(1);
		theTree.put(4, "four");
		theTree.put(2, "two");
		theTree.put(5, "five");
		theTree.put(3, "three");
		theTree.put(6, "six");
		theTree.put(1, "one");
		assertEquals(theSet, theTree.keySet());
	}
	@Test
	public void testSubMapandInOrder() {
		PolymorphicBST<Integer,String> theTree2= new PolymorphicBST<Integer, String>();
		theTree2.put(1, "one");
		theTree2.put(2, "two");
		theTree2.put(3, "three");
		theTree2.put(4, "four");
		theTree.put(1, "four");
		theTree.put(2, "two");
		theTree.put(3, "five");
		theTree.put(4, "three");
		theTree.put(5, "six");
		theTree.put(6, "one");
		PlaceKeysValuesInArrayLists<Integer, String> task = new PlaceKeysValuesInArrayLists<Integer, String>();
		PlaceKeysValuesInArrayLists<Integer, String> task2 = new PlaceKeysValuesInArrayLists<Integer, String>();
		theTree.subMap(1, 4).inorderTraversal(task);;
		theTree2.inorderTraversal(task2);
		assertEquals(task.getKeys().toString(), "[1, 2, 3, 4]");
		assertEquals(task2.getKeys().toString(), "[1, 2, 3, 4]");
		assertEquals(task.getKeys().toString(),task2.getKeys().toString());

	}
	@Test
	public void testClear() {
		theTree.put(1, "four");
		theTree.put(2, "two");
		theTree.put(3, "five");
		theTree.put(4, "three");
		theTree.put(5, "six");
		theTree.put(6, "one");
		assertEquals(6, theTree.size());
		theTree.clear();
		assertEquals(0, theTree.size());
	}
	@Test
	public void testHeight() {
		theTree.put(4, "four");
		theTree.put(2, "two");
		theTree.put(5, "five");
		theTree.put(3, "three");
		theTree.put(6, "six");
		theTree.put(1, "one");
		assertEquals(3, theTree.height());
		theTree.put(7, "six");
		theTree.put(8, "six");
		assertEquals(5, theTree.height());

	}
	@Test
	public void testRightLeftTraversal() {
		theTree.put(4, "four");
		theTree.put(2, "two");
		theTree.put(5, "five");
		theTree.put(3, "three");
		theTree.put(6, "six");
		theTree.put(1, "one");
		PlaceKeysValuesInArrayLists<Integer, String> task = new PlaceKeysValuesInArrayLists<Integer, String>();
		theTree.rightRootLeftTraversal(task);
		assertEquals(task.getKeys().toString(), "[5, 6, 4, 1, 2, 3]");


	}
	
	
	
	
}