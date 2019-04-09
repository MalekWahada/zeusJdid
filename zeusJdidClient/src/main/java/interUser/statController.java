/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interUser;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ejbService.UserServiceEjbRemote;
import ejbService.tettRemote;
import entities.EmplomentOffer;
import entities.Skill;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;

import javafx.scene.control.ChoiceBox;
/**
 * FXML Controller class
 *
 * @author jassa
 */
public class statController implements Initializable {

    @FXML
    private AnchorPane anpane;
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
    private Label in;
    @FXML
    private Label out;
 // int userid=AcceuilController.usconnected.getId();
    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
    	
    		
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
    slist = dao.getSkill(2);
    skilllist = dao.findjobskill(a);
    System.out.println(slist.get(0).getName()+slist.get(1).getName());
    System.out.println(skilllist);
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
    
}
