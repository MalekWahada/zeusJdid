package entities;

import java.io.Serializable;
import java.lang.String;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Comments
 *
 */
@Entity

public class Comments implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idComment;

    ///////// mapping ///////////////
    @ManyToOne
    @JoinColumn(name="idUser", referencedColumnName="id")
    private User user;

    @ManyToOne
    @JoinColumn(name="idPost", referencedColumnName="idPost")
    private Post post;

    /////////////////////////////////
	
	private String content;
	private Timestamp timeComment;

	private static final long serialVersionUID = 1L;

	public Comments() {
		super();
	}   
	public int getIdComment() {
		return this.idComment;
	}

	public void setIdComment(int idComment) {
		this.idComment = idComment;
	}   

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}   
	public Timestamp getTimeComment() {
		return this.timeComment;
	}

	public void setTimeComment(Timestamp timeComment) {
		this.timeComment = timeComment;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}

   
}
