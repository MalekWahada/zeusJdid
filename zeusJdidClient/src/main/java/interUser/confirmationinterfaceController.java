package interUser;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.EmailClient;

import java.io.IOException;
import java.security.SecureRandom;

import javax.mail.MessagingException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;

import ejbService.UserServiceEjbRemote;
import javafx.event.ActionEvent;

public class confirmationinterfaceController {
	@FXML
	private TextField codetf;
	@FXML
	private Button activbtn;
	@FXML
	private Button renvcodebtn;

	// Event Listener on Button[#activbtn].onAction
	@FXML
	public void activaction(ActionEvent event) throws NamingException, IOException {
		if(AcceuilController.usconnected.getCodeActivation().equals(codetf.getText()))
			{	
		Context context = new InitialContext();
		String jndi = "zeusPI-ear/zeusPI-ejb/UserServiceEjb!ejbService.UserServiceEjbRemote";
		UserServiceEjbRemote dao = (UserServiceEjbRemote) context.lookup(jndi);
		
		String pass=AcceuilController.usconnected.getPassword();
		String mail=AcceuilController.usconnected.getEmail();
		AcceuilController.usconnected=dao.Loginbymailpwd(mail,pass); 
		AcceuilController.usconnected.setAccountConfirmation(true);
		
		dao.updateUser(AcceuilController.usconnected);
		
		JOptionPane.showMessageDialog(null, "Your Account is activated","Display Message",JOptionPane.INFORMATION_MESSAGE);
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          Parent root = FXMLLoader.load(getClass().getResource("occupation.fxml"));
          Scene scene = new Scene(root);                     
          primaryStage.setTitle("Occupation!");
          primaryStage.setScene(scene);
          primaryStage.show();

			
			
			}
		else{JOptionPane.showMessageDialog(null, "this code is invalid","Display Message",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	// Event Listener on Button[#renvcodebtn].onAction
	@FXML
	public void renvcodeaction(ActionEvent event) throws MessagingException, NamingException, IOException {
	
		 Context context = new InitialContext();
			String jndi = "zeusPI-ear/zeusPI-ejb/UserServiceEjb!ejbService.UserServiceEjbRemote";
			UserServiceEjbRemote dao = (UserServiceEjbRemote) context.lookup(jndi);
			
			String pass=AcceuilController.usconnected.getPassword();
			String mail=AcceuilController.usconnected.getEmail();
			AcceuilController.usconnected=dao.Loginbymailpwd(mail,pass); 
			String newcode=randomString(5);
			EmailClient.sendAsHtml(AcceuilController.usconnected.getEmail(),"Code Activation",  "<h2>Your Code activation</h2><p>"+newcode+"</p>");
			
			 AcceuilController.usconnected.setCodeActivation(newcode);
			dao.updateUser(AcceuilController.usconnected);
	        
			
	}
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom rnd = new SecureRandom();
	static String randomString( int len ){
		   StringBuilder sb = new StringBuilder( len );
		   for( int i = 0; i < len; i++ ) 
		      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
		   return sb.toString();
		}
	
	
	
	
}
