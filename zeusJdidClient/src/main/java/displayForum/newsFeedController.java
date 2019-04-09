package displayForum;

import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeView;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class newsFeedController implements Initializable{
	@FXML
	private AnchorPane an;
	@FXML
	private JFXTextField searchFrTF;
	@FXML
	private JFXTextField sendMsgTF;
	@FXML
	private JFXTreeView JFXTreeView;
	
	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
	}
	
	 @FXML
	    private void searchFrAction(KeyEvent event)  {
		 
	 }
	 
	 @FXML
	    private void sendMsgAction(KeyEvent event)  {
		 
		 
	 }
	 
}
