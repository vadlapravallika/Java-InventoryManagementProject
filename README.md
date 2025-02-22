# Inventory Management System

The **Inventory Management System** is a JavaFX-based application designed to help administrators manage categories, products, customers, and orders efficiently. The system follows the **MVC (Model-View-Controller)** architecture, ensuring modularity and ease of maintenance. It provides functionalities such as user authentication, CRUD operations for categories, products, and customers, and bill generation for orders.


## Features

- **User Authentication**:
  - Admin login with username and password validation.
  - Sign-up option for new users/admins.

- **Dashboard**:
  - Displays the number of categories, products, customers, and orders.
  - Provides navigation to different sections of the application.

- **Category Management**:
  - Add, update, delete, and reset categories.
  - View a list of all categories in a table.

- **Product Management**:
  - Add, update, delete, and reset products.
  - View a list of all products with details such as name, quantity, price, description, and category.

- **Customer Management**:
  - Add, update, delete, and reset customers.
  - View a list of all customers with details such as name, mobile number, and email.

- **Order Management**:
  - Add products to a cart and generate bills.
  - Save order details and view them later.
  - Generate PDF bills for orders.

- **View Orders**:
  - View orders placed by customers.
  - Open PDF bills for specific orders.

## Technologies Used

- **Front End**: JavaFX
- **Back End**: MySQL
- **Tools**:
  - Eclipse IDE
  - JavaFX Scene Builder
  - MySQL Workbench

## Prerequisites

Before running the application, ensure you have the following installed:

1. **Java Development Kit (JDK)**: Version 8 or higher.
2. **MySQL**: For database management.
3. **JavaFX SDK**: For building the user interface.
4. **Eclipse IDE** (optional): For development and running the application.
5. **MySQL Connector/J**: JDBC driver for connecting Java with MySQL.

## Setup Instructions

### 1. **Database Setup**

1. Create a MySQL database named `inventory_management`.
2. Run the following SQL scripts to create the necessary tables:

```sql
CREATE DATABASE inventory_management;
USE inventory_management;

-- Table for users (admins)
CREATE TABLE pravallika_user_tbl (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL
);

-- Table for categories
CREATE TABLE im_category (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- Table for products
CREATE TABLE im_product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    description TEXT,
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES im_category(id)
);

-- Table for customers
CREATE TABLE im_customer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    mobile_number VARCHAR(15) NOT NULL,
    email VARCHAR(100) NOT NULL
);

-- Table for orders
CREATE TABLE im_orderdetails (
    id INT AUTO_INCREMENT PRIMARY KEY,
    order_id VARCHAR(100) NOT NULL,
    date DATE NOT NULL,
    total_paid DECIMAL(10, 2) NOT NULL
);
```
3. Insert sample data into the tables if needed.


### 2. **Project Setup**

1. Clone the repository to your local machine.
2. Open the project in **Eclipse IDE** or any other Java IDE.
3. Add the **JavaFX SDK** and **MySQL Connector/J** to your project's build path.
4. Update the database connection details in the `DBConnection.java` file (or equivalent) with your MySQL credentials:

```java
public class DBConnection {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory_management", "root", "your_password");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
```

### 3. **Running the Application**

1. Navigate to the `Main.java` file in the `src` folder.
2. Run the `Main.java` file to start the application.
3. The **Login Page** will appear. Use the following credentials to log in as an admin:
   - **Username**: `admin`
   - **Password**: `admin`
4. Alternatively, you can create a new account using the **Sign Up** option.


## Screenshots

### Login Page
![Login Page]![image](https://github.com/user-attachments/assets/47e2c023-b23e-4743-b1b5-c97f7e5b5334)

### Dashboard
![Dashboard]![image](https://github.com/user-attachments/assets/33e897e4-9c1e-4e2c-8c80-666c99a18f14)

### Category Management
![Category Management](https://github.com/user-attachments/assets/32bc985b-847c-4371-a22e-1fda47160a43)

### Product Management
![Product Management]![image](https://github.com/user-attachments/assets/01d3e27c-6b0d-4a01-86a8-fa2643ab933d)


### Order Management
![Order Management]![image](https://github.com/user-attachments/assets/b65d3a41-4b09-449e-ab77-1560bb2a6f4f)



## Project Structure

```
InventoryManagementSystem/
├── src/
│   ├── controller/          # Controller classes for JavaFX
│   ├── model/               # Model classes for database operations
│   ├── view/                # FXML files for UI
│   ├── DBConnection.java    # Database connection class
│   ├── Main.java            # Main class to launch the application
├── lib/                     # External libraries (JavaFX, MySQL Connector)
├── screenshots/             # Screenshots of the application
├── README.md                # Project documentation
```

## Conclusion

The **Inventory Management System** is a robust and user-friendly application designed to streamline inventory management for businesses. It provides a comprehensive set of features for managing categories, products, customers, and orders, along with secure user authentication and bill generation.


## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.



## Author

- **Pravallika Vadla**
- GitHub: [Your GitHub Profile](https://github.com/vadlapravallika)
- Email: pravalli541@gmail.com


Feel free to contribute to the project by submitting issues or pull requests. For any questions or feedback, please contact the author.
