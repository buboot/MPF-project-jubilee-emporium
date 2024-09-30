package pages;

public class Items {
	
	private int price;
	private int stock;
	private String desc;
	
	public Items(int price, int stock, String desc) {
		this.price = price;
		this.stock = stock;
		this.desc = desc;
	}

	public int getPrice() {
		return price;
	}

	public int getStock() {
		return stock;
	}

	public String getDesc() {
		return desc;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}



}
