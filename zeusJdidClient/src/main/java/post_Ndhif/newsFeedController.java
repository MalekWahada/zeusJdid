package post_Ndhif;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

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

import ejbService.PostServiceEjbRemote;
import entities.Comments;
import entities.LikedPost;
import entities.Media;
import entities.Post;
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

public class newsFeedController implements Initializable {
	@FXML
	private ScrollPane an1;

	private AnchorPane an;
	// Event Listener on JFXButton[#makePostBtn].onAction

	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		try {
			an1 = (ScrollPane) dis(an1);
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	////////////////////////////////////////////// service mainnews feed return
	////////////////////////////////////////////// function ///////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public Node dis(ScrollPane an2) throws NamingException {

		an = new AnchorPane();

		Context context = new InitialContext();
		String jndi = "/zeusPI-ear/zeusPI-ejb/PostServiceEjb!ejbService.PostServiceEjbRemote";
		PostServiceEjbRemote dao = (PostServiceEjbRemote) context.lookup(jndi);

		////////////////    after SKA ///////////
		User u1 = dao.findUser(3);
		
		ArrayList<Post> lPosts = dao.findallPostsRelatFriend(u1.getId());
		 int i = 1;
		
		
		for (Post p : lPosts) {
			
			if (p instanceof Media) {
				Media p1 = (Media) p;
				/////////////////////// user pic + name
				/////////////////////// //////////////////////////
				Label labNameaUser = new Label();
				labNameaUser.setText(p1.getUser().getFirstname() + " " + p1.getUser().getLastname());

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

				String cssLayoutDeltBtn = "-fx-background-color: linear-gradient(#ff5400, #be1d00);\n"
						+ "-fx-background-radius: 30;\n" + "-fx-background-insets: 0;\n" + " -fx-text-fill: white;";

				Button deletePostBtn = new Button("Delete Post");
				deletePostBtn.setStyle(cssLayoutDeltBtn);
				deletePostBtn.setOnAction(e -> {

					dao.deletePost(p.getIdPost());
				});

				//////////////////////// like/dislike ////////////////////////

				final ToggleButton likeBtn = new ToggleButton("like");

				likeBtn.setStyle("-fx-background-color: #80aaff;");

				likeBtn.setStyle("-fx-background-color: #80aaff;");
				likeBtn.setOnAction((ActionEvent e) -> {
					try {
						Context context1 = new InitialContext();
						String jndi1 = "/zeusPI-ear/zeusPI-ejb/PostServiceEjb!ejbService.PostServiceEjbRemote";
						PostServiceEjbRemote dao1 = (PostServiceEjbRemote) context.lookup(jndi1);

						if (likeBtn.isSelected()) {
							likeBtn.setText("unlike");
							likeBtn.setStyle("-fx-background-color: #ff8080;");
							LikedPost lPost = new LikedPost();
							lPost.setPost(p);
							lPost.setUser(p.getUser());
							dao1.addLike(lPost);

						} else {
							likeBtn.setText("like");
							likeBtn.setStyle("-fx-background-color: #80aaff;");
							LikedPost lPost = new LikedPost();
							lPost.setPost(p);
							lPost.setUser(p.getUser());
							dao1.deleteLike(lPost);
						}

					} catch (NamingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				});

				////////////////////////// update
				////////////////////////// //////////////////////////////////////

				String csslayoutUpd = "-fx-background-color: #c3c4c4,linear-gradient(#d6d6d6 50%, white 100%),radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);\n"
						+ " -fx-background-radius: 30;\n" + " -fx-background-insets: 0,1,1;\n"
						+ "-fx-background-color: #d7edfb;" + " -fx-text-fill: black;\n"
						+ "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );";
				Button updateBtn = new Button("Update");
				updateBtn.setStyle(csslayoutUpd);
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
				
				
				cmmtBtn.setOnAction((ActionEvent e) -> {
					Comments c1 = new Comments();
					c1.setContent(tAreaCmmt.getText());
					
					c1.setUser(u1);
					c1.setTimeComment(new Timestamp(System.currentTimeMillis()));
					c1.setPost(p);
					dao.addComment(c1);
					
					
				});
				
				hBoxAddCmmt.getChildren().addAll(tAreaCmmt, cmmtBtn);
				
				
				
				/////////////   vbox cmmts   ////////////
				VBox pp = new VBox();
				
				
				ArrayList<Comments> lCmmtsPost = dao.findallComments(p.getIdPost());
				
				
				int j = 1;
				for (Comments c2 : lCmmtsPost) {
					
					Label labCmmtUser = new Label();
					labCmmtUser.setText(p1.getUser().getFirstname() + " " + p1.getUser().getLastname());

					Circle cmmtUserPic = new Circle(50, 20, 20);
					Image imgUserCmm = new Image("file:///C://wamp64//www//pi2Images//"
							+ p1.getUser().getPictureURL().replace("localhost:8181//", ""));

					cmmtUserPic.setFill(new ImagePattern(imgUserCmm));
					
					
					HBox vbCmmtUser = new HBox();
					vbCmmtUser.getChildren().addAll(cmmtUserPic,labCmmtUser);
					////////////   content + time cmmt /////////////
					HBox vbseeCmmt = new HBox();
					Label labCmmt = new Label();
					//labCmmt.setDisable(false);
					labCmmt.setText(c2.getContent());
					labCmmt.setBackground(background);
					Label labCmtTime = new Label(timedisplay(c2.getTimeComment().getTime()));
					vbseeCmmt.getChildren().addAll(labCmmt, labCmtTime);

					/////////////////  Cmmt action   /////////
					HBox cmmtActionBox = new HBox();
					
					Button deleteCmmtBtn = new Button("Delete Comment");
					deleteCmmtBtn.setStyle(cssLayoutDeltBtn); 
					
					
					Button updateCmmtBtn = new Button("Update Comment");
					if(c2.getUser().getId()==u1.getId())
					cmmtActionBox.getChildren().addAll(deleteCmmtBtn,updateCmmtBtn);
					
					VBox unCmmt = new VBox();
					unCmmt.getChildren().addAll(vbCmmtUser,vbseeCmmt,cmmtActionBox);
					unCmmt.setStyle("-fx-border-color: black;");
					// vbseeCmmt.setLayoutX(200);
					unCmmt.setLayoutY(50 * j * 4.5);
					
					
					updateCmmtBtn.setOnAction((ActionEvent e) -> {
					
						 TextInputDialog dialog = new TextInputDialog(labCmmt.getText());
						 
					        dialog.setTitle("Update your comment");
					        dialog.setHeaderText("type :");
					        dialog.setContentText("Comment :");
					        Optional<String> result = dialog.showAndWait();
					        if(result.isPresent())
					        {
					        	Context context3;
								try {
									context3 = new InitialContext();
									String jndi3 = "/zeusPI-ear/zeusPI-ejb/PostServiceEjb!ejbService.PostServiceEjbRemote";
						    		PostServiceEjbRemote dao3 = (PostServiceEjbRemote) context3.lookup(jndi3);
						    		Comments c3 = new Comments();
						    		c3 = dao.findComment(c2);
						    		System.out.println("      "+c3);
						    		c3.setTimeComment(new Timestamp(System.currentTimeMillis()));
						    		c3.setContent(result.get());
						    		c3 = dao3.updateComment(c3);
						    		labCmmt.setText(result.get());
						    		labCmtTime.setText(timedisplay(c3.getTimeComment().getTime()));
								} catch (NamingException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
					    		

					        	
					        	System.out.println("        "+ result.get());
					        }
					        
					});
					
					deleteCmmtBtn.setOnAction((ActionEvent e) -> {
						dao.deleteComment(c2.getIdComment());
						unCmmt.setVisible(false);
					});
					
					pp.getChildren().addAll(unCmmt);
					
					j++;
				}
				
		/////////  display cmmts  + add field/////
						
						vbAllCmmt.setLayoutX(510);
						vbAllCmmt.setLayoutY(50 * i * 10);
						String cmmtLayoutBox = "-fx-border-color: black;\n" + "-fx-border-insets: 5;\n"
								+ "-fx-border-width: 3;\n";
						vbAllCmmt.setStyle(cmmtLayoutBox);
				
						vbAllCmmt.getChildren().addAll(hBoxAddCmmt,pp);
				
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
				
				if(p1.getUser().getId()== u1.getId())
				hBox.getChildren().addAll(deletePostBtn, likeBtn, updateBtn, seCmmtBtn);
				else hBox.getChildren().addAll( likeBtn, seCmmtBtn);
				//////////////////////// lab time /////////////////
				Label labtime = new Label();
				labtime.setText(timedisplay(p.getPostTime().getTime()));
				////////////////// buttons box ///////

				// hBoxUser labDesc image labtime

				VBox h = VBoxBuilder.create().spacing(30.0) // In case you
															// are
															// using
															// HBoxBuilder
						.padding(new Insets(5, 5, 5, 5)).children(hBoxUser, labDesc, image, hBox, labtime).build();

				// h.getChildren().addAll(labtime,labDesc, image);
				h.setAlignment(Pos.CENTER);
				h.setLayoutX(200);
				h.setLayoutY(50 * i * 10);
				i++;
				
				
				
				String cssLayout = "-fx-border-color: black;\n" + "-fx-border-insets: 5;\n" + "-fx-border-width: 3;\n"
						+ "-fx-background-color: #d7edfb;";

				h.setStyle(cssLayout);
				// h.setSpacing(30.0);

				an.getChildren().addAll(h,vbAllCmmt);

			}
			an1.setContent(an);
			System.out.println("   anchor        \\\\\\  "+an1);
			// an1.setFitToHeight(true);
			// an1.setFitToWidth(true);
		}

		return an1;
	}

	// @FXML
	// public void MakePostAction(ActionEvent event) throws IOException {
	// // TODO Autogenerated
	//
	// Image image1 = new Image(getClass().getResourceAsStream("makePost.png"));
	// makePostBtn.setGraphic(new ImageView(image1));
	// Parent rootAddPst =
//	 FXMLLoader.load(getClass().getResource("addPost.fxml"));
//	 Scene scAddPost = new Scene(rootAddPst);
//	 Stage s1 = new Stage();
//	 s1.setScene(scAddPost);
//	 s1.show();
//	 }

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

}
