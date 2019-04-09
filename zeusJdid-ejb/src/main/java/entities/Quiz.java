package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Quiz
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Quiz implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private int id;
	private int score;
	private String name;
	@OneToOne(mappedBy="quiz")
	private EmplomentOffer emplomentOffer;
	private static final long serialVersionUID = 1L;
	

	@OneToMany(mappedBy="quiz",cascade=CascadeType.PERSIST)
	private List<Question> listQuestion=new ArrayList<>();
	
	public List<Question> getListQuestion() {
		return listQuestion;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void setListQuestion(List<Question> listQuestion) {
		this.listQuestion = listQuestion;
	}
	
	public Quiz() {
		super();
	}   
	public Quiz(String name){
	   this.name=name;
	}
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	public void addQuestion(Question dep){
		dep.setQuiz(this);
		this.listQuestion.add(dep);
	}
	
}
