package model;

public class HeadingElement extends TagElement implements Element {

	int level;
	Element content;
	private String attributes;

	/**
	 * Constructor: calls the super class and checks to make sure attributes is not
	 * null initializes all instance vars
	 */
	public HeadingElement(Element content, int level, String attributes) {
		super("h", attributes, true, content);
		if (attributes == null) {
			this.attributes = "";
		} else if (attributes != null) {
			this.attributes = attributes;
		}
		this.level = level;
		this.content = content;
	}

	/**
	 * method return a string representation of the heading element. Taking into
	 * account the heading level
	 */
	@Override
	public String genHTML(int indentation) {

		return getStartTag() + level + ">" + content.genHTML(indentation) + "</h" + level + ">";

	}

}
