package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductDao {
	public boolean getProductById(int id) {
		Product product;
		String query= "SELECT * FROM im_product WHERE id = ?";
		try(Connection con= DBConnect.getConnection();
				PreparedStatement pstmt=con.prepareStatement(query)){
			pstmt.setInt(1, id);
			ResultSet resultSet= pstmt.executeQuery();
			if(resultSet.next())
			{
				product = new Product(id, resultSet.getString("name"), resultSet.getInt("quantity"), resultSet.getInt("price"), resultSet.getString("description"), resultSet.getString("category"));
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public ObservableList<Product> getProductList(){
		ObservableList<Product> categoryList=FXCollections.observableArrayList();
		String query = "SELECT * FROM im_product";
		try(Connection con= DBConnect.getConnection();
				PreparedStatement pstmt=con.prepareStatement(query)){
			ResultSet rs= pstmt.executeQuery();
			Product product;
			while(rs.next()) {
				product = new Product(rs.getInt("id"), rs.getString("name"), rs.getInt("quantity"),
						rs.getInt("price"), rs.getString("description"), rs.getString("category"));
				categoryList.add(product);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categoryList;
	}
	
	public boolean addProduct(int id, String name, int qty, int price, String desc, String category) {
		String query= "INSERT INTO im_product(id, name, quantity, price, description, category)"
				+ " VALUES (?,?,?,?,?,?)";
		try(Connection con = DBConnect.getConnection();
				PreparedStatement pstmt= con.prepareStatement(query)){
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setInt(3, qty);
			pstmt.setInt(4, price);
			pstmt.setString(5, desc);
			pstmt.setString(6, category);
			
			int result = pstmt.executeUpdate();
			return result>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean updateProduct(int id, String name, int qty, int price, String desc, String category) {
		String query= "UPDATE im_product SET name = ?, quantity = ?, price=?, description = ?, "
				+ "category = ? WHERE id= ?";
		try(Connection con = DBConnect.getConnection();
				PreparedStatement pstmt= con.prepareStatement(query)){
			
			pstmt.setString(1, name);
			pstmt.setInt(2, qty);
			pstmt.setInt(3, price);
			pstmt.setString(4, desc);
			pstmt.setString(5, category);
			pstmt.setInt(6, id);
			
			int result = pstmt.executeUpdate();
			return result>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean deleteProduct(int id) {
		String query= "DELETE FROM im_product WHERE id= ?";
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
	
	public ObservableList<String> getCategories(){
		ObservableList<String> categoryList=FXCollections.observableArrayList();
		String query = "SELECT name FROM im_category";
		try(Connection con= DBConnect.getConnection();
				PreparedStatement pstmt=con.prepareStatement(query)){
			ResultSet rs= pstmt.executeQuery();
			while(rs.next()) {
				String category = rs.getString("name");
				categoryList.add(category);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categoryList;
	}

}
