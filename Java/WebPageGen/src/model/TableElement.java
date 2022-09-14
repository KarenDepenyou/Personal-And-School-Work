package model;

//import java.util.ArrayList;

public class TableElement extends TagElement implements Element {
	private int cols, rows;
	private String attributes;
	Element[][] aTable;

	/**
	 * Constructor: calls the super class and checks to make sure attributes is not
	 * null initializes all instance vars
	 */
	public TableElement(int cols, int rows, String attributes) {
		super("table", attributes, true, null);
		if (attributes == null || attributes.length() == 0) {
			this.attributes = "";
		} else {
			this.attributes = attributes;
		}
		aTable = new Element[rows][cols];
		this.cols = cols;
		this.rows = rows;
	}

	/**
	 * method adds a new element item to the current 2D of elements
	 */
	public void addItem(int rowIndex, int colIndext, Element item) {
		aTable[rowIndex][colIndext] = item;
	}

	/**
	 * method returns the percentage of tables being used
	 */
	public double getTableUtilization() {
		double percent = 0;
		double sum = 0;
		for (int i = 0; i < aTable.length; i++) {// iterates through all rows to check if its empty or not
			for (int j = 0; j < aTable[i].length; j++) {// itertates through all the cols to check if its empty or no
				if (aTable[i][j] != null) {// double check that the current isn't empty/null
					sum++;
				}
			}
			percent = (sum / (rows * cols)) * 100;

		}
		return percent;
	}

	/**
	 * method return a string representation of the table element important to check
	 * whether or not the table element has an ID
	 */
	@Override
	public String genHTML(int indentation) {

		String finalText = "\n";
		if (this.getEnableId() == true) {
			finalText += "<" + this.tagName + " id=\"" + getStringId() + "\"";
			finalText += this.attributes + ">" + "\n";
		} else if (this.getEnableId() == false) {
			finalText += "<" + this.tagName;
			finalText += " " + this.attributes + ">" + "\n";
		}
		for (int i = 0; i < rows; i++) {
			finalText += "<tr>";
			for (int j = 0; j < cols; j++) {
				Element temp = aTable[i][j];
				if (temp != null) {// make sure the table space wasn't empty or null
					finalText += "<td>" + temp.genHTML(indentation) + "</td>";
				} else {
					finalText += "<td></td>";
				}
			}
			finalText += "</tr>\n";

		}

		finalText += getEndTag();
		return finalText;
	}

}
