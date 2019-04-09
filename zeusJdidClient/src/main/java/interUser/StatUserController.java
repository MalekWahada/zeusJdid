
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interUser;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ejbService.tettRemote;
import entities.EmplomentOffer;
import entities.Skill;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jassa
 */
public class StatUserController implements Initializable {

    @FXML
    private ImageView homeImg;
    @FXML
    private ImageView inboxImg;
    @FXML
    private ImageView jobOfferImg;
    @FXML
    private ImageView notifImg;
    @FXML
    private ImageView contactImg;
    @FXML
    private MenuButton dropDownProfile;
    @FXML
    private MenuItem seeProfileAction;
    @FXML
    private MenuItem jobApplications;
    @FXML
    private MenuItem logOut;
    @FXML
    private Circle profileImg;
    @FXML
    private ImageView statImg;
    @FXML
    private AnchorPane anpane;
    @FXML
    private Label out;
    @FXML
    private Label in;
    @FXML
    private BarChart<?, ?> chart;
    @FXML
    private Button select;
    @FXML
    private Button Refresh;
    @FXML
    private ChoiceBox<String> getlist;
    @FXML
    private PieChart pie;
    @FXML
    private Button back;
    @FXML
    private Button loc;
    @FXML
    private JFXTextField searchTF;
    @FXML
    private JFXButton ref1;
    @FXML
    private JFXButton sel1;
    @FXML
    private JFXButton loca1;

  
    /**
     * Initializes the controller class.
     */
    public void initialize1(URL url, ResourceBundle rb) {
    	
    		
    }    

    @FXML
    private void select(ActionEvent event) throws NamingException {
    	
		ObservableList<PieChart.Data> lis =
                FXCollections.observableArrayList();
		  lis.clear();
    	String	a=((String) getlist.getValue());
    	Context context = new InitialContext();
    	String jndi = "zeusPI-ear/zeusPI-ejb/tett!ejbService.tettRemote";
    tettRemote dao = (tettRemote) context.lookup(jndi);
    List<Skill> slist; 
    String skilllist; 
    slist = dao.getSkill(AcceuilController.usconnected.getId());
    skilllist = dao.findjobskill(a);
  
   
     if (skilllist.contains("sym"))
     System.out.println("yeeeeeeees");
     else
     { System.out.println("noooooo");}
   	out.setText("Skills needed: "+skilllist);
   	String b="My skills:";
   	int nb=slist.size();
   	Double t = (double) (100/nb);
    for (int i=0;i<slist.size();i++){
    				b=b+" "+slist.get(i).getName();
    				
    lis.add(  new PieChart.Data(slist.get(i).getName(), t));}
  //  in.setText(b);
    		
    		pie.setTitle("My Skills");
            pie.setData(lis);
        //  anpane.getChildren().add(pie);
            double k=0,chance=0;
          
            for (int i=0;i<slist.size();i++){
            	String s=slist.get(i).getName();
            	 int lon=0;
            			 lon=+s.length();
            	 
            	  if (skilllist.contains(s))
            	  { k=k+1;}
            	  
            	   if((k>=nb)&&(skilllist.length()<lon))
            	  chance=100;
            	
            	  else if(k==0)
            		  chance=0;
            	  else
            		  chance=(double)(k/4)*100;
            }
            
          
          
            
            
            XYChart.Series series1 = new XYChart.Series();
            series1.getData().add(new XYChart.Data<>("Chances to get hire ",chance));
            chart.getData().addAll(series1);
          
    }

    @FXML
    private void locate(ActionEvent event) throws NamingException {
    	Context context = new InitialContext();
		String jndi = "zeusPI-ear/zeusPI-ejb/tett!ejbService.tettRemote";
	tettRemote dao = (tettRemote) context.lookup(jndi);
		
		List<EmplomentOffer> listoff; 
	        listoff = dao.findallJobs();
	    int i=0;
	    String s;
	    
	       for (int j=0;j<listoff.size();j++)
	        {
	    	   ObservableList<String> L = FXCollections.observableArrayList(listoff.get(j).getJobTitle());
	        	
		        s=listoff.get(i).getJobDescr();
		        getlist.getItems().addAll(L);
	        	i++;
	        }
	      //  System.out.println(listoff.size()+"aaaaaaaaa");
	
	      
    }

    @FXML
    private void back(ActionEvent event) {
    }

    @FXML
    private void loc(ActionEvent event) throws IOException {
    	 FXMLLoader loader = new FXMLLoader();
         
         loader.setLocation(getClass().getResource("web.fxml"));
         try{
             loader.load();
         }catch(Exception ex){
             ex.printStackTrace();
         }
         Parent p = loader.getRoot();
         Stage stage = new Stage();
         //stage.initStyle(StageStyle.UNDECORATED);
         stage.setScene(new Scene(p));
         stage.show();
    }

	// Event Listener on ImageView[#homeImg].onMouseClicked
	@FXML
	public void homeImgClicked(MouseEvent event) throws IOException {
		// TODO Autogenerated
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("templateHome/home.fxml"));
		homeImg.getScene().setRoot(root);
		
	}
	// Event Listener on ImageView[#inboxImg].onMouseClicked
	@FXML
	public void inboxImgClicked(MouseEvent event) throws IOException {
		// TODO Autogenerated
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("templateInbox/inbox.fxml"));
		homeImg.getScene().setRoot(root);
	}
	// Event Listener on ImageView[#jobOfferImg].onMouseClicked
	@FXML
	public void jobOfferImgClicked(MouseEvent event) throws IOException {
		// TODO Autogenerated
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("templateJobs/jobs.fxml"));
		homeImg.getScene().setRoot(root);
	}
	// Event Listener on ImageView[#notifImg].onMouseClicked
	@FXML
	public void notifImgClicked(MouseEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("templateNotif/notif.fxml"));
		homeImg.getScene().setRoot(root);	
		}
	// Event Listener on ImageView[#contactImg].onMouseClicked
	@FXML
	public void contactImgAction(MouseEvent event) throws IOException {
		// TODO Autogenerated
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("templateContact/contact.fxml"));
		homeImg.getScene().setRoot(root);	
	}
	// Event Listener on MenuItem[#jobApplications].onAction
	@FXML
	public void jobApplicationsAction(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on MenuItem[#logOut].onAction
	@FXML
	public void logOutAction(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Circle[#profileImg].onMouseClicked
	@FXML
	public void profileImgAction(MouseEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on ImageView[#statImg].onMouseClicked
	@FXML
	public void statImgAction(MouseEvent event) throws IOException {
		// TODO Autogenerated
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("templateStat/stat.fxml"));
		homeImg.getScene().setRoot(root);	
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
    
    
}
