package model;

public class OrderDetails {
	private String orderId;
	private int customerId;
	private String orderDate;
	private int totalPaid;
	public OrderDetails( String orderId, int customerId, String orderDate, int totalPaid) {
		
		this.orderId = orderId;
		this.customerId = customerId;
		this.orderDate = orderDate;
		this.totalPaid = totalPaid;
	}
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public int getTotalPaid() {
		return totalPaid;
	}
	public void setTotalPaid(int totalPaid) {
		this.totalPaid = totalPaid;
	}
	

}
