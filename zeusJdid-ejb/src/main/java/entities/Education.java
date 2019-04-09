package entities;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.sql.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Education
 *
 */
@Entity

public class Education implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer Id;
	private String specialite;
	private String School;
	private Date Datedeb;
	private Date Datefin;
	private String Degree;
	@ManyToOne
	private User user;
	
	private static final long serialVersionUID = 1L;

	public Education() {
		super();
	}   
	public Integer getId() {
		return this.Id;
	}

	public void setId(Integer Id) {
		this.Id = Id;
	}   
	public String getSpecialite() {
		return this.specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}   
	public String getSchool() {
		return this.School;
	}

	public void setSchool(String School) {
		this.School = School;
	}   
	public Date getDatedeb() {
		return this.Datedeb;
	}

	public void setDatedeb(Date Datedeb) {
		this.Datedeb = Datedeb;
	}   
	public Date getDatefin() {
		return this.Datefin;
	}

	public void setDatefin(Date Datefin) {
		this.Datefin = Datefin;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getDegree() {
		return Degree;
	}
	public void setDegree(String degree) {
		Degree = degree;
	}
   
}
