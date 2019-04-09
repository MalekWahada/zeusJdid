package ejbService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Remote;

import entities.EmplomentOffer;
import entities.Post;
import entities.Skill;
import entities.StatUser;

@Remote
public interface tettRemote {
	StatUser addStat(StatUser u);
	StatUser updateStat(StatUser u);
	String findjobskill(String S);
	ArrayList<EmplomentOffer> findallJobs();
	ArrayList<Skill> getSkill(int iduser);
	ArrayList<Skill> getallskills();
	HashMap<String,Long> countskill();
	Long findnb();
}
