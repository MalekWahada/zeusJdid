package messageEJB;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Friend;
import entities.Message;
import entities.User;

/**
 * Session Bean implementation class ChatRoom
 */
@Stateless
@LocalBean
public class ChatRoom implements ChatRoomRemote, ChatRoomLocal {

	
	@PersistenceContext(unitName = "zeusJdid-ejb")
	EntityManager em;
    /**
     * Default constructor. 
     */
    public ChatRoom() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Message addMessage(Message m) {
		// TODO Auto-generated method stub
		
		em.persist(m);
		em.flush();
		return m;
	}

	@Override
	public ArrayList<User> findAllFrind(int u) {
		// TODO Auto-generated method stub
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
	public ArrayList<Message> getUserMessages(int trans, int recv) {
		
		ArrayList<Message> lmsgs = (ArrayList<Message>) em.createQuery("FROM  Message  WHERE idTransmitter=" + trans+ "and idReceiver="+recv+ "order by timeMessage desc")
				.getResultList();
		
		return lmsgs;
	}

//////////////////// to check later  ////////////////////////
	@Override
	public ArrayList<User> findSearchedFrinds(String st) {
		// TODO Auto-generated method stub
//ArrayList<User> lUsers = new ArrayList<User>();
//		
//
//		ArrayList<Friend> lfriends = (ArrayList<Friend>) em.createQuery("FROM  Friend  WHERE idfriendsent=" + u+"AND accepted='accepted'").getResultList();
//		for (Friend f : lfriends) {
//
//			lUsers.add(f.getFriendreciver());
//		}
//		ArrayList<Friend> lfriends1 = (ArrayList<Friend>) em.createQuery("FROM  Friend  WHERE idfriendreciv=" + u+"AND accepted='accepted'").getResultList();
//		for (Friend f : lfriends1) {
//
//			lUsers.add(f.getFriendsent());
//		}
		return null;
	}

	/////////////////////   user //////////////
	
	@Override
	public User findUser(int u) {
		// TODO Auto-generated method stub
		em.clear();
		return em.find(User.class, u);
	}
	
	
	
}
