package entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Post
 *
 */
@Entity

public class Post implements Serializable {

	   
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPost;
	@ManyToOne
	private User user;
	private Timestamp postTime;
	
	///////////////// mapping ////////////

	@OneToMany(mappedBy="post")
	private List<Comments> lComments;
	@OneToMany(mappedBy="post",orphanRemoval=true,cascade=CascadeType.ALL)
	private List<LikedPost> likedPosts;
	
	/////////////////////////////////////
	
	public List<Comments> getlComments() {
		return lComments;
	}
	public void setlComments(List<Comments> lComments) {
		this.lComments = lComments;
	}
	public List<LikedPost> getLikedPosts() {
		return likedPosts;
	}
	public void setLikedPosts(List<LikedPost> likedPosts) {
		this.likedPosts = likedPosts;
	}

	private static final long serialVersionUID = 1L;

	public Post() {
		super();
	}   
	public int getIdPost() {
		return this.idPost;
	}

	public void setIdPost(int idPost) {
		this.idPost = idPost;
	}   
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Timestamp getPostTime() {
		return postTime;
	}
	public void setPostTime(Timestamp postTime) {
		this.postTime = postTime;
	}
	
   
}
