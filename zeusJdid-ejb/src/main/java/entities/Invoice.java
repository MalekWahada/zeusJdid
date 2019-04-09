package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Invoice
 *
 */
@Entity

public class Invoice implements Serializable {
	@Id
	private String Id;
	@ManyToOne
	private Entreprise entreprise;
	@ManyToOne
	private PremiumOffer premiumOffer;
	@ManyToOne
	private Pack pack;

	private String state;
	private int type;
	private static final long serialVersionUID = 1L;

	public Invoice() {
		super();
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
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

	public Pack getPack() {
		return pack;
	}

	public void setPack(Pack pack) {
		this.pack = pack;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
   
}
