package model;

public class Customer {
	private int id;
	private String name;
	private String mobile;
	private String email;
	public Customer(int id, String name, String mobile, String email) {
		this.id = id;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
