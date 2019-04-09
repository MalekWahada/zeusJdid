package entities;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.sql.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: certification
 *
 */
@Entity

public class certification implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer Id;
	private String name;
	private String field;
	private Date Dateissue;
	private String centrecertif;
	@ManyToOne
	private User user;
	private static final long serialVersionUID = 1L;

	public certification() {
		super();
	}   
	public Integer getId() {
		return this.Id;
	}

	public void setId(Integer Id) {
		this.Id = Id;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public String getField() {
		return this.field;
	}

	public void setField(String field) {
		this.field = field;
	}   
	public Date getDateissue() {
		return this.Dateissue;
	}

	public void setDateissue(Date Dateissue) {
		this.Dateissue = Dateissue;
	}   
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public String getCentrecertif() {
		return centrecertif;
	}
	public void setCentrecertif(String centrecertif) {
		this.centrecertif = centrecertif;
	}
   
}
