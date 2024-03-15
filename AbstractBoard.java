
public abstract class AbstractBoard implements BoardInterface {

	Item[] items; //full item set that a Xianqi game contains

	public Item[] getItems() {
		return items;
	}

	public void setItems(Item[] items) {
		this.items = items;
	}

}
