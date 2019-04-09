package ejbService;

import java.util.ArrayList;

import javax.ejb.Remote;

import entities.Education;
import entities.Entreprise;
import entities.Follow;
import entities.Friend;
import entities.ProfessionalExp;
import entities.Skill;
import entities.User;
import entities.View;
import entities.certification;

@Remote
public interface UserServiceEjbRemote {

	User addUser(User u);
	public User Loginbymailpwd(String email, String pwd);
	public User updateUser(User u);
	public Skill AddSkill(Skill s);
	public ProfessionalExp AddProfessionalExp(ProfessionalExp pe);
	public Education Addeducation(Education educ);
	public certification Addcertification(certification cert);

	void deleteSkills(Skill s);
	void deleteProfessionalExp(ProfessionalExp p);
	void deleteeduc(Education e);
	void deletecertif(certification cert);
	ProfessionalExp updateprof(ProfessionalExp p);
	Education updateUser(Education e);
	Skill updateskill(Skill s);
	certification updatecert(certification c);
	Friend Addfriend(Friend f);
	void deleteFriend(Friend f);
	Friend updateFriend(Friend f);
	
	public ArrayList<Friend> verifFrinds(User u1,User u2);
	ArrayList<Friend> allmyfriends(User u1);
	public ArrayList<Friend> allmyfriendsrequests(User u1);
	View AddView(View v);
	public ArrayList<Friend> allmyfriendsrequestsent(User u1);
	ArrayList<Follow> allmyfollow(User u1);

	Follow Addfollow(Follow f);
	void deleteFollow(Follow f);
	public ArrayList<Follow> verifFollow(Entreprise e, User u1);
	public ArrayList<User> suggestionfriends(User u);
	public ArrayList<Entreprise> suggestionentreprise(User u);
}
