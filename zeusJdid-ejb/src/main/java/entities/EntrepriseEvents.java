package entities;

import java.io.Serializable;
import java.lang.String;
import java.sql.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: EntrepriseEvents
 *
 */
@Entity

public class EntrepriseEvents implements Serializable {

	   
	@Id
	private int Id;
	@ManyToOne
	private Entreprise EntrepriseId;
	private String EventName;
	private String EventDescription;
	private Date StartDate;
	private Date FinishDate;
	private static final long serialVersionUID = 1L;

	public EntrepriseEvents() {
		super();
	}   
	public int getId() {
		return this.Id;
	}



	public Entreprise getEntrepriseId() {
		return EntrepriseId;
	}
	public void setEntrepriseId(Entreprise entrepriseId) {
		EntrepriseId = entrepriseId;
	}
	public String getEventName() {
		return this.EventName;
	}

	public void setEventName(String EventName) {
		this.EventName = EventName;
	}   
	public String getEventDescription() {
		return this.EventDescription;
	}

	public void setEventDescription(String EventDescription) {
		this.EventDescription = EventDescription;
	}   
	public Date getStartDate() {
		return this.StartDate;
	}

	public void setStartDate(Date StartDate) {
		this.StartDate = StartDate;
	}   
	public Date getFinishDate() {
		return this.FinishDate;
	}

	public void setFinishDate(Date FinishDate) {
		this.FinishDate = FinishDate;
	}
   
}
