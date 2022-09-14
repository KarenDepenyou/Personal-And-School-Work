package model;

public class TextElement extends Object implements Element {
	private String text;

	/**
	 * Constructor: describes what a text element is. initializes all instance vars
	 */
	public TextElement(String text) {
		this.text = text;
	}

	@Override
	public String genHTML(int indentation) {// return text
		// TODO Auto-generated method stubtem
		return text;
	}

}
