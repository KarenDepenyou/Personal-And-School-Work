package model;

public class TagElement implements Element {

	protected String tagName;
	private String attributes;
	private boolean endTag;
	private static boolean setId = false;
	private static int id = 0;
	private int id2;
	Element content;

	/**
	 * Constructor: describes what an element is initializes all instance vars
	 */
	public TagElement(String tagName, String attributes, boolean endTag, Element content) {
		this.tagName = tagName;
		this.attributes = attributes;
		this.endTag = endTag;
		this.content = content;
		id++;

		id2 = id;

	}

	/**
	 * method returns whether the specific element will have ids
	 */
	public static void enableId(boolean choice) {
		setId = choice;
	}

	/**
	 * method returns String representation of endtag
	 */
	public String getEndTag() {
		if (endTag) {
			return "</" + tagName + ">";
		} else {
			return " ";
		}
	}

	/**
	 * method returns String representation of starttag important to check whether
	 * or not the id should be included must also check if the attribute is null or
	 * not
	 */
	public String getStartTag() {
		if (setId == true) {
			if (attributes == null) {
				attributes = " ";
			} else if (attributes != null) {
				this.setAttributes(attributes);
			}

			return "<" + tagName + " id = \"" + getStringId() + "\" " + attributes;
		} else {
			if (attributes == null) {
				attributes = " ";
			} else if (attributes != null) {
				this.setAttributes(attributes);
			}
			return "<" + tagName + " " + attributes;
		}
	}

	public int getId() {// return id
		return id2;
	}

	public static void resetIds() {// reset id
		id = 0;
	}

	public String getStringId() {// return string representation of id w/ tagname
		return tagName + id2;
	}

	public void setAttributes(String attributes) {// set attribute
		this.attributes = attributes;
	}

	// methods lets children classes check whether or not enable id is true
	public boolean getEnableId() {
		return setId;
	}

	/**
	 * method return a string representation of an element
	 */
	@Override
	public String genHTML(int indentation) {
		// TODO Auto-generated method stub
		return getStartTag() + content + getEndTag();
	}

}
