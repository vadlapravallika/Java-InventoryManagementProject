package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Dao {
	public User getUserByUsername(String username) {
		User user=null;
		String query= "SELECT * FROM pravalli_user_tbl WHERE USERNAME = ?";
		try(Connection con= DBConnect.getConnection();
				PreparedStatement pstmt=con.prepareStatement(query)){
			pstmt.setString(1, username);
			ResultSet resultSet= pstmt.executeQuery();
			if(resultSet.next())
			{
				user = new User(username, resultSet.getString("PASSWORD"), resultSet.getString("EMAIL"));
				System.out.println("username");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	public boolean addUser(String username, String password, String email) {
		String query = "INSERT INTO pravalli_user_tbl(USERNAME, EMAIL, PASSWORD) VALUES (?, ?, ?)";
		try(Connection con= DBConnect.getConnection();
				PreparedStatement pstmt=con.prepareStatement(query)){
			pstmt.setString(1, username);
			pstmt.setString(2, email);
			pstmt.setString(3, password);	
			int result = pstmt.executeUpdate();
			return result>0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean getCategoryById(int id) {
		Category category;
		String query= "SELECT * FROM im_category WHERE id = ?";
		try(Connection con= DBConnect.getConnection();
				PreparedStatement pstmt=con.prepareStatement(query)){
			pstmt.setInt(1, id);
			ResultSet resultSet= pstmt.executeQuery();
			if(resultSet.next())
			{
				category = new Category(id, resultSet.getString("name"));
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public ObservableList<Category> getCategoryList(){
		ObservableList<Category> categoryList=FXCollections.observableArrayList();
		String query = "SELECT * FROM im_category";
		try(Connection con= DBConnect.getConnection();
				PreparedStatement pstmt=con.prepareStatement(query)){
			ResultSet rs= pstmt.executeQuery();
			Category category;
			while(rs.next()) {
				category = new Category(rs.getInt("id"), rs.getString("name"));
				categoryList.add(category);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categoryList;
	}
	
	public boolean addCategory(int id, String name) {
		String query = "INSERT INTO im_category(id, name) VALUES (?, ?)";
		try(Connection con= DBConnect.getConnection();
				PreparedStatement pstmt=con.prepareStatement(query)){
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			int result = pstmt.executeUpdate();
			return result>0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean updateCategory(int id, String name) {
		String query = "UPDATE im_category SET name = ? WHERE id = ?";
		try(Connection con= DBConnect.getConnection();
				PreparedStatement pstmt=con.prepareStatement(query)){
			
			pstmt.setString(1, name);
			pstmt.setInt(2, id);
			int result = pstmt.executeUpdate();
			return result>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean deleteCategory(int id) {
		String query="DELETE FROM im_category WHERE id= ?";
		try(Connection con= DBConnect.getConnection();
				PreparedStatement pstmt=con.prepareStatement(query)){
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
