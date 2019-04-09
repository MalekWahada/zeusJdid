package ejbService;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.Education;
import entities.Entreprise;
import entities.Follow;
import entities.Friend;
import entities.LikedPost;
import entities.Post;
import entities.ProfessionalExp;
import entities.Skill;
import entities.User;
import entities.View;
import entities.certification;

/**
 * Session Bean implementation class UserServiceEjb
 */
@Stateless
@LocalBean
public class UserServiceEjb implements UserServiceEjbRemote, UserServiceEjbLocal {

	
	@PersistenceContext(unitName = "zeusPI-ejb")
	EntityManager em;
	
    /**
     * Default constructor. 
     */
    public UserServiceEjb() {
        // TODO Auto-generated constructor stub
    }
    
	public User addUser(User u) {
		// TODO Auto-generated method stub
		
		em.persist(u);
		em.flush();

		return u;
	}

	@Override
	public User Loginbymailpwd(String email, String pwd) {
					
		User user=new User();
		try{
			TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.Email = :email  AND u.Password = :pwd",User.class);
		 user = query.setParameter("email", email).setParameter("pwd", pwd).getSingleResult();}
		catch (NoResultException e) {
			// TODO: handle exception
		}
		if(user.getId()==null)
				return null;
		
		return user;
	}
	
/*	public User updateUser(User u) {
		// TODO Auto-generated method stub
		User u1=em.find(User.class, u.getId());
	u1.setAccountConfirmation(true);
	em.clear();
		return em.merge(u1);
	} */

	public User updateUser(User u) {
		// TODO Auto-generated method stub
		

	em.clear();
		return em.merge(u);
	}
	
	//////////////////////////////////////////////Skill Services//////////////////////////////////////////////////////////
	public Skill AddSkill(Skill s) {
		em.persist(s);
		em.flush();

		return s;
	}	


	
	@Override
	public void deleteSkills(Skill s) {
		// TODO Auto-generated method stub

		em.flush();
		em.clear();
		
		Query query = em.createQuery("delete from Skill where Id="+s.getId());
		query.executeUpdate();	
	}
	@Override
	public Skill updateskill(Skill s) {
		// TODO Auto-generated method stub
		

	em.clear();
		return em.merge(s);
	}
	
	//////////////////////////////////////////////Exp prof Services//////////////////////////////////////////////////////////
	public ProfessionalExp AddProfessionalExp(ProfessionalExp pe) {
		em.persist(pe);
		em.flush();

		return pe;
	}	
	@Override
	public void deleteProfessionalExp(ProfessionalExp p) {
		// TODO Auto-generated method stub

		em.flush();
		em.clear();
		Query query = em.createQuery("delete from ProfessionalExp where Id ="+p.getId());
		query.executeUpdate();
		
	}
	@Override
	public ProfessionalExp updateprof(ProfessionalExp p) {
		// TODO Auto-generated method stub
		

	em.clear();
		return em.merge(p);
	}
	
//////////////////////////////////////////////Skill Services//////////////////////////////////////////////////////////
	@Override
public Education Addeducation(Education educ) {
em.persist(educ);
em.flush();

return educ;
}	
@Override
public void deleteeduc(Education e) {
	// TODO Auto-generated method stub

	em.flush();
	em.clear();

	Query query = em.createQuery("delete from Education where Id ="+e.getId());
	query.executeUpdate();	
}
@Override
public Education updateUser(Education e) {
	// TODO Auto-generated method stub
	

em.clear();
	return em.merge(e);
}

//////////////////////////////////////////////Exp prof Services//////////////////////////////////////////////////////////
@Override 
public certification Addcertification(certification cert) {
em.persist(cert);
em.flush();

return cert;
}	
	
@Override
public void deletecertif(certification cert) {
	// TODO Auto-generated method stub

	em.flush();
	em.clear();
	em.remove(em.find(certification.class, cert.getId()));
	Query query = em.createQuery("delete from certification where Id ="+cert.getId());
	query.executeUpdate();		
}
@Override
public certification updatecert(certification c) {
	// TODO Auto-generated method stub
	

em.clear();
	return em.merge(c);
}

//////////////////////////////////////////////fiend prof Services//////////////////////////////////////////////////////////
@Override 
public Friend Addfriend(Friend f) {
em.persist(f);
em.flush();

return f;
}	

@Override
public void deleteFriend(Friend f) {
// TODO Auto-generated method stub

em.flush();
em.clear();
em.remove(em.find(Friend.class, f.getId()));
Query query = em.createQuery("delete from Friend where Id ="+f.getId());
query.executeUpdate();		
}
@Override
public Friend updateFriend(Friend f) {
// TODO Auto-generated method stub


em.clear();
return em.merge(f);
}
@Override
public ArrayList<Friend> verifFrinds(User u1,User u2) {
	// TODO Auto-generated method stub

	
	ArrayList<Friend>	friends = (ArrayList<Friend>) em.createQuery("FROM  Friend  WHERE (idfriendsent=" + u1.getId()+"AND friendreciver="+u2.getId()+") OR (idfriendsent=" + u2.getId()+"AND friendreciver="+u1.getId()+")").getResultList();

		


	return friends;

}
@Override
public ArrayList<Friend> allmyfriends(User u1) {
	// TODO Auto-generated method stub

	
	ArrayList<Friend>	friends = (ArrayList<Friend>) em.createQuery("FROM  Friend  WHERE (idfriendsent=" + u1.getId()+" AND accepted='friend') OR (accepted='friend' AND friendreciver="+u1.getId()+")").getResultList();

		


	return friends;

}
public ArrayList<Friend> allmyfriendsrequests(User u1) {
	// TODO Auto-generated method stub

	
	ArrayList<Friend>	friends = (ArrayList<Friend>) em.createQuery("FROM  Friend  WHERE accepted='invitation' AND friendreciver="+u1.getId()+" ORDER BY daterecept DESC").getResultList();

	return friends;

}

public ArrayList<Friend> allmyfriendsrequestsent(User u1) {
	// TODO Auto-generated method stub

	
	ArrayList<Friend>	friends = (ArrayList<Friend>) em.createQuery("FROM  Friend  WHERE accepted='invitation' AND idfriendsent="+u1.getId()+" ORDER BY daterecept DESC").getResultList();

	return friends;

}

//////////////////////////////////////////////Exp prof Services//////////////////////////////////////////////////////////
@Override 
public View AddView(View v) {
em.persist(v);
em.flush();

return v;
}	

public ArrayList<View> allmyView(User u1) {
	// TODO Auto-generated method stub

	
	ArrayList<View>	Views = (ArrayList<View>) em.createQuery("FROM  View Vieweduser="+u1.getId()+"ORDER BY daterecept DESC").getResultList();

	return Views;

}
////////////////////////////////follow///////////////////////////////
@Override
public ArrayList<Follow> allmyfollow(User u1) {
	// TODO Auto-generated method stub

	
	ArrayList<Follow>	follows = (ArrayList<Follow>) em.createQuery("FROM  Follow  WHERE idUser=" + u1.getId()).getResultList();

		


	return follows;

}

@Override 
public Follow Addfollow(Follow f) {
em.persist(f);
em.flush();

return f;
}	

@Override
public void deleteFollow(Follow f) {
// TODO Auto-generated method stub

em.flush();
em.clear();
em.remove(em.find(Follow.class, f.getId()));
Query query = em.createQuery("delete from Follow where Id ="+f.getId());
query.executeUpdate();		
}

public ArrayList<Follow> verifFollow(Entreprise e, User u1) {
	// TODO Auto-generated method stub

	
	ArrayList<Follow>	follow = (ArrayList<Follow>) em.createQuery("FROM  Follow  WHERE idUser=" + u1.getId()+" AND idEntreprise="+e.getId()).getResultList();
		


	return follow;

}

/////////////////////////////////////////////////suggestions_friend//////////////////////////////
public ArrayList<User> suggestionfriends(User u) {
	// TODO Auto-generated method stub
	ArrayList<User> usersnf=new ArrayList<>();
	///////////////////////par ville ou travail actu////////////////////////
/*	ArrayList<User>	users = (ArrayList<User>) em.createQuery("FROM  User  WHERE  (UPPER(entreschool) = UPPER('"+u.getEntreschool()+"') OR (UPPER(ville) = UPPER('"+u.getVille()+"') OR (UPPER(pays) = UPPER('"+u.getPays()+"'))  AND Id<>"+u.getId()).getResultList();
	
	if(users.size()!=0)
		for(User uvf:users)
		{
			if(verifFrinds(u, uvf).size()==0)
				usersnf.add(uvf);
		} 
		*/
ArrayList<User>	users1 = (ArrayList<User>) em.createQuery("FROM  User  WHERE  UPPER(entreschool) = UPPER('"+u.getEntreschool()+"') AND Id<>"+u.getId()).getResultList();
	
	if(users1.size()!=0)
		for(User uvf1:users1)
		{	Integer verifu=new Integer(0);
			if(verifFrinds(u, uvf1).size()==0){
				for(User verifo:usersnf){
				if (verifo.getId()==uvf1.getId())
				verifu=1;
			}
				if(verifu==0)
				usersnf.add(uvf1);}
		} 
	
ArrayList<User>	users2 = (ArrayList<User>) em.createQuery("FROM  User  WHERE  UPPER(ville) = UPPER('"+u.getVille()+"') AND Id<>"+u.getId()).getResultList();
	
	if(users2.size()!=0)
		for(User uvf2:users2)
		{	Integer verifu=new Integer(0);
			if(verifFrinds(u, uvf2).size()==0){
				for(User verifo:usersnf){
				if (verifo.getId()==uvf2.getId())
				verifu=1;
			}
				if(verifu==0)
				usersnf.add(uvf2);}
		} 
ArrayList<User>	users3 = (ArrayList<User>) em.createQuery("FROM  User  WHERE  UPPER(pays) = UPPER('"+u.getPays()+"') AND Id<>"+u.getId()).getResultList();
	
	if(users3.size()!=0)
		for(User uvf3:users3)
		{	Integer verifu=new Integer(0);
			if(verifFrinds(u, uvf3).size()==0){
				for(User verifo:usersnf){
				if (verifo.getId()==uvf3.getId())
				verifu=1;
			}
				if(verifu==0)
				usersnf.add(uvf3);}
		} 
///////////////////////par skill////////////////////////
	if (u.getSkills().size()!=0){
	for(Skill s:u.getSkills() ){
		
		ArrayList<Skill>	Skillsuggest = (ArrayList<Skill>) em.createQuery("FROM  Skill  WHERE UPPER(Name) = UPPER('"+s.getName()+"') AND user<>"+u.getId()).getResultList();
		if (Skillsuggest.size()!=0){
		for (Skill s2:Skillsuggest){
			Integer verifu=new Integer(0);
			if(verifFrinds(u, s2.getUser()).size()==0)
				for(User verifo:usersnf){
					if (verifo.getId()==s2.getId())
					verifu=1;
				}
				
				if(verifu==0)
				usersnf.add(s2.getUser());	}}
			
		}       }
	
	
	

	
	
///////////////////////experience////////////////////////
if (u.getProfessionalexps().size()!=0)
for(ProfessionalExp prfexp:u.getProfessionalexps() ){

ArrayList<ProfessionalExp>	ProfessionalExplist = (ArrayList<ProfessionalExp>) em.createQuery("FROM  ProfessionalExp  WHERE UPPER(EntrepriseName) = UPPER('"+prfexp.getEntrepriseName()+"') AND user<>"+u.getId()).getResultList();
if (ProfessionalExplist.size()!=0){
for (ProfessionalExp pe:ProfessionalExplist){
	Integer verifu=new Integer(0);
if(verifFrinds(u, pe.getUser()).size()==0)
	for(User verifo:usersnf){
		if (verifo.getId()==pe.getUser().getId())
		verifu=1;
	}
if(verifu==0)
	usersnf.add(pe.getUser());	

}



}
	
	}
///////////////////////////5edmou fil entreprise ili y5dm fiha taw ////////////////////////////////
ArrayList<ProfessionalExp>	ProfessionalExpactlist = (ArrayList<ProfessionalExp>) em.createQuery("FROM  ProfessionalExp  WHERE UPPER(EntrepriseName) = UPPER('"+u.getEntreschool()+"') AND user<>"+u.getId()).getResultList();

if (ProfessionalExpactlist.size()!=0){
	for (ProfessionalExp pe:ProfessionalExpactlist){
		Integer verifu=new Integer(0);
	if(verifFrinds(u, pe.getUser()).size()==0)
		for(User verifo:usersnf){
			if (verifo.getId()==pe.getUser().getId())
			verifu=1;
		}
	if(verifu==0)
		usersnf.add(pe.getUser());	

	}



}
///////////////////////education////////////////////////
if (u.getEducations().size()!=0)
for(Education educ:u.getEducations() ){

ArrayList<Education> educationlist = (ArrayList<Education>) em.createQuery("FROM  Education  WHERE UPPER(School) = UPPER('"+educ.getSchool()+"') AND user<>"+u.getId()).getResultList();
if (educationlist.size()!=0){
	for (Education ed:educationlist)
	{
		Integer verifu=new Integer(0);
		if(verifFrinds(u, ed.getUser()).size()==0)
			for(User verifo:usersnf){
				if (verifo.getId()==ed.getUser().getId())
				verifu=1;
			}
			
			if(verifu==0)
			usersnf.add(ed.getUser());	}}
}
	







///////////////////////par skill////////////////////////
if (u.getCertifications().size()!=0){
for(certification c:u.getCertifications() ){

ArrayList<certification>	certifsuggest = (ArrayList<certification>) em.createQuery("FROM  certification  WHERE UPPER(Name) = UPPER('"+c.getName()+"') AND user<>"+u.getId()).getResultList();
if (certifsuggest.size()!=0){
for (certification c2:certifsuggest)
{
	Integer verifu=new Integer(0);
	if(verifFrinds(u, c2.getUser()).size()==0)
		for(User verifo:usersnf){
			if (verifo.getId()==c2.getUser().getId())
			verifu=1;
		}
		
		if(verifu==0)
		usersnf.add(c2.getUser());	}}
}}


	return usersnf;

}

/////////////////////////////////////////////////suggestions_follow//////////////////////////////
public ArrayList<Entreprise> suggestionentreprise(User u) {
// TODO Auto-generated method stub
	ArrayList<Entreprise> entrenofoll=new ArrayList<>();
ArrayList<User> usersnf=new ArrayList<>();
///////////////////////par ville ou travail actu////////////////////////
/*	ArrayList<User>	users = (ArrayList<User>) em.createQuery("FROM  User  WHERE  (UPPER(entreschool) = UPPER('"+u.getEntreschool()+"') OR (UPPER(ville) = UPPER('"+u.getVille()+"') OR (UPPER(pays) = UPPER('"+u.getPays()+"'))  AND Id<>"+u.getId()).getResultList();

if(users.size()!=0)
for(User uvf:users)
{
if(verifFrinds(u, uvf).size()==0)
usersnf.add(uvf);
} 
*/
ArrayList<Entreprise>	entlist = (ArrayList<Entreprise>) em.createQuery("FROM  Entreprise  WHERE  UPPER(name) = UPPER('"+u.getEntreschool()+"') ").getResultList();

if(entlist.size()==0)
	for(Entreprise e:entlist)
	{
		if(verifFollow(e, u).size()==0)
			entrenofoll.add(e);
	} 

ArrayList<Entreprise>	entlist2 = (ArrayList<Entreprise>) em.createQuery("FROM  Entreprise  WHERE  UPPER(adress) LIKE '%UPPER("+u.getVille()+")%'").getResultList();

if (entlist2.size()!=0){
for (Entreprise e2:entlist2)
	{
	Integer everif=new Integer(0);
	if(verifFollow(e2, u).size()==0){
for(Entreprise ev:entrenofoll){
	if (ev.getId()==e2.getId())
		everif=1;
}
	if (everif==0)
	entrenofoll.add(e2);	}
		}
}

       


///////////////////////par skill////////////////////////
if (u.getSkills().size()!=0){
for(Skill s:u.getSkills() ){

ArrayList<Entreprise>entlist3 = (ArrayList<Entreprise>) em.createQuery("FROM  Entreprise  WHERE UPPER(Speciality) = UPPER('"+s.getSpecialite()+"')").getResultList();
if (entlist3.size()!=0){
for (Entreprise e3:entlist3)
	{
	Integer everif=new Integer(0);
	if(verifFollow(e3, u).size()==0){
for(Entreprise ev:entrenofoll){
	if (ev.getId()==e3.getId())
		everif=1;
}
	if (everif==0)
	entrenofoll.add(e3);	}
		}
}

}       }
///////////////////////experience////////////////////////
if (u.getProfessionalexps().size()!=0)
for(ProfessionalExp prfexp:u.getProfessionalexps() ){

ArrayList<Entreprise>	entlist4 = (ArrayList<Entreprise>) em.createQuery("FROM  Entreprise  WHERE UPPER(name) = UPPER('"+prfexp.getEntrepriseName()+"')").getResultList();
if (entlist4.size()!=0){
for (Entreprise e4:entlist4)
	{
	Integer everif=new Integer(0);
	if(verifFollow(e4, u).size()==0){
for(Entreprise ev:entrenofoll){
	if (ev.getId()==e4.getId())
		everif=1;
}
	if (everif==0)
	entrenofoll.add(e4);	}
		}
}

}       
///////////////////////////5edmou fil entreprise ili y5dm fiha taw ////////////////////////////////


///////////////////////education////////////////////////
if (u.getEducations().size()!=0)
for(Education educ:u.getEducations() ){

ArrayList<Entreprise> entlist5 = (ArrayList<Entreprise>) em.createQuery("FROM  Entreprise  WHERE UPPER(name) = UPPER('"+educ.getSchool()+"')").getResultList();
if (entlist5.size()!=0){
for (Entreprise e5:entlist5)
	{
	Integer everif=new Integer(0);
	if(verifFollow(e5, u).size()==0){
for(Entreprise ev:entrenofoll){
	if (ev.getId()==e5.getId())
		everif=1;
}
	if (everif==0)
	entrenofoll.add(e5);	}
		}
}

}  

///////////////////////par skill////////////////////////
if (u.getCertifications().size()!=0)
{
for(certification c:u.getCertifications() ){

ArrayList<Entreprise>	entlist6 = (ArrayList<Entreprise>) em.createQuery("FROM  Entreprise  WHERE UPPER(name) = UPPER('"+c.getCentrecertif()+"') ").getResultList();
if (entlist6.size()!=0){
for (Entreprise e6:entlist6)
	{
	Integer everif=new Integer(0);
	if(verifFollow(e6, u).size()==0){
for(Entreprise ev:entrenofoll){
	if (ev.getId()==e6.getId())
		everif=1;
}
	if (everif==0)
	entrenofoll.add(e6);	}
		}
}

}}  
///////////////////////par skill////////////////////////
if (u.getCertifications().size()!=0)
{
for(certification c:u.getCertifications() ){

ArrayList<Entreprise>	entlist7 = (ArrayList<Entreprise>) em.createQuery("FROM  Entreprise  WHERE UPPER(Speciality) = UPPER('"+c.getField()+"') ").getResultList();
if (entlist7.size()!=0){
for (Entreprise e7:entlist7)
{
Integer everif=new Integer(0);
if(verifFollow(e7, u).size()==0){
for(Entreprise ev:entrenofoll){
if (ev.getId()==e7.getId())
everif=1;
}
if (everif==0)
entrenofoll.add(e7);	}
}
}

}}  

return entrenofoll;

}









}