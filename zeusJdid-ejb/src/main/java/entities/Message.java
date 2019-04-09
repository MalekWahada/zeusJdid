package entities;

import java.io.Serializable;
import java.lang.String;
import java.sql.Timestamp;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Message
 *
 */
@Entity

public class Message implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idMessage;
	
	////////////////////  mapping /////////////////
    @ManyToOne
    @JoinColumn(name="idTransmitter", referencedColumnName="id")
	private User userTransmitter;
    @ManyToOne
    @JoinColumn(name="idReceiver", referencedColumnName="id")
    private User userReceiver;
	
	//////////////////////////////////////////////
	
	private String content;
	private Timestamp timeMessage;
	
 
	
	public User getUserTransmitter() {
		return userTransmitter;
	}
	public void setUserTransmitter(User userTransmitter) {
		this.userTransmitter = userTransmitter;
	}
	public User getUserReceiver() {
		return userReceiver;
	}
	public void setUserReceiver(User userReceiver) {
		this.userReceiver = userReceiver;
	}

	private static final long serialVersionUID = 1L;

	public Message() {
		super();
	}   
	public int getIdMessage() {
		return this.idMessage;
	}

	public void setIdMessage(int idMessage) {
		this.idMessage = idMessage;
	}   
	
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}   
	public Timestamp getTimeMessage() {
		return this.timeMessage;
	}

	public void setTimeMessage(Timestamp timeMessage) {
		this.timeMessage = timeMessage;
	}
   
}
