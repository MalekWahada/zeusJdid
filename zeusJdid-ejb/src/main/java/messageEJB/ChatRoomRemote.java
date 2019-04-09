package messageEJB;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.ejb.Remote;

import entities.Friend;
import entities.Message;
import entities.User;

@Remote
public interface ChatRoomRemote {
Message addMessage(Message m);
ArrayList<User> findAllFrind(int u);
ArrayList<Message> getUserMessages(int trans,int recv);
ArrayList<User> findSearchedFrinds(String st);

/////// user  //////

public User findUser(int u);

}
