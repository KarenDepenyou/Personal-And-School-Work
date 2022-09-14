package processor;

import java.util.HashMap;
import java.util.Map;

public class Item {
	private String item;
	private long price;
	private Map<String, Long> items;//key is the item name and value is the price
	public Item(String item, long price) {
		this.item=item;
		this.price=price;
		items=new HashMap<String, Long>();
	}
	public String getItem() {
		return item;
	}
	public long getPrice() {
		return price;
	}
	public void addItem(String name, long price) {
		items.put(name, price);
	}

}
