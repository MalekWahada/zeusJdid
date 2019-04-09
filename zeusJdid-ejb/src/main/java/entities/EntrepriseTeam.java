package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: EntrepriseTeam
 *
 */
@Entity
@Table(name="EntrepriseTeam")

public class EntrepriseTeam implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Id;
	private String FirstName;
	private String LastName;
	private int Role;
	private String Email;
	@ManyToOne
	private Entreprise EntrepriseId;
	@OneToMany(mappedBy="PostedBy")
	private List<EmplomentOffer> listEmplomentOffer;
	@OneToMany(mappedBy="entrepriseTeam")
	private List<Interview> listInterview;
	
	
	

	
	public Entreprise getEntrepriseId() {
		return EntrepriseId;
	}
	public void setEntrepriseId(Entreprise entrepriseId) {
		EntrepriseId = entrepriseId;
	}

	private static final long serialVersionUID = 1L;

	public EntrepriseTeam() {
		super();
	}   
	public int getId() {
		return this.Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}   
	public String getFirstName() {
		return this.FirstName;
	}

	public void setFirstName(String FirstName) {
		this.FirstName = FirstName;
	}   
	public String getLastName() {
		return this.LastName;
	}

	public void setLastName(String LastName) {
		this.LastName = LastName;
	}   
	public int getRole() {
		return this.Role;
	}

	public void setRole(int Role) {
		this.Role = Role;
	}   
	public String getEmail() {
		return this.Email;
	}

	public void setEmail(String Email) {
		this.Email = Email;
	}
   
}
