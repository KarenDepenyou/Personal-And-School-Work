package model;

public class AnchorElement extends TagElement implements Element {

	private String url, linkText, attributes;

	/**
	 * Constructor: calls the super class and checks to make sure attributes is not
	 * null initializes all instance vars
	 */
	public AnchorElement(String url, String linkText, String attributes) {
		super("a", attributes, true, null);
		if (attributes == null) {
			this.attributes = "";
		} else if (attributes != null) {
			this.attributes = attributes;
		}
		this.url = url;
		this.linkText = linkText;
		// this.attributes=attributes;
	}

	/**
	 * method return the url to be added to the anchor element
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * method return the text to be paired with the link
	 */
	public String getLinkText() {
		return this.linkText;
	}

	/**
	 * method return a string representation of the anchor element
	 */
	@Override
	public String genHTML(int indentation) {

		return getStartTag() + " href=\"" + this.url + "\">" + this.linkText + getEndTag();
	}

}
