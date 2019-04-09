package entities;

import java.io.Serializable;
import java.lang.Integer;
import java.sql.Timestamp;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: View
 *
 */
@Entity

public class View implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer Id;
	@ManyToOne
    @JoinColumn(name="idveweruser", referencedColumnName="id")
	private User Vieweruser;
	@ManyToOne
    @JoinColumn(name="idveweduser", referencedColumnName="id")
	private User Vieweduser;
	private Timestamp DateView;
	private static final long serialVersionUID = 1L;

	public View() {
		super();
	}   
	public Integer getId() {
		return this.Id;
	}

	public void setId(Integer Id) {
		this.Id = Id;
	}   
	public User getVieweruser() {
		return this.Vieweruser;
	}

	public void setVieweruser(User Vieweruser) {
		this.Vieweruser = Vieweruser;
	}   
	public User getVieweduser() {
		return this.Vieweduser;
	}

	public void setVieweduser(User Vieweduser) {
		this.Vieweduser = Vieweduser;
	}   
	public Timestamp getDateView() {
		return this.DateView;
	}

	public void setDateView(Timestamp DateView) {
		this.DateView = DateView;
	}
   
}
