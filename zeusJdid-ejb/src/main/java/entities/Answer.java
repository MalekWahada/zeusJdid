package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Answer
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Answer implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private int id;
	private int UserID;
	private int QuizID;
	private int score;
	private static final long serialVersionUID = 1L;

	public Answer() {
		super();
	} 
	
	public Answer(int quizId,int userId,int score) {
		QuizID=quizId;
		UserID=quizId;
		this.score=score;
	}  
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public int getUserID() {
		return this.UserID;
	}

	public void setUserID(int UserID) {
		this.UserID = UserID;
	}   
	public int getQuizID() {
		return this.QuizID;
	}

	public void setQuizID(int QuizID) {
		this.QuizID = QuizID;
	}   
	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}
   
}
