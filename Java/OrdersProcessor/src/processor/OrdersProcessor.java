package processor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
//import java.io.File;
import java.util.Scanner;
import java.util.TreeMap;
//import java.io.FileNotFoundException;

public class OrdersProcessor implements Runnable {

	private Map<String, Double> pricesMap;
	private ArrayList<String> entry;
	private Map<String, Integer> theMap;
	private ArrayList<Thread> threadList;

	public OrdersProcessor() {
		this.pricesMap = new HashMap<String, Double>();
		this.entry = new ArrayList<String>();
		this.theMap = new TreeMap<String, Integer>();
		this.threadList = new ArrayList<Thread>();
	}

	public void processFiles(String s) {
		Map<String, Integer> mp = new TreeMap<String, Integer>();
		int clientId = 0;
		try {
			String info = null;
			File file = new File(s);
			BufferedReader bReader = new BufferedReader(new FileReader(file));
			String line1 = bReader.readLine();
			clientId = Integer.parseInt(line1.split(" ")[1]);
//			System.out.println(clientId+" client");
			while ((info = bReader.readLine()) != null) {
//				String itemName= info.substring(0, info.indexOf(" "));
				String[] info2 = info.split(" ");
//				System.out.println(info2[1]);
				if (!(mp.containsKey(info2[0]))) {
					mp.put(info2[0], 1);
				} else {
					mp.put(info2[0], mp.get(info2[0]) + 1);
				}

			}
		} catch (Exception e) {
		}
//		for(String k:mp.keySet()) {
//			System.out.println(k+" count "+mp.get(k));
//		}
		addToSummary(mp, clientId);

	}

	public void processDataFile(String s) {
		try {
			String info = null;
			File file = new File(s);
			BufferedReader bReader = new BufferedReader(new FileReader(file));
			while ((info = bReader.readLine()) != null) {
				String[] info2 = info.split(" ");
				Double price = Double.parseDouble(info2[1]);
				pricesMap.put(info2[0], price);

			}
		} catch (Exception e) {
		}

	}

	public void addToSummary(Map<String, Integer> tm, int id) {

		entry.add("----- Order details for client with Id: " + id + " -----\n");
		double orderTotal = 0;
		for (String k : tm.keySet()) {

			if (!(theMap.containsKey(k))) {
				theMap.put(k, tm.get(k));
			} else {
				theMap.put(k, theMap.get(k) + tm.get(k));
			}
			double price = pricesMap.get(k);
			int quan = tm.get(k);
			double cost = price * quan;
			orderTotal += cost;
			entry.add("Item's name: " + k + ", Cost per item: " + NumberFormat.getCurrencyInstance().format(price)
					+ ", Quantity: " + quan + ", Cost: " + NumberFormat.getCurrencyInstance().format(cost) + "\n");

		}
		entry.add("Order Total: " + NumberFormat.getCurrencyInstance().format(orderTotal) + "\n");

	}

	public void completeSummary() {
		entry.add("***** Summary of all orders *****\n");
		double grandTotal = 0;
		for (String k : theMap.keySet()) {
			double cost = pricesMap.get(k);
			int quan = theMap.get(k);
			double totalCost = cost * quan;
			grandTotal += totalCost;
			entry.add("Summary - Item's name: " + k + ", Cost per item: "
					+ NumberFormat.getCurrencyInstance().format(cost) + ", Number sold: " + quan + ", Item's Total: "
					+ NumberFormat.getCurrencyInstance().format(totalCost) + "\n");
		}
		entry.add("Summary Grand Total: " + NumberFormat.getCurrencyInstance().format(grandTotal) + "\n");
	}

	public void writeToFile(String s) {
		try {
			File file = new File(s);
			file.createNewFile();
			FileWriter writer = new FileWriter(file);

			for (int i = 0; i < entry.size(); i++) {
				writer.write(entry.get(i));

//				System.out.println(entry.get(i));
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		OrdersProcessor op = new OrdersProcessor();
		Scanner obj = new Scanner(System.in);
		System.out.println("Enter item's data file name: ");
		String dataFile = obj.nextLine();
		System.out.println("Enter 'y' for multiple threads, any other character otherwise: ");
		String ans = obj.nextLine();
		System.out.println("Enter number of orders to process: ");
		int num = Integer.parseInt(obj.nextLine());
		System.out.println("Enter order's base filename: ");
		String baseFileName = obj.nextLine();
		System.out.println("Enter result's filename: ");
		String resultFileName = obj.nextLine();

		// data file processing
		op.processDataFile(dataFile);

		// order files processing
		for (int i = 1; i <= num; i++) {
			String orderFileName = baseFileName + i + ".txt";
			op.processFiles(orderFileName);
		}
		op.completeSummary();

		op.writeToFile(resultFileName);

	}

	public void multiThread() {
		long start = System.currentTimeMillis();
		for (int i = 0; i < theMap.size(); i++) {
			Thread t = new Thread(this);
			threadList.add(t);
			t.start();
//			multiThreadArray.add(t);
		}
		try {
			for (Thread t : threadList) {
				t.join();
			}

		} catch (InterruptedException e) {
		}
		this.completeSummary();
		long end = System.currentTimeMillis();
		System.out.println("Process time(msec): " + (end - start));

	}

	public void singleThread() {
		long start = System.currentTimeMillis();
		Thread t = new Thread(this);
		for (int i = 0; i < theMap.size(); i++) {
			t = new Thread(this);
//			Thread t= new Thread(this);
//			threadList.add(t);
			t.start();
//			multiThreadArray.add(t);
			try {
				t.join();

			} catch (InterruptedException e) {
			}
		}
		this.completeSummary();
		long end = System.currentTimeMillis();
		System.out.println("Process time(msec): " + (end - start));

	}

	@Override
	public void run() {

	}

}
