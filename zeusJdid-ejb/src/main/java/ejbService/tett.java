package ejbService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.EmplomentOffer;
import entities.Post;
import entities.Skill;
import entities.StatUser;
import entities.User;

/**
 * Session Bean implementation class tett
 */
@Stateless
@LocalBean
public class tett implements tettRemote, tettLocal {
	@PersistenceContext(unitName = "zeusPI-ejb")
	EntityManager em;

	/**
	 * Default constructor.
	 */
    public tett() {
        // TODO Auto-generated constructor stub
    }
	public StatUser addStat(StatUser u) {
		// TODO Auto-generated method stub
		
		em.persist(u);
		em.flush();

		return u;
	}
	public  StatUser updateStat(StatUser u) {
		// TODO Auto-generated method stub
		
		em.clear();
		return em.merge(u);
	}
	public ArrayList<EmplomentOffer> findallJobs() {
		// TODO Auto-generated method stub 
		ArrayList<EmplomentOffer> result = (ArrayList<EmplomentOffer>) em.createQuery("from EmplomentOffer").getResultList();
		return result;
	}

	@Override
	public  ArrayList<Skill> getSkill(int iduser) {
		// TODO Auto-generated method stub
		ArrayList<Skill> result = (ArrayList<Skill>) em.createQuery("from Skill where user =" +iduser,Skill.class).getResultList();
		return result;
	}
	
	public String findjobskill(String S) {
		// TODO Auto-generated method stub 
		Query qr =  em.createQuery("select e.JobDescr from EmplomentOffer e where e.JobTitle ='" + S +  "'");
		
		return (String) qr.getSingleResult();
		
		
	}
	public  ArrayList<Skill> getallskills() {
		// TODO Auto-generated method stub
		ArrayList<Skill> result = (ArrayList<Skill>) em.createQuery("from Skill group by Name" ,Skill.class).getResultList();
		return result;
	}
	public HashMap<String,Long> countskill() {
		// TODO Auto-generated method stub
		ArrayList<Skill> result = (ArrayList<Skill>) em.createQuery("from Skill group by Name").getResultList();
		HashMap<String,Long> hmap = new HashMap<String,Long>();
		
		for(int i=0;i<result.size();i++)
		{
			Long j = (Long)em.createQuery(" select count(*) from Skill  where name = '"+result.get(i).getName()+"'").getSingleResult();
			hmap.put(result.get(i).getName(),j );
		}
		return hmap;
	}
	public Long findnb() {
		// TODO Auto-generated method stub 
		Query qr =  em.createQuery("select count(*) from Skill");
		
		return (Long) qr.getSingleResult();
		
		
	}
	
}
