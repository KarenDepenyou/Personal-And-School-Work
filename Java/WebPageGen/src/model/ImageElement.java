package model;

public class ImageElement extends TagElement implements Element {

	private String alt, imageURL, attributes;
	private int width, height;

	/**
	 * Constructor: calls the super class and checks to make sure attributes is not
	 * null initializes all instance vars
	 */
	public ImageElement(String imageURL, int width, int height, String alt, String attributes) {
		super("img", attributes, false, null);
		if (attributes == null) {
			attributes = "";
		} else if (attributes != null) {
			this.attributes = attributes;
		}
		this.imageURL = imageURL;
		this.width = width;
		this.height = height;
		this.alt = alt;

	}

	/**
	 * method return the image url
	 */
	public String getImageURL() {
		return this.imageURL;
	}

	/**
	 * method return a string representation of the image element important to check
	 * whether or not the image element has an ID
	 */
	public String genHTML(int indentation) {
		// TODO Auto-generated method stubtem
		String finalText = "\n";
		if (this.getEnableId() == true) {
			finalText += "<img id=\"img" + getId() + "\" src=\"" + getImageURL() + "\" width=\"" + this.width
					+ "\" height=\"" + this.height + "\" alt=\"" + this.alt + "\">";

		} else if (this.getEnableId() == false) {
			finalText += "<img src=\"" + getImageURL() + "\" width=\"" + this.width + "\" height=\"" + this.height
					+ "\" alt=\"" + this.alt + "\">";

		}

		return finalText;

	}

}
