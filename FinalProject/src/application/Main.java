package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	private static Stage stg;
	@Override
	public void start(Stage primaryStage) {
		try {
			stg = primaryStage;
			// Load the login page FXML file

			Parent root = FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml"));

			Scene scene = new Scene(root);
			primaryStage.setTitle("Inventory Managment System");

			// Add external CSS style to the scene
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void changeScene(String fxml) throws IOException {
		// Load the FXML file for the new scene
		Parent pane = FXMLLoader.load(getClass().getResource(fxml));
		// Set the new scene as the root of the primary stage
		stg.getScene().setRoot(pane);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
