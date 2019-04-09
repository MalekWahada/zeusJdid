package interUser;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;

import ejbService.UserServiceEjbRemote;
import entities.ProfessionalExp;
import entities.Skill;
import javafx.event.ActionEvent;

import javafx.scene.control.TextArea;

import javafx.scene.control.DatePicker;

public class expprofController implements Initializable{
	@FXML
	private TextField namej;
	@FXML
	private Button addexp;
	@FXML
	private Button ignoreexp;
	@FXML
	private DatePicker datedebexp;
	@FXML
	private DatePicker datefexp;
	@FXML
	private TextField entreprisen;
	@FXML
	private TextArea missionta;

	// Event Listener on Button[#addexp].onAction
	@FXML
	public void addexpAction(ActionEvent event) throws NamingException {
		Context context = new InitialContext();
		String jndi = "zeusPI-ear/zeusPI-ejb/UserServiceEjb!ejbService.UserServiceEjbRemote";
		UserServiceEjbRemote dao = (UserServiceEjbRemote) context.lookup(jndi);
		ProfessionalExp pe=new ProfessionalExp();
		
		pe.setDatedebut(Date.valueOf(datedebexp.getValue()));
		pe.setDatefin(Date.valueOf(datedebexp.getValue()));
		pe.setJobdescription(missionta.getText());
		pe.setJobname(namej.getText());
		pe.setEntrepriseName(entreprisen.getText());
	
		pe.setUser(AcceuilController.usconnected);
		dao.AddProfessionalExp(pe);
		JOptionPane.showMessageDialog(null, "Your Experience is added","Display Message",JOptionPane.INFORMATION_MESSAGE);
	initialize(null, null);
	}
	// Event Listener on Button[#ignoreexp].onAction
	@FXML
	public void ignoreexpAction(ActionEvent event) throws IOException {
		
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("addfirsteducation.fxml"));
        Scene scene = new Scene(root);                     
        primaryStage.setTitle("Educations !");
        primaryStage.setScene(scene);
        primaryStage.show();
	

		
		
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		datedebexp.setValue(null);
		datefexp.setValue(null);
		missionta.setText("");
		namej.setText("");
		entreprisen.setText("");
	}
}
