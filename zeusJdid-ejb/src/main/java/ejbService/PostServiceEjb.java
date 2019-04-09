package ejbService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.soap.SOAPBinding.Use;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.constraints.Null;

import entities.Comments;
import entities.EmplomentOffer;
import entities.Friend;
import entities.LikedPost;
import entities.Notification;
import entities.Post;
import entities.User;

/**
 * Session Bean implementation class PostServiceEjb
 */
@Stateless
@LocalBean
public class PostServiceEjb implements PostServiceEjbRemote, PostServiceEjbLocal {

	@PersistenceContext(unitName = "zeusJdid-ejb")
	EntityManager em;
//zeusJdid-ejb
	/**
	 * Default constructor.
	 */
	public PostServiceEjb() {
		// TODO Auto-generated constructor stub
	}

	///////////////////// user service //////////////////////
	@Override
	public User addUser(User u) {
		// TODO Auto-generated method stub

		em.persist(u);
		em.flush();

		return u;
	}

	@Override
	public User updateUser(User u) {
		// TODO Auto-generated method stub

		em.clear();
		return em.merge(u);
	}

	@Override
	public void deleteUser(User u) {
		// TODO Auto-generated method stub
		em.clear();
		User u1 = em.find(User.class, u.getId());
		em.remove(u1);

	}

	@Override
	public User findUser(int u) {
		// TODO Auto-generated method stub
		em.clear();
		return em.find(User.class, u);
	}

	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub

		// Query qr = em.createQuery(" select Firstname from user u");

		List<User> result = (List<User>) em.createQuery("from User").getResultList();
		return result;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	///////////////////// post service
	//////////////////////////////////////////////////////////////////////////////////////// /////////////////////////////////////////////////////

	@Override
	public Post addPost(Post p) {
		// TODO Auto-generated method stub
		em.persist(p);
		em.flush();
		return p;
	}

	@Override
	public Post updatePost(Post p) {
		// TODO Auto-generated method stub

		em.clear();
		return em.merge(p);
	}

	@Override
	public void deletePost(int p) {
		// TODO Auto-generated method stub
		em.clear();
		Post p1 = em.find(Post.class, p);
		em.remove(p1);
	}

	@Override
	public Post findPost(int p) {
		// TODO Auto-generated method stub

		return em.find(Post.class, p);
	}

	@Override
	public ArrayList<Post> findallPosts() {
		// TODO Auto-generated method stub List<User> result = (List<User>)
		// em.createQuery("from User").getResultList();
		ArrayList<Post> result = (ArrayList<Post>) em.createQuery("from Post").getResultList();
		return result;
	}

	@Override
	public ArrayList<Post> findallPostsRelatFriend(int i) {
		// TODO Auto-generated method stub

		ArrayList<User> lUsers = new ArrayList<User>();
		User u = em.find(User.class, i);
		lUsers.add(u);

		ArrayList<Friend> lfriends = (ArrayList<Friend>) em.createQuery("FROM  Friend  WHERE idfriendsent=" + i+"AND accepted='friend'").getResultList();
		for (Friend f : lfriends) {

			lUsers.add(f.getFriendreciver());
		}
		ArrayList<Friend> lfriends1 = (ArrayList<Friend>) em.createQuery("FROM  Friend  WHERE idfriendreciv=" + i+"AND accepted='friend'").getResultList();
		for (Friend f : lfriends1) {

			lUsers.add(f.getFriendsent());
		}
		
		ArrayList<Post> lposts = new ArrayList<Post>();
		ArrayList<Post> lposts1 = new ArrayList<Post>();
		for(User user:lUsers)
		{// lposts1 = new ArrayList<Post>();
			lposts.clear();
			lposts = (ArrayList<Post>) em.createQuery("from Post where User_id=" +user.getId()).getResultList();
			lposts1.addAll(lposts);
			lposts.clear();
		}
		

		return lposts1;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	///////////////////// Comments service
	//////////////////////////////////////////////////////////////////////////////////////// ////////////////////////////////////////////////
	@Override
	public Comments addComment(Comments c) {
		// TODO Auto-generated method stub
		em.persist(c);
		em.flush();
		return c;
	}

	@Override
	public Comments updateComment(Comments c) {
		// TODO Auto-generated method stub
		em.clear();
		return em.merge(c);
	}

	@Override
	public void deleteComment(int c) {
		// TODO Auto-generated method stub
		em.clear();
		Comments p1 = em.find(Comments.class, c);
		em.remove(p1);
	}

	@Override
	public Comments findComment(Comments c) {
		// TODO Auto-generated method stub
		return em.find(Comments.class, c.getIdComment());
	}

	@Override
	public ArrayList<Comments> findallComments(int postId) {
		// TODO Auto-generated method stub
		em.flush();
		em.clear();
		ArrayList<Comments> result = (ArrayList<Comments>) em.createQuery("from Comments where idPost =" + postId)
				.getResultList();
		return result;
	}
	
	@Override
	public long countNbCmmts(int p) {
		// TODO Auto-generated method stub
		em.flush();
		em.clear();
		Query query = em.createQuery("SELECT count(*) FROM Comments WHERE idPost=" + p);
		long count = (long) query.getSingleResult();
		return count;
	}

	///////////////////// likes service //////////////////////
	@Override
	public void addLike(LikedPost l) {
		// TODO Auto-generated method stub

		em.persist(l);
		em.flush();

	}

	@Override
	public void deleteLike(LikedPost l) {
		// TODO Auto-generated method stub

		em.flush();
		em.clear();
		Query query = em.createQuery("delete from LikedPost where idpost = '" + l.getPost().getIdPost()
				+ "' and iduser = '" + l.getUser().getId() + "'");
		query.executeUpdate();

	}

	@Override
	public long countPostLike(int p) {
		// TODO Auto-generated method stub
		em.flush();
		em.clear();
		Query query = em.createQuery("SELECT count(*) FROM LikedPost WHERE idPost=" + p);
		long count = (long) query.getSingleResult();
		return count;
	}

	@Override
	public LikedPost findlikePostUser(int post, int u) {
		// TODO Auto-generated method stub

		em.flush();
		em.clear();
		LikedPost result = new LikedPost();
		try{
		 result = (LikedPost) em.createQuery("from LikedPost where idPost =" + post + "and idUser=" + u)
				.getSingleResult();}
		catch (NoResultException nre){
			//Ignore this because as per your logic this is ok!
			}

		if (result == null) {
			return null;
		}
		return result;
	}
//////////////////////////////////////////////   recommondation + suggestions part
	
	@Override
	public ArrayList<User> findAllFrind(int u) {
		// TODO Auto-generated method stub
		em.flush();
		em.clear();
		ArrayList<User> lUsers = new ArrayList<User>();
		

		ArrayList<Friend> lfriends = (ArrayList<Friend>) em.createQuery("FROM  Friend  WHERE idfriendsent=" + u+"AND accepted='friend'").getResultList();
		for (Friend f : lfriends) {

			lUsers.add(f.getFriendreciver());
		}
		ArrayList<Friend> lfriends1 = (ArrayList<Friend>) em.createQuery("FROM  Friend  WHERE idfriendreciv=" + u+"AND accepted='friend'").getResultList();
		for (Friend f : lfriends1) {

			lUsers.add(f.getFriendsent());
		}
		return lUsers;
	}

	@Override
	public ArrayList<User> findAllNoFriend(int u) {
		em.flush();
		em.clear();
		// TODO Auto-generated method stub
		ArrayList<User> lUsers1 = new ArrayList<User>();
		User user = em.find(User.class, u);
		ArrayList<Friend> lfriends = (ArrayList<Friend>) em.createQuery("FROM  Friend  WHERE idfriendsent=" + u+"AND accepted='friend'").getResultList();
		for (Friend f : lfriends) {
			lUsers1.add(f.getFriendreciver());
		}
		ArrayList<Friend> lfriends1 = (ArrayList<Friend>) em.createQuery("FROM  Friend  WHERE idfriendreciv=" + u+"AND accepted='friend'").getResultList();
		for (Friend f : lfriends1) {
			lUsers1.add(f.getFriendsent());
		}
		
		ArrayList<User> lUsers = (ArrayList<User>) em.createQuery("FROM  User  WHERE id<>" + u).getResultList();
		lUsers.removeAll(lUsers1);
		return lUsers;
	}
	
	
	
	
//////////////////////////////////////////////  jobs service

	@Override
	public ArrayList<EmplomentOffer> findAllJobs() {
		// TODO Auto-generated method stub
		ArrayList<EmplomentOffer> loffers = (ArrayList<EmplomentOffer>) em.createQuery("FROM  EmplomentOffer where confirmed="+1)
				.getResultList();
		
		return loffers;
	}

	@Override
	public ArrayList<EmplomentOffer> searchJobsOnTitle(String s) {
		// TODO Auto-generated method stub
		ArrayList<EmplomentOffer> loffers = (ArrayList<EmplomentOffer>) em.createQuery("FROM  EmplomentOffer where jobtitle like '%"+s+"%'")
				.getResultList();
		
		return loffers;
	}

	@Override
	public Notification addNotification(Notification notification) {
		// TODO Auto-generated method stub
		em.persist(notification);
		em.flush();
		return notification;
	}

	@Override
	public void removeNotification(int u) {
		// TODO Auto-generated method stub
		em.clear();
		Notification u1 = em.find(Notification.class, u);
		em.remove(u1);
	}

	@Override
	public ArrayList<Notification> getAllNotif(int i) {
		// TODO Auto-generated method stub
		ArrayList<Notification> loffers = (ArrayList<Notification>) em.createQuery("FROM  Notification where userReceiverNtf_Id ="+i)
				.getResultList();
		
		return loffers;
	}

	@Override
	public ArrayList<Notification> getAllNotReadNotif(int i) {
		// TODO Auto-generated method stub
		ArrayList<Notification> loffers = (ArrayList<Notification>) em.createQuery("FROM  Notification where readNotif = 1 and userReceiverNtf_Id ="+i)
				.getResultList();
		
		return loffers;
	}

	@Override
	public Notification markNotifAsRead(Notification n) {
		// TODO Auto-generated method stub
		
		em.clear();
		
		
		return em.merge(n);
	}

	@Override
	public Notification findNotif(int i) {
		// TODO Auto-generated method stub
		em.clear();
		return em.find(Notification.class, i);
	}

	@Override
	public long countReadOrNot(int p) {
		// TODO Auto-generated method stub
		em.flush();
		em.clear();
		Query query = em.createQuery("SELECT count(*) FROM Notification WHERE readNotif = 1 and userReceiverNtf_Id ="+p);
		long count = (long) query.getSingleResult();
		return count;
	}
//"SELECT count(*) FROM Comments WHERE idPost=" + p
	// SELECT * FROM `emplomentoffer` WHERE JobTitle like '%kk%'

}
