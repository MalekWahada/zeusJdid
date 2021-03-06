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
import entities.Education;
import entities.Skill;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;

import javafx.scene.control.DatePicker;

public class addfirsteducationController implements Initializable{
	@FXML
	private TextField namespeciality;
	@FXML
	private Button addeduc;
	@FXML
	private Button ignoreeduc;
	@FXML
	private DatePicker datedebeduc;
	@FXML
	private DatePicker datefeduc;
	@FXML
	private TextField schoolnam;
	@FXML
	private ComboBox<String> degreecombo=new ComboBox<>();
	ObservableList<String> options= FXCollections.observableArrayList("Engineering","Master","doctoral degree","Licence");

	// Event Listener on Button[#addeduc].onAction
	@FXML
	public void addeducAction(ActionEvent event) throws NamingException {
		// TODO Autogenerated
		Context context = new InitialContext();
		String jndi = "zeusPI-ear/zeusPI-ejb/UserServiceEjb!ejbService.UserServiceEjbRemote";
		UserServiceEjbRemote dao = (UserServiceEjbRemote) context.lookup(jndi);
		Education ed=new Education();
		ed.setDatedeb(Date.valueOf(datedebeduc.getValue()));
		ed.setDatefin(Date.valueOf(datefeduc.getValue()));
		ed.setDegree(degreecombo.getValue());
		ed.setUser(AcceuilController.usconnected);
		ed.setSchool(schoolnam.getText());
		ed.setSpecialite(namespeciality.getText());
		
		dao.Addeducation(ed);
		JOptionPane.showMessageDialog(null, "Your Skill is added","Display Message",JOptionPane.INFORMATION_MESSAGE);
	initialize(null, null);

	}
	// Event Listener on Button[#ignoreeduc].onAction
	@FXML
	public void ignoreeducAction(ActionEvent event) throws IOException {
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("addfirstcertif.fxml"));
        Scene scene = new Scene(root);                     
        primaryStage.setTitle("certification!");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		namespeciality.setText("");
		schoolnam.setText("");
		
					degreecombo.setItems(options);	
					datedebeduc.setValue(null);
					datefeduc.setValue(null);
					degreecombo.setItems(options);	
			
	}
}
