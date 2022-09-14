package model;

import java.util.ArrayList;

public class ParagraphElement extends TagElement implements Element {
	private ArrayList<Element> aPara;
	public String attributes;

	/**
	 * Constructor: calls the super class and checks to make sure attributes is not
	 * null initializes all instance vars
	 */
	public ParagraphElement(String attributes) {
		super("p", attributes, true, null);
		if (attributes == null) {
			this.attributes = " ";
		} else if (attributes != null) {
			this.attributes = attributes;
		}
		aPara = new ArrayList<Element>();

	}

	/**
	 * method adds a new element item to the current ArrayList of elements
	 */
	public void addItem(Element item) {
		aPara.add(item);
	}

	/**
	 * method return a string representation of the paragraph element important to
	 * check whether or not the paragraph element has an ID
	 */
	@Override
	public String genHTML(int indentation) {
		// TODO Auto-generated method stub
		String finalText = "\n";
		if (this.getEnableId() == true) {
			finalText += "<" + this.tagName + " id=\"" + getStringId() + "\"";
			finalText += this.attributes + ">" + "\n";
		} else if (this.getEnableId() == false) {
			finalText += "<" + this.tagName;
			finalText += " " + this.attributes + ">" + "\n";
		}

		for (Element temp : aPara) {// this loop adds elemets from our arraylist to the final text
			finalText += temp.genHTML(indentation) + "\n";

		}
		finalText += getEndTag();
		return finalText;
	}

}
