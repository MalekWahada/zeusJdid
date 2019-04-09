package entities;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.sql.Timestamp;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Friend
 *
 */
@Entity

public class Friend implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer Id;
	private String accepted;
	@ManyToOne
    @JoinColumn(name="idfriendsent", referencedColumnName="id")
	private User friendsent;
	@ManyToOne
	@JoinColumn(name="idfriendreciv", referencedColumnName="id")
	private User friendreciver;
	private Timestamp daterecept;
	
	private static final long serialVersionUID = 1L;

	public Friend() {
		super();
	}   
	public Integer getId() {
		return this.Id;
	}

	public void setId(Integer Id) {
		this.Id = Id;
	}   
	public String getAccepted() {
		return this.accepted;
	}

	public void setAccepted(String accepted) {
		this.accepted = accepted;
	}
	public User getFriendsent() {
		return friendsent;
	}
	public void setFriendsent(User friendsent) {
		this.friendsent = friendsent;
	}
	public User getFriendreciver() {
		return friendreciver;
	}
	public void setFriendreciver(User friendreciver) {
		this.friendreciver = friendreciver;
	}
	public Timestamp getDaterecept() {
		return daterecept;
	}
	public void setDaterecept(Timestamp daterecept) {
		this.daterecept = daterecept;
	}
   
}
