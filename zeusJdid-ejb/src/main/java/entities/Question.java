package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Question
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Question implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private int id;
	private String question;
	private String correct;
	@ManyToOne
	private Quiz quiz;
	
	@OneToMany(mappedBy="question",cascade=CascadeType.PERSIST)
	private List<Reponse> listReponse=new ArrayList<>();
	
	public List<Reponse> getListReponse() {
		return listReponse;
	}
	public void setListReponse(List<Reponse> listReponse) {
		this.listReponse = listReponse;
	}

	private static final long serialVersionUID = 1L;

	public Quiz getQuiz() {
		return quiz;
	}
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	public Question() {
		super();
	}   
	
	public Question(String Question,String correct) {
		this.question=Question;
		this.correct=correct;
	}   
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}   
	public String getCorrect() {
		return this.correct;
	}

	public void setCorrect(String correct) {
		this.correct = correct;
	}
	
	public void addReponse(Reponse dep){
		dep.setQuestion(this);
		this.listReponse.add(dep);
	}
	
   
}
