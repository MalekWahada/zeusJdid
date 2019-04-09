package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Pack
 *
 */
@Entity

public class Pack implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer Id;
	
	@ManyToOne
	@JoinColumn(name="idPremiumOffer", referencedColumnName="id")
	private PremiumOffer premiumOffer;
	@ManyToOne
	@JoinColumn(name="idReduction", referencedColumnName="id")
	private Reduction reduction;
	
	@OneToMany(mappedBy="pack")
	private List<Invoice> invoices;
	
	private static final long serialVersionUID = 1L;

	public Pack() {
		super();
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public PremiumOffer getPremiumOffer() {
		return premiumOffer;
	}

	public void setPremiumOffer(PremiumOffer premiumOffer) {
		this.premiumOffer = premiumOffer;
	}

	public Reduction getReduction() {
		return reduction;
	}

	public void setReduction(Reduction reduction) {
		this.reduction = reduction;
	}

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}
	
	
   
}
