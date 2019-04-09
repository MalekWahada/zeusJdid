package entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Interview
 *
 */
@Entity

public class Interview implements Serializable {

	   
	@Id
	private int id;
	private Date timing;
	
	@ManyToOne
	@JoinColumn(name="idEntrepriseTeam", referencedColumnName="Id")
	private EntrepriseTeam entrepriseTeam;
	@ManyToOne
	@JoinColumn(name="idUser", referencedColumnName="Id")
	private User user;
	
	private static final long serialVersionUID = 1L;

	public Interview() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public Date getTiming() {
		return this.timing;
	}

	public void setTiming(Date timing) {
		this.timing = timing;
	}
   
}
