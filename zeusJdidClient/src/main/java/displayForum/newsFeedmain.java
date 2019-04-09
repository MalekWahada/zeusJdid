package displayForum;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sound.midi.ControllerEventListener;

import UsrInterface.Upload;
import ejbService.PostServiceEjbRemote;
import entities.Comments;
import entities.LikedPost;
import entities.Media;
import entities.Post;
import entities.User;
import javafx.application.Application;
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

public class newsFeedmain extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException, NamingException {

		AnchorPane an = new AnchorPane();

		/////////////////////////////////// add post /////////////////////
		Button makePostBtn = new Button();
		Image image1 = new Image(getClass().getResourceAsStream("makePost.png"));
		makePostBtn.setGraphic(new ImageView(image1));
		makePostBtn.setOnAction(e -> {

			try {
				Parent rootAddPst = FXMLLoader.load(getClass().getResource("addPost.fxml"));
				Scene scAddPost = new Scene(rootAddPst);
				Stage s1 = new Stage();
				s1.setScene(scAddPost);
				s1.show();

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		makePostBtn.setAlignment(Pos.CENTER);
		an.getChildren().add(makePostBtn);

		///////////////////////////////////// service /////////////////////
		Context context = new InitialContext();
		String jndi = "zeusPI-ear/zeusPI-ejb/PostServiceEjb!ejbService.PostServiceEjbRemote";
		PostServiceEjbRemote dao = (PostServiceEjbRemote) context.lookup(jndi);

		ArrayList<Post> lPosts = dao.findallPosts();
		int i = 1;
		for (Post p : lPosts) {

			if (p instanceof Media) {

				Media p1 = (Media) p;

				/////////////////////// description + timePost
				Label labDesc = new Label();
				Label labtime = new Label();
				///////////////////// adjust time and date

				///////////////////// label to time and decription
				///////////////////// ////////////////
				labtime.setText(timedisplay(p.getPostTime().getTime()));

				labDesc.setText(p1.getDescription());
				Color col = Color.rgb(205, 205, 205);
				CornerRadii corn = new CornerRadii(10);
				Background background = new Background(new BackgroundFill(col, corn, Insets.EMPTY));
				labDesc.setBackground(background);

				///////////////////////// image ///////////////////////
				ImageView image = new ImageView();
				image.setFitWidth(250);
				image.setFitHeight(150);
				if (p1.getPath_url() != null) {
					Image imag = new Image(
							"file:///C://wamp64//www//pi2Images//" + p1.getPath_url().replace("localhost:8181//", ""));

					image.setImage(imag);
				}

				Button deletePostBtn = new Button("Delete Post");
				deletePostBtn.setOnAction(e -> {

					dao.deletePost(p.getIdPost());
				});

				//////////////// like button ///////////

				final ToggleButton likeBtn = new ToggleButton("like");
				likeBtn.setOnAction((ActionEvent e) -> {
					if (likeBtn.isSelected()) {
						likeBtn.setText("unlike");
						LikedPost lPost = new LikedPost();
						lPost.setPost(p);
						lPost.setUser(p.getUser());
						dao.addLike(lPost);

					} else {
						likeBtn.setText("like");
						LikedPost lPost = new LikedPost();
						lPost.setPost(p);
						lPost.setUser(p.getUser());
						dao.deleteLike(lPost);
					}
				});

				////////////////////// + post /////////////////////
				Button updateBtn = new Button("Update");
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

				////////////////////////////////// comments button
				////////////////////////////////// ///////////////////////

				//////////////////////////////// see comments
				//////////////////////////////// //////////////////////////
				HBox hBoxComments = new HBox();
				TextField tAreaCmmt = new TextField();

				Color col1 = Color.rgb(128, 255, 255);
				CornerRadii corn1 = new CornerRadii(10);
				Background background1 = new Background(new BackgroundFill(col1, corn1, Insets.EMPTY));
				tAreaCmmt.setBackground(background1);
				tAreaCmmt.setPrefWidth(220);

				Button cmmtBtn = new Button("Comments");
				VBox vbCmmt = new VBox();
				hBoxComments.getChildren().addAll(tAreaCmmt, cmmtBtn);
				//vbCmmt.getChildren().add(hBoxComments);

				//// style and layout/////
				vbCmmt.setLayoutX(510);
				vbCmmt.setLayoutY(50 * 7.5);
				String cmmtLayoutBox = "-fx-border-color: black;\n" + "-fx-border-insets: 5;\n"
						+ "-fx-border-width: 3;\n";
				vbCmmt.setStyle(cmmtLayoutBox);
				
				
				
				

				Button seeCmmt = new Button("see Comments");
				
				seeCmmt.setOnAction((ActionEvent e) -> {

					VBox pp = new VBox();
					
					
					ArrayList<Comments> lCmmtsPost = dao.findallComments(p.getIdPost());
					vbCmmt.getChildren().clear();
					vbCmmt.getChildren().remove(pp);
					
					
					int j = 1;
					for (Comments c : lCmmtsPost) {
						HBox vbseeCmmt = new HBox();
						Label labCmmt = new Label();
						labCmmt.setText(c.getContent());
						labCmmt.setBackground(background);
						Label labCmtTime = new Label(timedisplay(c.getTimeComment().getTime()));
						vbseeCmmt.getChildren().addAll(labCmmt, labCmtTime);

						// vbseeCmmt.setLayoutX(200);
						vbseeCmmt.setLayoutY(50 * j * 4.5);
						pp.getChildren().add(vbseeCmmt);
						
						j++;
					}
//					pp.getChildren().clear();
//					vbCmmt.getChildren().remove(pp);
					
					vbCmmt.getChildren().add(pp);
					pp.getChildren().clear();
					vbCmmt.getChildren().remove(pp);
				});
				
				
				cmmtBtn.setOnAction((ActionEvent e) -> {
					Comments c = new Comments();
					c.setContent(tAreaCmmt.getText());
					User u1 = dao.findUser(3);
					c.setUser(u1);
					c.setTimeComment(new Timestamp(System.currentTimeMillis()));
					c.setPost(p);
					dao.addComment(c);
					
					
				});
				vbCmmt.getChildren().add(hBoxComments);
				
				an.getChildren().add(vbCmmt);
				////////////////////////////////////////////////////////////////////////
				////////////////////////////////////////////////////////////////////////
				HBox hBox = new HBox();
				hBox.getChildren().addAll(deletePostBtn, likeBtn, updateBtn, seeCmmt);
				VBox h = VBoxBuilder.create().spacing(30.0) // In case you are
															// using HBoxBuilder
						.padding(new Insets(5, 5, 5, 5)).children(labtime, labDesc, image, hBox).build();

				// h.getChildren().addAll(labtime,labDesc, image);
				h.setAlignment(Pos.CENTER);
				h.setLayoutX(200);
				h.setLayoutY(50 * i * 7.5);
				i++;
				String cssLayout = "-fx-border-color: red;\n" + "-fx-border-insets: 5;\n" + "-fx-border-width: 3;\n"
						+ "-fx-background-color: #d7edfb;";

				h.setStyle(cssLayout);
				// h.setSpacing(30.0);

				an.getChildren().add(h);

			}

		}

		ScrollPane an1 = new ScrollPane(an);
		primaryStage.setTitle("uzzzzzzzzzerrrrr");
		primaryStage.setScene(new Scene(an1, 800, 600));
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public String timedisplay(long c) {
		Date date = new Date();

		long time = date.getTime();
		long diff = time - c;
		long seconds = diff / 1000;
		long minutes = seconds / 60;
		long hours = minutes / 60;
		long days = hours / 24;
		return days + " d  " + hours % 24 + "  h  " + minutes % 60 + "  m  " + seconds % 60 + "  s  ";

	}

}
