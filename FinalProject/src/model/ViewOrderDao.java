package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ViewOrderDao {
	public ObservableList<OrderDetails> getOrdersList(int customerId){
		ObservableList<OrderDetails> orderList=FXCollections.observableArrayList();
		String query = "SELECT * FROM im_orderdetails WHERE customerId = ?";
		try(Connection con= DBConnect.getConnection();
				PreparedStatement pstmt=con.prepareStatement(query)){
			pstmt.setInt(1, customerId);
			ResultSet rs= pstmt.executeQuery();
			OrderDetails orderDetails;
			while(rs.next()) {
				orderDetails = new OrderDetails(rs.getString("orderId"), rs.getInt("customerId"), rs.getString("orderDate"), rs.getInt("totalPaid"));
				orderList.add(orderDetails);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderList;
	} 

}
