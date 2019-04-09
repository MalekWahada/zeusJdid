package post_Ndhif;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.print.DocFlavor.URL;

import org.omg.CORBA.INITIALIZE;
import org.omg.CORBA.PRIVATE_MEMBER;

import com.jfoenix.controls.JFXButton;
import com.sun.javafx.geom.transform.Affine2D;

import UsrInterface.Upload;
import ejbService.PostServiceEjbRemote;
import entities.Comments;
import entities.LikedPost;
import entities.Media;
import entities.Post;
import entities.TextPost;
import entities.User;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class NewsfeedzController implements Initializable {

	private String imageUrl;

	Context context;
	String jndi = "zeusPI-ear/zeusPI-ejb/PostServiceEjb!ejbService.PostServiceEjbRemote";

	///////////////////////

	@FXML
	private ScrollPane an3;
	@FXML
	private JFXButton makePostBtn;

	// Event Listener on JFXButton[#makePostBtn].onAction

	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		init();
	}

	public void init() {
		Image image1 = new Image(getClass().getResourceAsStream("makePost.png"));
		makePostBtn.setGraphic(new ImageView(image1));
		try {
			an3 = dis(an3);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	////////////////////////////////////////////// service mainnews feed return
	////////////////////////////////////////////// function ///////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public ScrollPane dis(ScrollPane an4) throws NamingException {

		VBox an = new VBox();
		an.getChildren().add(makePostBtn);
		context = new InitialContext();
		PostServiceEjbRemote dao = (PostServiceEjbRemote) context.lookup(jndi);

		//////////////// after SKA ///////////
		User u1 = dao.findUser(3);
		ArrayList<Post> lPosts = dao.findallPostsRelatFriend(u1.getId());
		int i = 1;

		for (Post p : lPosts) {

			if (p instanceof Media) {
				Media p1 = (Media) p;
				/////////////////////// user pic + name
				/////////////////////// //////////////////////////
				Label labNameaUser = new Label();
				labNameaUser.setText("\n" + p1.getUser().getFirstname() + " " + p1.getUser().getLastname());

				Circle userPicCerc = new Circle(50, 20, 20);
				Image imgUser = new Image("file:///C://wamp64//www//pi2Images//"
						+ p1.getUser().getPictureURL().replace("localhost:8181//", ""));

				userPicCerc.setFill(new ImagePattern(imgUser));
				HBox hBoxUser = new HBox();

				hBoxUser.getChildren().addAll(userPicCerc, labNameaUser);
				////////////////////// desc /////////////////////
				Label labDesc = new Label();

				labDesc.setText(p1.getDescription());
				Color col = Color.rgb(205, 205, 205);
				CornerRadii corn = new CornerRadii(10);
				Background background = new Background(new BackgroundFill(col, corn, Insets.EMPTY));
				labDesc.setBackground(background);
				// hBoxUser labDesc image
				/////////////// image ///////////////////////////
				ImageView image = new ImageView();
				image.setFitWidth(250);
				image.setFitHeight(150);
				if (p1.getPath_url() != null) {
					Image imag = new Image(
							"file:///C://wamp64//www//pi2Images//" + p1.getPath_url().replace("localhost:8181//", ""));

					image.setImage(imag);
				}

				///////////////////// delete button ///////////////////////////

				Image imagedelP = new Image(getClass().getResourceAsStream("/icons/deleteRed.png"));
				Button deletePostBtn = new Button();
				deletePostBtn.setGraphic(new ImageView(imagedelP));
				deletePostBtn.setStyle("-fx-background-color: #ffffff;");
				deletePostBtn.setOnAction(e -> {

					try {
						String jndidel = "zeusPI-ear/zeusPI-ejb/PostServiceEjb!ejbService.PostServiceEjbRemote";
						PostServiceEjbRemote daodel = (PostServiceEjbRemote) context.lookup(jndidel);
						daodel.deletePost(p.getIdPost());
						init();
					} catch (NamingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				});

				//////////////////////// like/dislike ////////////////////////
				long kj = dao.countPostLike(p.getIdPost());
				Label nblik = new Label("\n" + kj + " likes");

				final ToggleButton likeBtn = new ToggleButton("like");

				Image imagelk = new Image(getClass().getResourceAsStream("/icons/likeBl.png"));
				Image imagedlk = new Image(getClass().getResourceAsStream("/icons/thumbdown.png"));

				likeBtn.setGraphic(new ImageView(imagelk));

				LikedPost lpFind = dao.findlikePostUser(p1.getIdPost(), u1.getId());
				System.out.println(lpFind);
				likeBtn.setStyle("-fx-background-color: #ffffff;");
				if (lpFind.getIdLk() != 0) {
					likeBtn.setGraphic(new ImageView(imagedlk));
					likeBtn.setText("unlike");
					likeBtn.setStyle("-fx-background-color: #ffffff;");
					likeBtn.setSelected(true);
				}

				likeBtn.setOnAction((ActionEvent e) -> {
					if (likeBtn.isSelected()) {
						likeBtn.setGraphic(new ImageView(imagedlk));
						likeBtn.setText("unlike");
						likeBtn.setStyle("-fx-background-color: #ffffff;");
						LikedPost lPost = new LikedPost();
						lPost.setPost(p);
						lPost.setUser(u1);
						dao.addLike(lPost);
						long kj1 = dao.countPostLike(p.getIdPost());
						nblik.setText("\n" + kj1 + " likes");
					} else {
						likeBtn.setGraphic(new ImageView(imagelk));
						likeBtn.setText("like");
						likeBtn.setStyle("-fx-background-color: #ffffff;");
						LikedPost lPost = new LikedPost();
						lPost.setPost(p);
						lPost.setUser(u1);
						dao.deleteLike(lPost);
						long kj2 = dao.countPostLike(p.getIdPost());
						nblik.setText("\n" + kj2 + " likes");
					}

				});

				HBox hblk = new HBox();
				hblk.getChildren().addAll(likeBtn, nblik);
				////////////////////////// update
				////////////////////////// //////////////////////////////////////

				Button updateBtn = new Button();
				Image imageUpdP = new Image(getClass().getResourceAsStream("/icons/updateBl.png"));
				updateBtn.setStyle("-fx-background-color: #ffffff;");
				updateBtn.setGraphic(new ImageView(imageUpdP));
				updateBtn.setOnAction((ActionEvent e) -> {

					try {
						FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("updatePopUp.fxml"));

						Parent root = fxmlLoader.load();
						UpdatePopUpController cntrl = fxmlLoader.getController();
						cntrl.setInterfaceParam(p);

						Scene scene = new Scene(root);
						Stage stage = new Stage();
						stage.setScene(scene);
						stage.show();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				});

				/////////////////////////////////// see comments
				/////////////////////////////////// //////////////////////////
				ToggleSwitch seCmmtBtn = new ToggleSwitch();
				// add cmmt //
				VBox vbAllCmmt = new VBox();

				HBox hBoxAddCmmt = new HBox();
				TextField tAreaCmmt = new TextField();

				Color col1 = Color.rgb(128, 255, 255);
				CornerRadii corn1 = new CornerRadii(10);
				Background background1 = new Background(new BackgroundFill(col1, corn1, Insets.EMPTY));
				tAreaCmmt.setBackground(background1);
				tAreaCmmt.setPrefWidth(220);

				Button cmmtBtn = new Button("Comment");

				Label nbCmmt = new Label();
				nbCmmt.setText(dao.countNbCmmts(p.getIdPost()) + "  Comments");
				cmmtBtn.setOnAction((ActionEvent e) -> {
					Comments c1 = new Comments();
					c1.setContent(tAreaCmmt.getText());

					c1.setUser(u1);
					c1.setTimeComment(new Timestamp(System.currentTimeMillis()));
					c1.setPost(p);
					dao.addComment(c1);
					nbCmmt.setText(dao.countNbCmmts(p.getIdPost()) + "  Comments");
				});

				hBoxAddCmmt.getChildren().addAll(tAreaCmmt, cmmtBtn);

				///////////// vbox cmmts ////////////
				VBox pp = new VBox();

				ArrayList<Comments> lCmmtsPost = dao.findallComments(p.getIdPost());

				int j = 1;
				for (Comments c2 : lCmmtsPost) {
					///////// user ////////////////
					Label labCmmtUser = new Label();
					labCmmtUser.setText(p1.getUser().getFirstname() + " " + p1.getUser().getLastname());

					Circle cmmtUserPic = new Circle(15, 15, 15);
					Image imgUserCmm = new Image("file:///C://wamp64//www//pi2Images//"
							+ p1.getUser().getPictureURL().replace("localhost:8181//", ""));

					cmmtUserPic.setFill(new ImagePattern(imgUserCmm));

					HBox vbCmmtUser = new HBox();
					vbCmmtUser.getChildren().addAll(cmmtUserPic, labCmmtUser);
					//////////// content + time cmmt /////////////
					HBox vbseeCmmt = new HBox();
					Label labCmmt = new Label();
					// labCmmt.setDisable(false);
					labCmmt.setText(c2.getContent());
					labCmmt.setBackground(background);
					Label labCmtTime = new Label(timedisplay(c2.getTimeComment().getTime()));
					vbseeCmmt.getChildren().addAll(labCmmt, labCmtTime);

					///////////////// Cmmt action /////////
					HBox cmmtActionBox = new HBox();
					Image imageDelP = new Image(getClass().getResourceAsStream("/icons/deleteRed.png"));
					Button deleteCmmtBtn = new Button();
					deleteCmmtBtn.setGraphic(new ImageView(imageDelP));
					Button updateCmmtBtn = new Button("Update Comment");
					if (c2.getUser().getId() == u1.getId())
				    cmmtActionBox.getChildren().addAll(deleteCmmtBtn, updateCmmtBtn);

					VBox unCmmt = new VBox();
					unCmmt.getChildren().addAll(vbCmmtUser, vbseeCmmt, cmmtActionBox);
					unCmmt.setStyle("-fx-border-color: black;");
					// vbseeCmmt.setLayoutX(200);
					unCmmt.setLayoutY(50 * j * 4.5);

					updateCmmtBtn.setOnAction((ActionEvent e) -> {

						TextInputDialog dialog = new TextInputDialog(labCmmt.getText());

						dialog.setTitle("Update your comment");
						dialog.setHeaderText("type :");
						dialog.setContentText("Comment :");
						Optional<String> result = dialog.showAndWait();
						if (result.isPresent()) {
							// Context context3;
							Comments c3 = new Comments();
							c3 = dao.findComment(c2);
							System.out.println("      " + c3);
							c3.setTimeComment(new Timestamp(System.currentTimeMillis()));
							c3.setContent(result.get());
							c3 = dao.updateComment(c3);
							labCmmt.setText(result.get());
							labCmtTime.setText(timedisplay(c3.getTimeComment().getTime()));

							System.out.println("        " + result.get());
						}

					});

					deleteCmmtBtn.setOnAction((ActionEvent e) -> {
						dao.deleteComment(c2.getIdComment());
						unCmmt.setVisible(false);
						nbCmmt.setText(dao.countNbCmmts(p.getIdPost()) + "  Comments");
					});

					pp.getChildren().addAll(unCmmt);
					j++;
				}

				///////// display cmmts + add field/////

				vbAllCmmt.setLayoutX(510);
				vbAllCmmt.setLayoutY(50 * i * 10);
				String cmmtLayoutBox = "-fx-border-color: black;\n" + "-fx-border-insets: 5;\n"
						+ "-fx-border-width: 3;\n";
				vbAllCmmt.setStyle(cmmtLayoutBox);

				vbAllCmmt.getChildren().addAll(hBoxAddCmmt, pp);

				vbAllCmmt.setVisible(false);

				seCmmtBtn.getSwitchedOn().addListener((a, b, c) -> {
					if (c) {

						// System.out.println(" hh "+seCmmtBtn.getSwitchedOn());
						vbAllCmmt.setVisible(true);
					} else {
						// System.out.println(" hh "+seCmmtBtn.getSwitchedOn());
						vbAllCmmt.setVisible(false);
					}
				});

				HBox hBox = new HBox();

				if (p1.getUser().getId() == u1.getId())
					hBox.getChildren().addAll(deletePostBtn, updateBtn, hblk);
				else
					hBox.getChildren().addAll(likeBtn, hblk);
				//////////////////////// lab time /////////////////
				Label labtime = new Label();

				labtime.setText(timedisplay(p.getPostTime().getTime()));
				////////////////// buttons box ///////

				// hBoxUser labDesc image labtime

				HBox hBnbcmmt = new HBox(seCmmtBtn, nbCmmt);
				VBox h = VBoxBuilder.create().spacing(30.0) // In case you
															// are
															// using
															// HBoxBuilder
						.padding(new Insets(5, 5, 5, 5)).children(hBoxUser, labDesc, image, hBox, labtime, hBnbcmmt)
						.build();

				// h.getChildren().addAll(labtime,labDesc, image);
				h.setAlignment(Pos.CENTER);
				//h.setLayoutX(200);
				//h.setLayoutY(50 * i * 10);
				i++;

				String cssLayout = "-fx-border-color: black;\n" + "-fx-border-insets: 5;\n" + "-fx-border-width: 3;\n"
						+ "-fx-background-color: #d7edfb;";

				h.setStyle(cssLayout);
				// h.setSpacing(30.0);

				an.getChildren().addAll(h, vbAllCmmt);

				an.setLayoutX(100);
			}
			an4.setContent(an);

		}

		return an4;
	}

	@FXML
	public void MakePostAction(ActionEvent event) throws IOException {
		// TODO Autogenerated

		Parent rootAddPst = FXMLLoader.load(getClass().getResource("addPost.fxml"));
		Scene scAddPost = new Scene(rootAddPst);
		Stage s1 = new Stage();
		s1.setScene(scAddPost);

		s1.show();
	}

	public String timedisplay(long c) {
		Date date = new Date();

		long time = date.getTime();
		long diff = time - c;
		long seconds = diff / 1000;
		long minutes = seconds / 60;
		long hours = minutes / 60;
		long days = hours / 24;
		return days + " d " + hours % 24 + " h ";
		// + minutes % 60 + " m " + seconds % 60 + " s ";

	}

	public ScrollPane getAn3() {
		return an3;
	}

	public void setAn3(ScrollPane an3) {
		this.an3 = an3;
	}

	public JFXButton getMakePostBtn() {
		return makePostBtn;
	}

	public void setMakePostBtn(JFXButton makePostBtn) {
		this.makePostBtn = makePostBtn;
	}

	/////////////////////////////////////////////////////// add post //////////

}
