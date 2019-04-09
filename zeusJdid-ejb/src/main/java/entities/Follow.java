package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Follow
 *
 */
@Entity

public class Follow implements Serializable {

	   
	@Id
	private int id;
	@ManyToOne
	@JoinColumn(name="idUser", referencedColumnName="id")
	private User user;
	@ManyToOne
	@JoinColumn(name="idEntreprise", referencedColumnName="Id")
	private Entreprise entreprise;
	
	
	private static final long serialVersionUID = 1L;

	public Follow() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Entreprise getEntreprise() {
		return entreprise;
	}
	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}
   
}
