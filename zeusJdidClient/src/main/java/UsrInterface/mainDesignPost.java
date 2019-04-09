package UsrInterface;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class mainDesignPost extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		
		Pane pane = new Pane();
		
		 Parent root = FXMLLoader.load(getClass().getResource("MediaText.fxml"));
	        primaryStage.setTitle("uzzzzzzzzzerrrrr");
	        primaryStage.setScene(new Scene(root, 800, 500));
	        primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
