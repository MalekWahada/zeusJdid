package interUser;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import utils.EmailClient;
import utils.Upload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Date;

import javax.mail.MessagingException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;

import ejbService.PostServiceEjbRemote;
import ejbService.UserServiceEjbRemote;
import entities.User;
import javafx.event.ActionEvent;

import javafx.scene.control.TextArea;

import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
public class AcceuilController {
	@FXML
	private JFXButton login;
	@FXML
	private TextField passlogin;
	@FXML
	private TextField emaillog;
	@FXML
	private TextField firstname;
	@FXML
	private TextField lastname;
	@FXML
	private TextArea bio;
	@FXML
	private DatePicker birthday;
	@FXML
	private JFXButton image;
	@FXML
	private JFXButton Signup;
	@FXML
	private TextField Email;
	@FXML
	private PasswordField Password;
String img;
@FXML
private PasswordField passfield;
static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
static SecureRandom rnd = new SecureRandom();

static String randomString( int len ){
   StringBuilder sb = new StringBuilder( len );
   for( int i = 0; i < len; i++ ) 
      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
   return sb.toString();
}
public static User usconnected=new User();
    @FXML
    private TextField city;
    @FXML
    private TextField coutry;
    @FXML
    private PasswordField passwordverif;
	// Event Listener on Button[#login].onAction
	@FXML
	public void LoginAction(ActionEvent event) throws NamingException, IOException {
		Context context = new InitialContext();
		String jndi = "zeusPI-ear/zeusPI-ejb/UserServiceEjb!ejbService.UserServiceEjbRemote";
		UserServiceEjbRemote dao = (UserServiceEjbRemote) context.lookup(jndi);
		System.out.println(dao.Loginbymailpwd(emaillog.getText(),passfield.getText()));
		if(dao.Loginbymailpwd(emaillog.getText(),passfield.getText())!=null)
		{
			usconnected=dao.Loginbymailpwd(emaillog.getText(),passfield.getText());
			
			
			
			if(usconnected.getAccountConfirmation()){
		      Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              Parent root = FXMLLoader.load(getClass().getResource("/templateHome/home.fxml"));
              Scene scene = new Scene(root);
              primaryStage.setTitle("profil_client!");
              primaryStage.setScene(scene);
              primaryStage.show();}
			else{
				JOptionPane.showMessageDialog(null, "Please activate your account","Display Message",JOptionPane.INFORMATION_MESSAGE);

				Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	          Parent root = FXMLLoader.load(getClass().getResource("confirmationinterface.fxml"));
	          Scene scene = new Scene(root);                     
	          primaryStage.setTitle("Activation!");
	          primaryStage.setScene(scene);
	          primaryStage.show();
	          
			}
		}
		else JOptionPane.showMessageDialog(null, "ERROR","Display Message",JOptionPane.INFORMATION_MESSAGE);
		
			
	}

	// Event Listener on Button[#image].onAction
	@FXML
	public void imageAction(ActionEvent event) throws FileNotFoundException, IOException {
		  FileChooser fileChooser = new FileChooser();
	        fileChooser.setTitle("Selectionnez votre image");
	        fileChooser.getExtensionFilters().addAll(
	        new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));

	        File selectedFile = fileChooser.showOpenDialog(null);

	        if (selectedFile != null) {
	          
	           Upload u = new Upload();
	           u.upload(selectedFile);
	          
	            
	                 Image image = new Image("file:///C://wamp64//www//pi2Images//"+selectedFile.getName());
	       img="file:///C://wamp64//www//pi2Images//"+selectedFile.getName();          

	}}
	// Event Listener on Button[#Signup].onAction
	@FXML
	public void SignupAction(ActionEvent event) throws NamingException, MessagingException, IOException {
		Context context = new InitialContext();
		String jndi = "zeusPI-ear/zeusPI-ejb/UserServiceEjb!ejbService.UserServiceEjbRemote";
		UserServiceEjbRemote dao = (UserServiceEjbRemote) context.lookup(jndi);
		
		
		User u = new User();
		String code=randomString(5);
		u.setVille(city.getText());
		u.setPays(coutry.getText());
		u.setCodeActivation(code);
		u.setFirstname(firstname.getText());
		u.setLastname(lastname.getText());
		u.setEmail(Email.getText());
		u.setPassword(Password.getText());
		u.setPictureURL(img);
		Date date = Date.valueOf(birthday.getValue());
		
		u.setBirthDate(date);
u.setBio(bio.getText());

System.out.println("   kkkkkkkkkkkkkkkkkkk   "+u);
	
		System.out.println("   kkkkkkkkkkkkkkkkkkk   "+u);
	
		if((EmailClient.sendAsHtml(Email.getText(),"Code Activation",  "<h2>Your Code activation</h2><p>"+code+"</p>")).equals("erreur"))
    		JOptionPane.showMessageDialog(null, "Your email is invalid","Display Message",JOptionPane.WARNING_MESSAGE);

		else{
	
			u = dao.addUser(u); 		
			usconnected=u;
		
	      Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          Parent root = FXMLLoader.load(getClass().getResource("confirmationinterface.fxml"));
          Scene scene = new Scene(root);                     
          primaryStage.setTitle("Activation!");
          primaryStage.setScene(scene);
          primaryStage.show();
          
		}
		
	}
}
