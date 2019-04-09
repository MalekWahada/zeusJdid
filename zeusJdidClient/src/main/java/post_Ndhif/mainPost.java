package post_Ndhif;

import java.io.IOException;

import javafx.application.Application;
import javafx.beans.property.ObjectPropertyBase;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class mainPost extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("newsfeedz.fxml"));
		primaryStage.setTitle("uzzzzzzzzzerrrrr");
		primaryStage.setScene(new Scene(root, 800, 600,Color.BLACK));
		primaryStage.show(); 

	}

	public static void main(String[] args) {
		launch(args);
	}
}
