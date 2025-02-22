package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerDao {
	
	public boolean getCustomerBtId(int id) {
		Customer customer;
		String query= "SELECT * FROM im_customer WHERE id = ?";
		try(Connection con= DBConnect.getConnection();
				PreparedStatement pstmt=con.prepareStatement(query)){
			pstmt.setInt(1, id);
			ResultSet resultSet= pstmt.executeQuery();
			if(resultSet.next())
			{
				customer = new Customer(id, resultSet.getString("name"), resultSet.getString("mobile"), resultSet.getString("email"));
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public ObservableList<Customer> getCustomerList(){
		ObservableList<Customer> customerList=FXCollections.observableArrayList();
		String query = "SELECT * FROM im_customer";
		try(Connection con= DBConnect.getConnection();
				PreparedStatement pstmt=con.prepareStatement(query)){
			ResultSet rs= pstmt.executeQuery();
			Customer customer;
			while(rs.next()) {
				customer = new Customer(rs.getInt("id"), rs.getString("name"), rs.getString("mobile"), rs.getString("email"));
				customerList.add(customer);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customerList;
	}
	
	public boolean addCustomer(int id, String name, String mobile, String email) {
		String query= "INSERT INTO im_customer(id, name, mobile, email) VALUES (?,?,?,?)";
		try(Connection con = DBConnect.getConnection();
				PreparedStatement pstmt= con.prepareStatement(query)){
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, mobile);
			pstmt.setString(4, email);
			
			int result = pstmt.executeUpdate();
			return result>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean updateCustomer(int id, String name, String mobile, String email) {
		String query= "UPDATE im_customer SET name = ?, mobile =?, email =? WHERE id= ?";
		try(Connection con = DBConnect.getConnection();
				PreparedStatement pstmt= con.prepareStatement(query)){
			
			pstmt.setString(1, name);
			pstmt.setString(2, mobile);
			pstmt.setString(3, email);
			pstmt.setInt(4, id);
			
			
			int result = pstmt.executeUpdate();
			return result>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean deleteCustomer(int id) {
		String query= "DELETE FROM im_customer WHERE id= ?";
		try(Connection con = DBConnect.getConnection();
				PreparedStatement pstmt= con.prepareStatement(query)){			
			pstmt.setInt(1, id);			
			int result = pstmt.executeUpdate();
			return result>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	

}
