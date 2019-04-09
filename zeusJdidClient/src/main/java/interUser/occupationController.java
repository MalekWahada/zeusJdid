package interUser;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;

import ejbService.UserServiceEjbRemote;
import javafx.event.ActionEvent;

public class occupationController {
	@FXML
	private TextField poste;
	@FXML
	private Button addoccup;
	@FXML
	private Button ignoreoccup;
	@FXML
	private TextField entrepschool;

	// Event Listener on Button[#addoccup].onAction
	@FXML
	public void addoccupAction(ActionEvent event) throws NamingException, IOException {
		Context context = new InitialContext();
		String jndi = "zeusPI-ear/zeusPI-ejb/UserServiceEjb!ejbService.UserServiceEjbRemote";
		UserServiceEjbRemote dao = (UserServiceEjbRemote) context.lookup(jndi);
		
		String pass=AcceuilController.usconnected.getPassword();
		String mail=AcceuilController.usconnected.getEmail();
		AcceuilController.usconnected=dao.Loginbymailpwd(mail,pass); 
		AcceuilController.usconnected.setActualOccupation(poste.getText());
		
		AcceuilController.usconnected.setEntreschool(entrepschool.getText());
		
		dao.updateUser(AcceuilController.usconnected);
		
		JOptionPane.showMessageDialog(null, "Your Occupation is Added","Display Message",JOptionPane.INFORMATION_MESSAGE);
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          Parent root = FXMLLoader.load(getClass().getResource("addfirstskill.fxml"));
          Scene scene = new Scene(root);                     
          primaryStage.setTitle("Skills!");
          primaryStage.setScene(scene);
          primaryStage.show();
	}
	// Event Listener on Button[#ignoreoccup].onAction
	@FXML
	public void ignoreoccupAction(ActionEvent event) throws IOException {
		
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          Parent root = FXMLLoader.load(getClass().getResource("addfirstskill.fxml"));
          Scene scene = new Scene(root);                     
          primaryStage.setTitle("Skills!");
          primaryStage.setScene(scene);
          primaryStage.show();		
	}
}
