package model;

import java.util.ArrayList;

public class ListElement extends TagElement implements Element {
	private boolean ordered;
	private String attributes;
	private ArrayList<Element> aList;

	/**
	 * Constructor: calls the super class and checks to make sure attributes is not
	 * null the default tagName is "ul" but it can be set to "ol" initializes all
	 * instance vars
	 */
	public ListElement(boolean ordered, String attributes) {
		super("ul", attributes, false, null);
		this.ordered = ordered;
		if (ordered) {
			this.tagName = "ol";
		}
		if (attributes == null || attributes.length() == 0) {
			this.attributes = "";
		} else if (attributes != null) {
			this.attributes = attributes;
		}

		aList = new ArrayList<Element>();
	}

	/**
	 * method adds a new element item to the current ArrayList of elements
	 */
	public void addItem(Element item) {

		aList.add(item);
	}

	/**
	 * method return a string representation of the list element important to check
	 * whether or not the list element has an ID also check if the list is ordered
	 * or not
	 */
	@Override
	public String genHTML(int indentation) {
		// TODO Auto-generated method stub
		String finalText = "";
		finalText += Utilities.spaces(1);
		if (ordered) {
			finalText += "<ol ";
			if (this.getEnableId() == true) {
				finalText += " id=\"" + getStringId() + "\"";
				finalText += this.attributes + ">" + "\n";
			} else if (this.getEnableId() == false) {
				finalText += " " + this.attributes + ">" + "\n";
			}
		} else {
			finalText += "<ul ";
			if (this.getEnableId() == true) {
				finalText += " id=\"" + getStringId() + "\"";
				finalText += this.attributes + ">" + "\n";
			} else if (this.getEnableId() == false) {
				finalText += " " + this.attributes + ">" + "\n";
			}
		}
		for (Element temp : aList) {// this loop adds elemets from our arraylist to the final text
			finalText += Utilities.spaces(2) + "<li>\n";
			String tempText = temp.genHTML(indentation);
			String[] tempStringLines = tempText.split("\n");
			for (int i = 0; i < tempStringLines.length; i++) {

				if (temp != null) {
					finalText += Utilities.spaces(2) + tempStringLines[i] + "\n" + Utilities.spaces(2) + "</li>\n";
				} else {
					finalText += "";
				}
			}
		}

		if (ordered) {
			finalText += "</ol" + ">\n";
		} else {
			finalText += "</ul" + ">\n";
		}
		return finalText;
	}

}
