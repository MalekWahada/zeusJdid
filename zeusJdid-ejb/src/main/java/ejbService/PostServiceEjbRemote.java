package ejbService;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.jws.soap.SOAPBinding.Use;

import entities.*;


@Remote
public interface PostServiceEjbRemote {

	/////////////////////     user service    //////////////////
	
	User addUser(User u);
	User updateUser(User u);
	void deleteUser(User u);
	User findUser(int u);
	List<User> findAllUsers();
	
	/////////////////////     Post service    //////////////////
	
	Post addPost(Post u);
	Post updatePost(Post u);
	void deletePost(int u);
	Post findPost(int u);
	ArrayList<Post> findallPosts();
	ArrayList<Post> findallPostsRelatFriend(int i);
	
	/////////////////////     Comments service    //////////////////
	
	Comments addComment(Comments u);
	Comments updateComment(Comments u);
	void deleteComment(int u);
	Comments findComment(Comments u);
	ArrayList<Comments> findallComments(int postId);
	long countNbCmmts(int p);

	/////////////////////     likes service    //////////////////
	
	void addLike(LikedPost u);
	void deleteLike(LikedPost u);
	long countPostLike(int p);
	LikedPost findlikePostUser(int postid,int u);
	
    /////////////////////     recommandations service    //////////////////
	
	ArrayList<User> findAllFrind(int u);
	ArrayList<User> findAllNoFriend(int u);
	
   /////////////////////     Jobs service Display   //////////////////
	ArrayList<EmplomentOffer> findAllJobs();
	ArrayList<EmplomentOffer> searchJobsOnTitle(String s);
	
   /////////////////////     notification   //////////////////
	
	Notification addNotification(Notification notification);
	void removeNotification(int u);
	ArrayList<Notification> getAllNotif(int i);
	ArrayList<Notification> getAllNotReadNotif(int i);
	Notification markNotifAsRead(Notification n);
	Notification findNotif(int i);
	 long countReadOrNot(int p);
	
}
