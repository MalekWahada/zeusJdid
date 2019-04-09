package entities;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.String;
import java.sql.Timestamp;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Notification
 *
 */
@Entity

public class Notification implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idNotif;
	private Timestamp notifTime;
	private String contentNotif;
	private Boolean readNotif;
	@ManyToOne
	private User userSenderNtf;
	@ManyToOne
	private User userReceiverNtf;
	private static final long serialVersionUID = 1L;

	public Notification() {
		super();
	}   
	public int getIdNotif() {
		return this.idNotif;
	}

	public void setIdNotif(int idNotif) {
		this.idNotif = idNotif;
	}   
	public Timestamp getNotifTime() {
		return this.notifTime;
	}

	public void setNotifTime(Timestamp notifTime) {
		this.notifTime = notifTime;
	}   
	public String getContentNotif() {
		return this.contentNotif;
	}

	public void setContentNotif(String contentNotif) {
		this.contentNotif = contentNotif;
	}   
	public Boolean getReadNotif() {
		return this.readNotif;
	}

	public void setReadNotif(Boolean readNotif) {
		this.readNotif = readNotif;
	}
	public User getUserSenderNtf() {
		return userSenderNtf;
	}
	public void setUserSenderNtf(User userSenderNtf) {
		this.userSenderNtf = userSenderNtf;
	}
	public User getUserReceiverNtf() {
		return userReceiverNtf;
	}
	public void setUserReceiverNtf(User userReceiverNtf) {
		this.userReceiverNtf = userReceiverNtf;
	}
	
   
}
