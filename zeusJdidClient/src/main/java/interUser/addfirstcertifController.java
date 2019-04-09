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
import entities.certification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;

import javafx.scene.control.DatePicker;

public class addfirstcertifController implements Initializable{
	@FXML
	private TextField namescertif;
	@FXML
	private Button addcertif;
	@FXML
	private Button ignorecertif;
	@FXML
	private DatePicker datecertif;
	@FXML
	private TextField centernam;
	@FXML
	private ComboBox<String> specombo=new ComboBox();
	ObservableList<String> options= FXCollections.observableArrayList("Informatique","Literature","Medecine","Bricolage");


	// Event Listener on Button[#addcertif].onAction
	@FXML
	public void addcertifAction(ActionEvent event) throws NamingException {
		Context context = new InitialContext();
		String jndi = "zeusPI-ear/zeusPI-ejb/UserServiceEjb!ejbService.UserServiceEjbRemote";
		UserServiceEjbRemote dao = (UserServiceEjbRemote) context.lookup(jndi);
		certification cert=new certification();
		cert.setDateissue(Date.valueOf(datecertif.getValue()));
		cert.setField(specombo.getValue());
		cert.setName(namescertif.getText());
		cert.setUser(AcceuilController.usconnected);
		cert.setCentrecertif(centernam.getText());
		
		dao.Addcertification(cert);
		JOptionPane.showMessageDialog(null, "Your Certification is added","Display Message",JOptionPane.INFORMATION_MESSAGE);
	initialize(null, null);
	
	}
	// Event Listener on Button[#ignorecertif].onAction
	@FXML
	public void ignorecertifAction(ActionEvent event) throws IOException {
		 Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         Parent root = FXMLLoader.load(getClass().getResource("/templateHome/home.fxml"));
         Scene scene = new Scene(root);
         primaryStage.setTitle("profil_client!");
         primaryStage.setScene(scene);
         primaryStage.show();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		namescertif.setText("");
		centernam.setText("");
		
		specombo.getItems().clear();
					specombo.setItems(options);	
	}
}
