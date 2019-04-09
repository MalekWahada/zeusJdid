package entities;

import java.util.List;
import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.sql.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity

public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	private String Email;
	private String Password;
	private String Firstname;
	private String Lastname;
	private Date BirthDate;
	private String bio;
	private String AccountConfirmation;
	private String PictureURL;
	private String ActualOccupation;
	private String entreschool;
	@OneToMany(mappedBy = "user",orphanRemoval=true,cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Skill> Skills;
	@OneToMany(mappedBy = "user",orphanRemoval=true,cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<ProfessionalExp> professionalexps;

	@OneToMany(mappedBy = "friendsent",orphanRemoval=true,cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Friend> friendsents;
    @OneToMany(mappedBy="friendreciver",orphanRemoval=true,cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Friend> friendrecivers;
    @OneToMany(mappedBy="user",orphanRemoval=true,cascade=CascadeType.ALL)
    private List<Education> educations;
    @OneToMany(mappedBy="user",orphanRemoval=true,cascade=CascadeType.ALL)
    private List<certification> certifications;
	@OneToMany(mappedBy = "Vieweruser",orphanRemoval=true,cascade=CascadeType.ALL)
	private List<View> viewerusers;
    @OneToMany(mappedBy="Vieweduser",orphanRemoval=true,cascade=CascadeType.ALL)
	private List<View> viewedusers;	

	/// malek mapped entities///////////////////

	//// post & comments
	@OneToMany(mappedBy = "user")
	private List<Comments> lComments;
	@OneToMany(mappedBy = "user",orphanRemoval=true,cascade=CascadeType.ALL)
	private List<LikedPost> lLikedPosts;
	@OneToMany(mappedBy = "user")
	private List<Post> lPosts;
	//// message
	@OneToMany(mappedBy = "userTransmitter")
	private List<Message> lTransmiiters;
	@OneToMany(mappedBy = "userReceiver")
	private List<Message> lReceivers;
	////  notification
	@OneToMany(mappedBy = "userSenderNtf")
	private List<Notification> lNotificationsSent;
	@OneToMany(mappedBy = "userReceiverNtf")
	private List<Notification> lNotificationsReceived;

	///////////////   amine mapped entities  ///////////
	
	@OneToMany(mappedBy = "user")
	private List<Follow> listFollow;  //
	@OneToMany(mappedBy = "user")
	private List<Application> listApplication; 
	@OneToMany(mappedBy = "user")
	private List<Interview> listInterview; 
	
	////////////////////////////////////////////

	public List<Friend> getFriendsents() {
		return friendsents;
	}

	public void setFriendsents(List<Friend> friendsents) {
		this.friendsents = friendsents;
	}

	public List<Friend> getFriendrecivers() {
		return friendrecivers;
	}

	public void setFriendrecivers(List<Friend> friendrecivers) {
		this.friendrecivers = friendrecivers;
	}

	public List<Follow> getListFollow() {
		return listFollow;
	}

	public void setListFollow(List<Follow> listFollow) {
		this.listFollow = listFollow;
	}

	public List<LikedPost> getlLikedPosts() {
		return lLikedPosts;
	}

	public List<Post> getlPosts() {
		return lPosts;
	}

	public void setlPosts(List<Post> lPosts) {
		this.lPosts = lPosts;
	}

	public void setlLikedPosts(List<LikedPost> lLikedPosts) {
		this.lLikedPosts = lLikedPosts;
	}

	public List<Message> getlTransmiiters() {
		return lTransmiiters;
	}

	public void setlTransmiiters(List<Message> lTransmiiters) {
		this.lTransmiiters = lTransmiiters;
	}

	public List<Message> getlReceivers() {
		return lReceivers;
	}

	public void setlReceivers(List<Message> lReceivers) {
		this.lReceivers = lReceivers;
	}

	public List<Skill> getSkills() {
		return Skills;
	}

	public void setSkills(List<Skill> skills) {
		Skills = skills;
	}

	public List<ProfessionalExp> getProfessionalexps() {
		return professionalexps;
	}

	public void setProfessionalexps(List<ProfessionalExp> professionalexps) {
		this.professionalexps = professionalexps;
	}

	public List<Comments> getlComments() {
		return lComments;
	}

	public void setlComments(List<Comments> lComments) {
		this.lComments = lComments;
	}

	///////////////////////////////////////
	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}

	public Integer getId() {
		return this.Id;
	}

	public void setId(Integer Id) {
		this.Id = Id;
	}

	public String getEmail() {
		return this.Email;
	}

	public void setEmail(String Email) {
		this.Email = Email;
	}

	public String getPassword() {
		return this.Password;
	}

	public void setPassword(String Password) {
		this.Password = Password;
	}

	public String getFirstname() {
		return this.Firstname;
	}

	public void setFirstname(String Firstname) {
		this.Firstname = Firstname;
	}

	public String getLastname() {
		return this.Lastname;
	}

	public void setLastname(String Lastname) {
		this.Lastname = Lastname;
	}

	public Date getBirthDate() {
		return this.BirthDate;
	}

	public void setBirthDate(Date BirthDate) {
		this.BirthDate = BirthDate;
	}

	public String getBio() {
		return this.bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getAccountConfirmation() {
		return this.AccountConfirmation;
	}

	public void setAccountConfirmation(String AccountConfirmation) {
		this.AccountConfirmation = AccountConfirmation;
	}

	public String getPictureURL() {
		return this.PictureURL;
	}

	public void setPictureURL(String PictureURL) {
		this.PictureURL = PictureURL;
	}

	public String getActualOccupation() {
		return ActualOccupation;
	}

	public void setActualOccupation(String actualOccupation) {
		ActualOccupation = actualOccupation;
	}

	public String getEntreschool() {
		return entreschool;
	}

	public void setEntreschool(String entreschool) {
		this.entreschool = entreschool;
	}

	public List<Application> getListApplication() {
		return listApplication;
	}

	public void setListApplication(List<Application> listApplication) {
		this.listApplication = listApplication;
	}

	public List<Interview> getListInterview() {
		return listInterview;
	}

	public void setListInterview(List<Interview> listInterview) {
		this.listInterview = listInterview;
	}

	public List<Education> getEducations() {
		return educations;
	}

	public void setEducations(List<Education> educations) {
		this.educations = educations;
	}

	public List<certification> getCertifications() {
		return certifications;
	}

	public void setCertifications(List<certification> certifications) {
		this.certifications = certifications;
	}

	public List<View> getViewerusers() {
		return viewerusers;
	}

	public void setViewerusers(List<View> viewerusers) {
		this.viewerusers = viewerusers;
	}

	public List<View> getViewedusers() {
		return viewedusers;
	}

	public void setViewedusers(List<View> viewedusers) {
		this.viewedusers = viewedusers;
	}

	public List<Notification> getlNotificationsSent() {
		return lNotificationsSent;
	}

	public void setlNotificationsSent(List<Notification> lNotificationsSent) {
		this.lNotificationsSent = lNotificationsSent;
	}

	public List<Notification> getlNotificationsReceived() {
		return lNotificationsReceived;
	}

	public void setlNotificationsReceived(List<Notification> lNotificationsReceived) {
		this.lNotificationsReceived = lNotificationsReceived;
	}

	@Override
	public String toString() {
		return "User [Id=" + Id + ", Email=" + Email + ", Password=" + Password + ", Firstname=" + Firstname
				+ ", Lastname=" + Lastname + ", bio=" + bio + "]";
	}

}
