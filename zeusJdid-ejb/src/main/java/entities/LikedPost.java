package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: LikedPost
 *
 */
@Entity

public class LikedPost implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idLk;
	
	///////  mapping //////////////
	
    @ManyToOne
    @JoinColumn(name="idUser", referencedColumnName="id")
    private User user;

    @ManyToOne
    @JoinColumn(name="idPost", referencedColumnName="idPost")
    private Post post;
	
	
	///////////////////////////////
	private static final long serialVersionUID = 1L;

	public LikedPost() {
		super();
	}   
	public int getIdLk() {
		return this.idLk;
	}

	public void setIdLk(int idLk) {
		this.idLk = idLk;
	}   
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}   
	public Post getPost() {
		return this.post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
   
}
