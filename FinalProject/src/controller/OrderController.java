package controller;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Other.OpenPdf;
import application.Main;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.Cart;
import model.Customer;
import model.CustomerDao;
import model.InventoryUtils;
import model.OrderDao;
import model.Product;
import model.ProductDao;

public class OrderController implements Initializable{
	@FXML
	private Button btnDash;
	@FXML
	private Button btnCategory;
	@FXML
	private Button btnProduct;
	@FXML
	private Button btnCustomer;
	@FXML
	private Button btnOrder;
	@FXML
	private Button btnViewOrder;
	@FXML
	private TableView<Customer> tCustomer;
	@FXML
	private TableColumn<Customer, Integer> colId;
	@FXML
	private TableColumn<Customer, String> colName;
	@FXML
	private TableColumn<Customer, String> colMbl;
	@FXML
	private TableColumn<Customer, String> colEmail;
	@FXML
	private TextField tfName;
	@FXML
	private TextField tfId;
	@FXML
	private TextField tfMbl;
	@FXML
	private TextField tfEmail;
	@FXML
	private TableView<Product> tProduct;
	@FXML
	private TableColumn<Product, Integer> colProductId;
	@FXML
	private TableColumn<Product, String> colProductName;
	@FXML
	private TableColumn<Product, Integer> colQty;
	@FXML
	private TableColumn<Product, Integer> colPrice;
	@FXML
	private TableColumn<Product, String> colDesc;
	@FXML
	private TableColumn<Product, String> colCategory;
	@FXML
	private TextField tfProductId;
	@FXML
	private TextField tfProductName;
	@FXML
	private TextField tfPrice;
	@FXML
	private TextField tfDesc;
	@FXML
	private TextField tfOrderQty;
	@FXML
	private TableView<Cart> tCart;
	@FXML
	private TableColumn<Cart, Integer> cartId;
	@FXML
	private TableColumn<Cart, String> cartName;
	@FXML
	private TableColumn<Cart, Integer> cardQty;
	@FXML
	private TableColumn<Cart, Integer> cartPrice;
	@FXML
	private TableColumn<Cart, String> cartDesc;
	@FXML
	private TableColumn<Cart, Integer> cartSubTotal;
	@FXML
	private Text tfTotalAmount;
	@FXML
	private Button btnAddToCart;
	@FXML
	private Button btnSave;
	@FXML
	private Button btnReset;
	Alert alert;
	int totalAmount = 0;
	String orderId;
	Main m=new Main();
	
	@FXML
    private Button btnLogout;
    
    @FXML
    void onLogoutBtnClick(ActionEvent event) throws IOException {
    	m.changeScene("/view/LoginPage.fxml");

    }

	// Event Listener on Button[#btnDash].onAction
	@FXML
	public void onDashBtnClick(ActionEvent event) throws IOException {
		// TODO Autogenerated
		m.changeScene("/view/Dashboard.fxml");
	}
	// Event Listener on Button[#btnCategory].onAction
	@FXML
	public void onCategoryBtnClick(ActionEvent event) throws IOException {
		// TODO Autogenerated
		m.changeScene("/view/Category.fxml");
	}
	// Event Listener on Button[#btnProduct].onAction
	@FXML
	public void onProductBtnClick(ActionEvent event) throws IOException {
		// TODO Autogenerated
		m.changeScene("/view/Product.fxml");
	}
	// Event Listener on Button[#btnCustomer].onAction
	@FXML
	public void onCustomerBtnClick(ActionEvent event) throws IOException {
		// TODO Autogenerated
		m.changeScene("/view/Customer.fxml");
	}
	// Event Listener on Button[#btnOrder].onAction
	@FXML
	public void onOrderBtnClick(ActionEvent event) throws IOException {
		// TODO Autogenerated
		m.changeScene("/view/Order.fxml");
	}
	// Event Listener on Button[#btnViewOrder].onAction
	@FXML
	public void onViewOrderBtnClick(ActionEvent event) throws IOException {
		// TODO Autogenerated
		m.changeScene("/view/ViewOrder.fxml");
	}

	@FXML
	public void handleRowDel(MouseEvent event) {
		int selectedIdx = tCart.getSelectionModel().getSelectedIndex();

		int totalPrice = tCart.getSelectionModel().getSelectedItem().getSubtotal();
		alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Removing Item From Cart Message");
		alert.setHeaderText(null);
		alert.setContentText("Do You Want To Remove This Item From The Cart");
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get() == ButtonType.OK) {
			totalAmount -=totalPrice;
			tfTotalAmount.setText(""+totalAmount);
			tCart.getItems().remove(selectedIdx);
		}

	}
	// Event Listener on TableView[#tCustomer].onMouseClicked
	@FXML
	public void handleOnMouseclick(MouseEvent event) {
		// TODO Autogenerated
		Customer customer=tCustomer.getSelectionModel().getSelectedItem();
		tfId.setText("" + customer.getId());
		tfName.setText(customer.getName());
		tfMbl.setText(customer.getMobile());
		tfEmail.setText(customer.getEmail());
	}
	// Event Listener on TableView[#tProduct].onMouseClicked
	@FXML
	public void handleOnMouseClick(MouseEvent event) {
		// TODO Autogenerated

		Product product = tProduct.getSelectionModel().getSelectedItem();
		tfProductId.setText("" + product.getId());
		tfProductName.setText(product.getName());
		tfPrice.setText("" + product.getPrice());
		tfDesc.setText("" + product.getDescription());
	}
	// Event Listener on Button[#btnAddToCart].onAction
	@FXML
	public void onAddToCartBtnClick(ActionEvent event) {
		// TODO Autogenerated
		OrderDao dao= new OrderDao();
		String productId = tfProductId.getText();
		if(productId.equals("")) {

			alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Message");
			alert.setHeaderText(null);
			alert.setContentText("Quantity and product information is required, Please select some product!");
			alert.showAndWait();
		}
		else {
			String productPrice = tfPrice.getText();
			String orderQty = tfOrderQty.getText();
			if(orderQty.equals("")) {
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Message");
				alert.setHeaderText(null);
				alert.setContentText("Order quantity Is Required");
				alert.showAndWait();
			} else {
				String productName= tfProductName.getText();
				String productDesc = tfDesc.getText();
				int productQty = dao.getProductQty(Integer.valueOf(productId));
				if(Integer.valueOf(orderQty) > productQty) {
					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Message");
					alert.setHeaderText(null);
					alert.setContentText("Product is out of stock. Only " + productQty +"left");
					alert.showAndWait();

				}else {
					boolean checkIfProductAlreadyAdded = false;
					int size = tCart.getItems().size();
					for(int i=0;i<size;i++) {
						if(Integer.valueOf(productId) == tCart.getItems().get(i).getId()) {
							checkIfProductAlreadyAdded = true;
							break;
						}
					}
					if(checkIfProductAlreadyAdded) {
						alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Information Message");
						alert.setHeaderText(null);
						alert.setContentText("Product is already added");
						alert.showAndWait();
					} else {

						int total = Integer.valueOf(productPrice) * Integer.valueOf(orderQty);
						Cart cart=new Cart(Integer.valueOf(productId),productName,  Integer.valueOf(orderQty),
								Integer.valueOf(productPrice), productDesc, total);
						ObservableList<Cart> cartList= tCart.getItems();
						cartList.add(cart);
						cartId.setCellValueFactory(new PropertyValueFactory<Cart, Integer>("id"));
						cartName.setCellValueFactory(new PropertyValueFactory<Cart, String>("pname"));
						cardQty.setCellValueFactory(new PropertyValueFactory<Cart, Integer>("quantity"));
						cartPrice.setCellValueFactory(new PropertyValueFactory<Cart, Integer>("price"));
						cartDesc.setCellValueFactory(new PropertyValueFactory<Cart, String>("description"));
						cartSubTotal.setCellValueFactory(new PropertyValueFactory<Cart, Integer>("subtotal"));
						tCart.setItems(cartList);
						alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Information Message");
						alert.setHeaderText(null);
						alert.setContentText("Product added successfully!!");
						alert.showAndWait();
						totalAmount += total;
						tfTotalAmount.setText(""+totalAmount);
					}
				}
			}
		}
	}

	public String getUniqueId(String prefix) {
		return prefix + System.nanoTime();
	}
	// Event Listener on Button[#btnSave].onAction
	@FXML
	public void onSaveBtnClick(ActionEvent event) {
		// TODO Autogenerated

		if(totalAmount !=0 && !tfName.getText().equals("")) {
			OrderDao dao= new OrderDao();
			int size = tCart.getItems().size();
			orderId = getUniqueId("Bill-");
			if(size !=0) {
				for(int i=0;i<size;i++) {				
					dao.updateProductQty(tCart.getItems().get(i).getQuantity(), tCart.getItems().get(i).getId());
				}		

			}else {
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Message");
				alert.setHeaderText(null);
				alert.setContentText("Please Add Some Product To Cart");
				alert.showAndWait();

			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
			Calendar cal=Calendar.getInstance();

			try {			
				dao.insertOrderDetails(orderId, Integer.parseInt(tfId.getText()), dateFormat.format(cal.getTime()), totalAmount);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
			try{
				PdfWriter.getInstance(doc, new FileOutputStream(InventoryUtils.buillpath+""+orderId+".pdf"));
				doc.open();
				Paragraph projectName=new Paragraph("                                   Inventory Management System");
				doc.add(projectName);
				Paragraph line = new Paragraph("**************************************************************************");
				doc.add(line);
				Paragraph details =  new Paragraph("\tOrder ID: "+orderId+"\nDate: "+ dateFormat.format(cal.getTime())+"\nTotal Paid: "+totalAmount);
				doc.add(details);
				doc.add(line);
				PdfPTable tbl= new PdfPTable(5);
				PdfPCell nameCell=new PdfPCell(new Phrase("Name"));
				PdfPCell descriptionCell=new PdfPCell(new Phrase("Description"));
				PdfPCell priceCell=new PdfPCell(new Phrase("Price Per Unit"));
				PdfPCell quantityCell=new PdfPCell(new Phrase("Quantity"));
				PdfPCell subTotalPriceCell=new PdfPCell(new Phrase("Total Price"));

				BaseColor bgcolor=new BaseColor(Color.magenta);

				nameCell.setBackgroundColor(bgcolor);
				descriptionCell.setBackgroundColor(bgcolor);
				priceCell.setBackgroundColor(bgcolor);
				quantityCell.setBackgroundColor(bgcolor);
				subTotalPriceCell.setBackgroundColor(bgcolor);
				tbl.addCell(nameCell);
				tbl.addCell(descriptionCell);
				tbl.addCell(priceCell);
				tbl.addCell(quantityCell);
				tbl.addCell(subTotalPriceCell);

				for(int i=0;i<size;i++) {
					tbl.addCell(tCart.getItems().get(i).getPname());
					tbl.addCell(tCart.getItems().get(i).getDescription());
					tbl.addCell(""+tCart.getItems().get(i).getPrice());
					tbl.addCell(""+tCart.getItems().get(i).getQuantity());
					tbl.addCell(""+tCart.getItems().get(i).getSubtotal());

				}
				doc.add(tbl);
				doc.add(line);
				Paragraph thankMsg = new Paragraph("\nThank you, Please visit us again.");
				doc.add(thankMsg);
				OpenPdf.openById(orderId);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			doc.close();

		}else {
			alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Message");
			alert.setHeaderText(null);
			alert.setContentText("Please sdd some product to cart or select customer");
			alert.showAndWait();
		}
	}


	// Event Listener on Button[#btnReset].onAction
	@FXML
	public void onResetBtnClick(ActionEvent event) {
		// TODO Autogenerated
		showCustomers();
		showProducts();
		tfName.setText(null);
		 tfId.setText(null);
		 tfMbl.setText(null);;
		tfEmail.setText(null);
		tfProductId.setText(null);
		tfProductName.setText(null);
		tfPrice.setText(null);
		tfDesc.setText(null);
		tfOrderQty.setText(null);
		
	}
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		showCustomers();
		showProducts();
	}
	public void showCustomers() {
		CustomerDao dao=new CustomerDao();
		ObservableList<Customer> list= dao.getCustomerList();
		colId.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
		colName.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
		colMbl.setCellValueFactory(new PropertyValueFactory<Customer, String>("mobile"));
		colEmail.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
		tCustomer.setItems(list);
	}
	public void showProducts() {
		ProductDao dao=new ProductDao();
		ObservableList<Product> list= dao.getProductList();
		colProductId.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
		colProductName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
		colQty.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
		colPrice.setCellValueFactory(new PropertyValueFactory<Product, Integer>("price"));
		colDesc.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));
		colCategory.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
		tProduct.setItems(list);
	}
}
