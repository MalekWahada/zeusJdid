package UsrInterface;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ejbService.PostServiceEjbRemote;
import entities.User;
import javafx.event.ActionEvent;

public class UserAddController {
	@FXML
	private TextField lastNameTF;
	@FXML
	private TextField firstNameTF;
	@FXML
	private Button addUserBtn;

	// Event Listener on Button[#addUserBtn].onAction
	@FXML
	public void addUser(ActionEvent event) {
		// TODO Autogenerated
		
		
	
			try {
				Context context = new InitialContext();
				String jndi = "zeusPI-ear/zeusPI-ejb/PostServiceEjb!ejbService.PostServiceEjbRemote";
				PostServiceEjbRemote dao = (PostServiceEjbRemote) context.lookup(jndi);
				User u1 = new User(); 
				u1.setFirstname(firstNameTF.getText()); u1.setLastname(lastNameTF.getText());
				u1 = dao.addUser(u1);
				System.out.println("  userzbi    "+u1);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		
		
	}
}
