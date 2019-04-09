package entities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Subscription
 *
 */
@Entity

public class Subscription implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer Id;
	@ManyToOne
	@JoinColumn(name="idEntreprise", referencedColumnName="Id")
	private Entreprise entreprise;
	@ManyToOne
	@JoinColumn(name="idPremiumOffer", referencedColumnName="Id")
	private PremiumOffer premiumOffer;
	private static final long serialVersionUID = 1L;
	private Timestamp debutDate = new Timestamp(System.currentTimeMillis());
	private Date finalDate;
	
	
	
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

	public PremiumOffer getPremiumOffer() {
		return premiumOffer;
	}

	public void setPremiumOffer(PremiumOffer premiumOffer) {
		this.premiumOffer = premiumOffer;
	}

	public Timestamp getDebutDate() {
		return debutDate;
	}

	public void setDebutDate(Timestamp debutDate) {
		this.debutDate = debutDate;
	}

	public Date getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}

	public Subscription() {
		super();
	}
   
}
