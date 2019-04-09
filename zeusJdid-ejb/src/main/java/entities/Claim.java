package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Claim
 *
 */
@Entity

public class Claim implements Serializable {

	@Id
	private Integer Id;
	@ManyToOne
	@JoinColumn(name="idEntreprise", referencedColumnName="id")
	private Entreprise entreprise;
	@ManyToOne
	@JoinColumn(name="idUser", referencedColumnName="id")
	private User user;
	private int type;
	private String content;
	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}
	
	
	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Claim() {
		super();
	}
   
}
