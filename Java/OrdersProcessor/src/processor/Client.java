package processor;

import java.io.BufferedWriter;
import java.util.ArrayList;

public class Client implements Runnable{
	private int id;
	private int numOrders;
	private BufferedWriter writer;
	ArrayList<Item> listOfItems;
	public Client(int id, int numOrders) {
		this.id=id;
		this.numOrders=numOrders;
	}
	public void placeOrder(Item i) {
		numOrders++;
	}
	public int getNumOrders() {
		return numOrders;
	}
	public int getClientId() {
		return id;
	}
	public void setNumOrders(Item i) {}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
