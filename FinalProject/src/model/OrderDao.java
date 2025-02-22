package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDao {
	
	public void updateProductQty(int qty, int productId) {
		String query = "UPDATE im_product SET quantity = quantity-? WHERE id = ?";
		try(Connection con= DBConnect.getConnection();
				PreparedStatement pstmt=con.prepareStatement(query)){
			pstmt.setInt(1, qty);
			pstmt.setInt(2, productId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void insertOrderDetails(String orderId, int customerId, String date, int totalPaid) {
		String query = "INSERT INTO im_orderdetails (orderId, customerId, orderDate, totalPaid) "
				+ "VALUES (?,?,?,?)";
		try(Connection con= DBConnect.getConnection();
				PreparedStatement pstmt=con.prepareStatement(query)){
			pstmt.setString(1, orderId);
			pstmt.setInt(2, customerId);
			pstmt.setString(3, date);
			pstmt.setInt(4, totalPaid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
	}
	public int getProductQty(int id) {
		String query = "SELECT * FROM im_product WHERE id= ?";

		try(Connection con= DBConnect.getConnection();
				PreparedStatement pstmt=con.prepareStatement(query)){
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("quantity");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;  
	}

}
