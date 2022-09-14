package model;

import java.util.ArrayList;

public class WebPage extends Object implements Comparable<WebPage> {
	private String title;
	private static boolean setId;
	private ArrayList<Element> anElem;

	/**
	 * Constructor: describes what a text element is. initializes all instance vars
	 */
	public WebPage(String title) {
		this.title = title;
		anElem = new ArrayList<Element>();
	}

	/**
	 * method adds a new element item to the current ArrayList of elements if the
	 * element is a tagElement then return the id of set element else return -1;
	 */
	public int addElement(Element element) {
		anElem.add(element);
		if (element instanceof TagElement) {
			return ((TagElement) element).getId();
		} else {
			return -1;
		}
	}

	/**
	 * method return a string representation of a webpage
	 */
	public String getWebPageHTML(int indentation) {
		String spaces = Utilities.spaces(indentation);
		String finalText = "";
		finalText += "<!doctype html>\n" + "<html>\n" + "<head>\n" + spaces + "<meta charset=\"utf-8\"/>\n" + spaces
				+ "<title>" + title + "</title>\n" + "</head>\n" + "<body>\n";
		for (Element temp : anElem) {// this loop adds elemets from our arraylist to the final text
			finalText += temp.genHTML(indentation) + "\n";
		}
		finalText += "</body>\n" + "</html>";
		return finalText;
	}

	public void writeToFile(String filename, int indentation) {
		Utilities.writeToFile(filename, getWebPageHTML(indentation));
	}

	public Element findElem(int id) {
		for (int i = 0; i < anElem.size(); i++) {
			if (((TagElement) anElem.get(i)).getId() == id) {
				return anElem.get(i);
			}

		}
		return null;
	}

	/**
	 * method return a count of all the the tag elements in a string also returns
	 * the % of tables used lists, paragraphs, and tables all have a counter counter
	 * will increase if the current element is an instance of the specified tag
	 * element
	 */
	public String stats() {
		String results = "";
		int list = 0, para = 0, tables = 0;
		double percentUsed = 0.0;
		for (Element e : anElem) {// loops through current array of elements
			if (e instanceof ListElement) {
				list++;
			} else if (e instanceof ParagraphElement) {
				para++;
			} else if (e instanceof TableElement) {
				TableElement tableElem = (TableElement) e;
				tables++;
				if (percentUsed == 0) {
					percentUsed += tableElem.getTableUtilization();
				} else {
					percentUsed += tableElem.getTableUtilization();
				}
			}

		}
		percentUsed /= tables;
		results += "List Count: " + list + "\n" + "Paragraph Count: " + para + "\n" + "Table Count: " + tables + "\n"
				+ "TableElement Utilization: " + percentUsed;
		return results;
	}

	public static void enableId(boolean choice) {
		setId = choice;
	}

	// @Override
	public int compareTo(WebPage webPage) {
		return title.compareTo(webPage.title);// compare if the titles are the same

	}

}
