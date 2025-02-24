package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;

public class DashboardController implements Initializable{
	@FXML
    private Button btnCategory;

    @FXML
    private Button btnCustomer;

    @FXML
    private Button btnDasboard;

    @FXML
    private Button btnOrder;

    @FXML
    private Button btnProduct;

    @FXML
    private Button btnViewOrder;

    @FXML
    private AnchorPane sideNav;
    
    Main m;
    
    @FXML
    private Button btnLogout;
	
    
    @FXML
    public void onLogoutBtnClick(ActionEvent event) throws IOException {
    	m=new Main();
    	m.changeScene("/view/LoginPage.fxml");

    }
    
    @FXML
    void onCategoryClick(MouseEvent event) {

    }
    @FXML
    void onCustomerClick(MouseEvent event) {

    }

    @FXML
    void onOrderClick(MouseEvent event) {

    }

    @FXML
    void onProductClick(MouseEvent event) {

    }
    @FXML
    void onViewOrderClick(MouseEvent event) {

    }



	// Event Listener on Button[#btnDash].onAction
	@FXML
	public void onDashBtnClick(ActionEvent event) throws IOException {
		// TODO Autogenerated
		m=new Main();
		m.changeScene("/view/Dashboard.fxml");
	}
	// Event Listener on Button[#btnCategory].onAction
	@FXML
	public void onCategoryBtnClick(ActionEvent event) throws IOException {
		// TODO Autogenerated
		m=new Main();
		m.changeScene("/view/Category.fxml");
	}
	// Event Listener on Button[#btnProduct].onAction
	@FXML
	public void onProductBtnClick(ActionEvent event) throws IOException {
		// TODO Autogenerated
		m=new Main();
		m.changeScene("/view/Product.fxml");
	}
	// Event Listener on Button[#btnCustomer].onAction
	@FXML
	public void onCustomerBtnClick(ActionEvent event) throws IOException {
		// TODO Autogenerated
		m=new Main();
		m.changeScene("/view/Customer.fxml");
	}
	// Event Listener on Button[#btnOrder].onAction
	@FXML
	public void onOrderBtnClick(ActionEvent event) throws IOException {
		// TODO Autogenerated
		m=new Main();
		m.changeScene("/view/Order.fxml");
	}
	// Event Listener on Button[#btnViewOrder].onAction
	@FXML
	public void onViewOrderBtnClick(ActionEvent event) throws IOException {
		// TODO Autogenerated
		m=new Main();
		m.changeScene("/view/ViewOrder.fxml");
	}
	@Override
    public void initialize(URL url, ResourceBundle rb) {
		
	}

}
