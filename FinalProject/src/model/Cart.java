package model;

public class Cart {
	private int id;
	private String pname;
	private int quantity;
	private int price;
	private String description;
	private int subtotal;
	public Cart(int id, String pname, int quantity, int price, String description, int subtotal) {
		super();
		this.id = id;
		this.pname = pname;
		this.quantity = quantity;
		this.price = price;
		this.description = description;
		this.subtotal = subtotal;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(int subtotal) {
		this.subtotal = subtotal;
	}
	

}
