package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Entreprise
 *
 */
@Entity

public class Entreprise implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer Id;
	private String email;
	private String name;
	private String Speciality;
	private int nb_employe;
	private String bio;
	private int allowed_job_offers;
	private boolean account_confirmed;
	
	/////////////////////   mapped entities ////////////////
	@OneToMany(mappedBy="EntrepriseId")
	private List<EntrepriseEvents> EntrepriseEvents;
	@OneToMany(mappedBy = "entreprise")
	private List<Follow> listEntreprise;
	@OneToMany(mappedBy="EntrepriseId")
	private List<EntrepriseTeam> EntrepriseTeam;
	@OneToMany(mappedBy="entreprise")
	private List<Invoice> invoices;
	@OneToMany(mappedBy="entreprise")
	private List<Claim> claims;
	@OneToMany(mappedBy="entreprise")
	private List<Subscription> subscriptions;
	
	private static final long serialVersionUID = 1L;
	
	public List<Follow> getListEntreprise() {
		return listEntreprise;
	}

	public void setListEntreprise(List<Follow> listEntreprise) {
		this.listEntreprise = listEntreprise;
	}

	public List<EntrepriseEvents> getEntrepriseEvents() {
		return EntrepriseEvents;
	}

	public void setEntrepriseEvents(List<EntrepriseEvents> entrepriseEvents) {
		EntrepriseEvents = entrepriseEvents;
	}
	

	public List<EntrepriseTeam> getEntrepriseTeam() {
		return EntrepriseTeam;
	}

	public void setEntrepriseTeam(List<EntrepriseTeam> entrepriseTeam) {
		EntrepriseTeam = entrepriseTeam;
	}
	//******************************** AMAL ENTITIES *****************************************
	//Invoices
	
	
	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}
	

	//Claims
	
	
	public List<Claim> getClaims() {
		return claims;
	}

	public void setClaims(List<Claim> claims) {
		this.claims = claims;
	}
	
	//Subscriptions
	
	
	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public int getAllowed_job_offers() {
		return allowed_job_offers;
	}

	public void setAllowed_job_offers(int allowed_job_offers) {
		this.allowed_job_offers = allowed_job_offers;
	}

	//*****************************************************************************************
	

	public Entreprise() {
		super();
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpeciality() {
		return Speciality;
	}

	public void setSpeciality(String speciality) {
		Speciality = speciality;
	}

	public int getNb_employe() {
		return nb_employe;
	}

	public void setNb_employe(int nb_employe) {
		this.nb_employe = nb_employe;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public boolean isAccount_confirmed() {
		return account_confirmed;
	}

	public void setAccount_confirmed(boolean account_confirmed) {
		this.account_confirmed = account_confirmed;
	}
   
}
