package entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Application
 *
 */
@Entity

public class Application implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private int Id;
	@ManyToOne
	@JoinColumn(name="idEmploymentOffer", referencedColumnName="Id")
	private EmplomentOffer emplomentOffer;
	@ManyToOne
	@JoinColumn(name="idUser", referencedColumnName="Id")
	private User user;
	
	private Date ApplicationDate;
	private static final long serialVersionUID = 1L;

	public Application() {
		super();
	}   
	public int getId() {
		return this.Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}   
	
	public Date getApplicationDate() {
		return this.ApplicationDate;
	}

	public void setApplicationDate(Date ApplicationDate) {
		this.ApplicationDate = ApplicationDate;
	}
	public EmplomentOffer getEmplomentOffer() {
		return emplomentOffer;
	}
	public void setEmplomentOffer(EmplomentOffer emplomentOffer) {
		this.emplomentOffer = emplomentOffer;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
   
}
