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
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;

import ejbService.UserServiceEjbRemote;
import entities.Skill;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.Slider;

import javafx.scene.control.ComboBox;

public class addfirstskillController implements Initializable{
	@FXML
	private ComboBox<String> specialitycombo=new ComboBox<>();
	@FXML
	private Slider evaluates;
	@FXML
	private TextField names;
	@FXML
	private Button adds;
	@FXML
	private Button ignores;
	 ObservableList<String> options= FXCollections.observableArrayList("Informatique","Literature","Medecine","Bricolage");
	 
		@Override
		public void initialize(URL location, ResourceBundle resources) {
names.setText("");

			specialitycombo.setItems(options);	
	evaluates.setValue(1);
	
		}
	 
	 
	 
	// Event Listener on Button[#adds].onAction
	@FXML
	public void addsAction(ActionEvent event) throws NamingException, IOException {
		Context context = new InitialContext();
		String jndi = "zeusPI-ear/zeusPI-ejb/UserServiceEjb!ejbService.UserServiceEjbRemote";
		UserServiceEjbRemote dao = (UserServiceEjbRemote) context.lookup(jndi);
		Skill s=new Skill();
		s.setName(names.getText());
		
		s.setRating((int) evaluates.getValue());
		s.setSpecialite(specialitycombo.getValue());
		s.setUser(AcceuilController.usconnected);
		dao.AddSkill(s);
		JOptionPane.showMessageDialog(null, "Your Skill is added","Display Message",JOptionPane.INFORMATION_MESSAGE);
	initialize(null, null);

      
	}
	// Event Listener on Button[#ignores].onAction
	@FXML
	public void ignoresAction(ActionEvent event) throws IOException {

		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("expprof.fxml"));
        Scene scene = new Scene(root);                     
        primaryStage.setTitle("Experiences !");
        primaryStage.setScene(scene);
        primaryStage.show();
	
	}

}
