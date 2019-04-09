package entities;

import java.io.Serializable;
import java.lang.Integer;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: StatUser
 *
 */
@Entity

public class StatUser implements Serializable {

	   
	@Id
	private Integer idStat;
	@ManyToOne
	private User user;
	@ManyToOne
	private EmplomentOffer joboff;
	private String localisation ;
	private static final long serialVersionUID = 1L;
	
	public String getLocalisation() {
		return localisation;
	}
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}
	public StatUser() {
		super();
	}   
	public Integer getIdStat() {
		return this.idStat;
	}

	public void setIdStat(Integer idStat) {
		this.idStat = idStat;
	}   
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}   
	public EmplomentOffer getJoboff() {
		return this.joboff;
	}

	public void setJoboff(EmplomentOffer joboff) {
		this.joboff = joboff;
	}
   
}
