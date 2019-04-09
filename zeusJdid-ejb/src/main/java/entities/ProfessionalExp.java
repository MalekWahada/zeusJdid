package entities;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.sql.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: professionalExp
 *
 */
@Entity

public class ProfessionalExp implements Serializable {

	   
	@Id
	private Integer Id;
	private String EntrepriseName;
	private String jobname;
	private String jobdescription;
	private Date datedebut;
	private Date datefin;
    @ManyToOne
	private User user;
	private static final long serialVersionUID = 1L;

	public ProfessionalExp() {
		super();
	}   
	public Integer getId() {
		return this.Id;
	}

	public void setId(Integer Id) {
		this.Id = Id;
	}   
	public String getEntrepriseName() {
		return this.EntrepriseName;
	}

	public void setEntrepriseName(String EntrepriseName) {
		this.EntrepriseName = EntrepriseName;
	}   
	public String getJobname() {
		return this.jobname;
	}

	public void setJobname(String jobname) {
		this.jobname = jobname;
	}   
	public String getJobdescription() {
		return this.jobdescription;
	}

	public void setJobdescription(String jobdescription) {
		this.jobdescription = jobdescription;
	}   
	public Date getDatedebut() {
		return this.datedebut;
	}

	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}   
	public Date getDatefin() {
		return this.datefin;
	}

	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}
   
}
