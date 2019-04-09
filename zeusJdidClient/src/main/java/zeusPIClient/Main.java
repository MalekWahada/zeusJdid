package zeusPIClient;

import java.util.ArrayList;
import java.util.List;

import javax.jws.soap.SOAPBinding.Use;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ejbService.PostServiceEjbRemote;
import entities.*;
import javafx.geometry.Pos;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			Context context1 = new InitialContext();
			String jndi1 = "zeusPI-ear/zeusPI-ejb/PostServiceEjb!ejbService.PostServiceEjbRemote";
			PostServiceEjbRemote dao1 = (PostServiceEjbRemote) context1.lookup(jndi1);

			////////////////// usr service main ////////////////////

			// User u1 = new User(); u1.setFirstname("mikalov");
			// u1 = dao.addUser(u1);
			//
			// User u2 = new User();u2.setFirstname("milka ");
			// u2 = dao.addUser(u2);
			//
			// User u4 = new User();u4.setFirstname("milka ");
			// u4 = dao.addUser(u4);
			//
			//// User u5 = u4; u5.setId(null);
			//// u5 = dao.addUser(u5);
			//
			// System.out.println(" userrrrrrr "+u4);
			//
			// u4.setFirstname(" hamza ");
			// System.out.println(" userrrrrrr "+u4);
			// u4 = dao.updateUser(u4);
			// System.out.println(" userrrrrrr "+u4);
			// dao.deleteUser(u4);
			//
			//
			//
			// System.out.println(" find u2 "+u2);
			// u2 = dao.findUser(u2);
			// System.out.println(" finded u2 "+u2);

			// List<User> userList = dao.findAllUsers();
			// System.out.println("result size : " + userList.size());
			// int i = 0;
			// for (User user:userList) {
			// System.out.println((i+1)+". "+user.getId()+" "
			// +user.getFirstname());
			// i++;
			// }
			////////////////// Post service main ////////////////////
			// Media media = new Media();
			// media.setDescription("tryy1");
			// media.setPath_url("");

			// List<Comments> ll= dao.findallComments(3);
			//
			// int i = 0;
			// if(ll == null)
			// System.out.println(" empty ");
			// else{
			// System.out.println("result size : " + ll.size());
			// for (Comments user:ll) {
			// System.out.println((i+1)+". "+user.getIdComment()+" "
			// +user.getContent());
			// i++;
			// } }

			User user = new User();
			user.setId(3);
			user.setFirstname("mlk");
			user.setLastname("mlk");
			// ArrayList<Post> list = dao.findallPostsRelatFriend(user.getId());
			//
			// System.out.println(list.size());
			// int i=1;
			// for (Post u : list) {
			//
			// System.out.println(" "+i+" "+u.getIdPost()); i++;
			//
			// }

			// Post post = new Post();post.setUser(user);post.setIdPost(3);
			// long j = dao1.countPostLike(3);
			// System.out.println(" kkkkkk "+j);
			// LikedPost lPost = dao1.findlikePostUser(1,3);

			// System.out.println(" lposssssssssst "+lPost.toString());

			// System.out.println(dao1.findAllNoFriend(3).size()+" ;;;;;;;;;
			// "+dao1.findAllNoFriend(3).toString());
			// ArrayList<User> listuse = new ArrayList<User>();
			// listuse = dao1.findAllNoFriend(3);
			// System.out.println(listuse.size()); 
// 
//			ArrayList<Post> lPosts = dao1.findallPostsRelatFriend(3);
//			System.out.println("    jjjjjjjjjjj    " + lPosts.size()); 
//			int i = 1;
//			
////			for (User l : lPosts) {
////
////				System.out.println(" " + i + "  idpost : "+l.getId()+ " mlk :  " + l.getFirstname());
////				i++;
////
////			}
//			
//			for (Post l : lPosts) {
//
//				System.out.println(" " + i + "  idpost : "+l.getIdPost()+ " mlk :  " + l.getUser().getFirstname()+"  time : "+l.getPostTime());
//				i++;
//
//			}
			
			
			
			/////////////////////  notif
			
//			ArrayList<Notification> lNotifications = dao1.getAllNotif(3);
//			System.out.println("    jjjjjjjjjjj    " + lNotifications.size()); 
//			int i = 1;
			
//			for (User l : lPosts) {
//
//				System.out.println(" " + i + "  idpost : "+l.getId()+ " mlk :  " + l.getFirstname());
//				i++;
//
//			}
			
//			for (Notification l : lNotifications) {
//
//				System.out.println(" " + i +"sender "+ l.getIdNotif());
//				i++;
//
//			}
//
			
			Notification not = new Notification();
			not.setContentNotif("kkkkkkkkkkkkkkkkk");
			not = dao1.addNotification(not);
			System.out.println("      "+not.toString());
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

	}

}
