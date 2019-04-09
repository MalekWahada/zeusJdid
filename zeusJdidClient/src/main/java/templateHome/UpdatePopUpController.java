package templateHome;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.hamcrest.core.IsInstanceOf;

import UsrInterface.Upload;
import ejbService.PostServiceEjbRemote;
import entities.Media;
import entities.Post;
import entities.TextPost;
import entities.User;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UpdatePopUpController {
	@FXML
	private ImageView imagViewUpdt;
	@FXML
	private Label messageLabel;
	@FXML
	private TextArea updateTF;
	@FXML
	private Label detailsLabel;
	@FXML
	private HBox actionParent;
	@FXML
	private Button uploadUpdBtn;
	@FXML
	private HBox okParent;
	@FXML
	private Button updateBtn;

	
	
	//////////////////////////////////////   private parameters  ////////////////////////
	private String imageUrl;
	private Post pp;
	
	private Context context;
	private String jndi = "zeusPI-ear/zeusPI-ejb/PostServiceEjb!ejbService.PostServiceEjbRemote";
	
	// Event Listener on Button[#uploadUpdBtn].onAction

	public void setInterfaceParam(Post p) {

		if (p instanceof Media) {
			pp = p;
			Media p1 = (Media) p;
			Image imag = new Image(p1.getPath_url());

			imagViewUpdt.setImage(imag);
			updateTF.setText(p1.getDescription());
			imageUrl = p1.getPath_url();
		}
		else if(p instanceof TextPost)
		{
			pp = p;
			TextPost p2 = (TextPost) p;
			updateTF.setText(p2.getContent());
		}

	}

	@FXML
	public void UploadUpdAction(ActionEvent event) {
		// TODO Autogenerated

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Selectionnez votre image");
		fileChooser.getExtensionFilters()
				.addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		File selectedFile = fileChooser.showOpenDialog(null);
		if (selectedFile != null) {

			Upload u = new Upload();
			try {
				u.upload(selectedFile);
				imageUrl = u.upload(selectedFile);

			} catch (IOException ex) {
			}

			Image imag = new Image("file:///C://wamp64//www//pi2Images//" + selectedFile.getName());
			// chemin.setText("file:///C://wamp64//www//pi2Images//" +
			// selectedFile.getName());
			imagViewUpdt.setImage(imag);

		}

	}

	// Event Listener on Button[#updateBtn].onAction
	@FXML
	public void UpdateAction(ActionEvent event) throws NamingException, IOException {
		// TODO Autogenerated
		 context = new InitialContext();
		
		PostServiceEjbRemote dao = (PostServiceEjbRemote) context.lookup(jndi);
		if(imageUrl==null)
		{
			TextPost tPost = new TextPost();
			tPost = (TextPost) dao.findPost(pp.getIdPost());
			System.out.println(tPost);
			tPost.setContent(updateTF.getText());
			tPost.setPostTime(new Timestamp(System.currentTimeMillis()));
			System.out.println(tPost);
			tPost = (TextPost) dao.updatePost(tPost);
			Stage stage = (Stage) updateBtn.getScene().getWindow();
			stage.close();
		}
		else{
		

		Post m2 = new Post();
		m2 = dao.findPost(pp.getIdPost());
		Media mm = (Media) m2;

		System.out.println(m2 + "   " + updateTF.getText());
		mm.setDescription(updateTF.getText());
		mm.setPostTime(new Timestamp(System.currentTimeMillis()));
		if (imageUrl != null)
			mm.setPath_url(imageUrl);
		mm = (Media) dao.updatePost(mm);

		System.out.println("jjjjjjjjjjjjj    " + mm);
		Stage stage = (Stage) updateBtn.getScene().getWindow();
		stage.close();
		}
		
		
		
		
	}

}
