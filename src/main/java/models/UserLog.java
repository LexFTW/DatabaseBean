package models;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.Calendar;

public class UserLog{

	private String username, tquery, sentence;
	private int numRegis;
	private Calendar hexecute;
	
	public UserLog() {
		super();
	}
	

	public UserLog(String username, String tquery, String sentence, int numRegis, Calendar hexecute) {
		super();
		this.username = username;
		this.tquery = tquery;
		this.numRegis = numRegis;
		this.hexecute = hexecute;
		this.sentence = sentence;
	}


	public String getUsername() {
		return username;
	}

	public String getTquery() {
		return tquery;
	}
	
	public String getSentence() {
		return sentence;
	}

	public int getNumRegis() {
		return numRegis;
	}

	public String getHexecute() {
		return hexecute.get(Calendar.HOUR_OF_DAY) + ":" + hexecute.get(Calendar.MINUTE) + ":" + hexecute.get(Calendar.SECOND) + " " + hexecute.get(Calendar.DAY_OF_MONTH) + "/" + hexecute.get(Calendar.MONTH) + "/" + hexecute.get(Calendar.YEAR);
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setTquery(String tquery) {
		this.tquery = tquery;
	}
	
	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public void setNumRegis(int numRegis) {
		this.numRegis = numRegis;
	}

	public void setHexecute(Calendar hexecute) {
		this.hexecute = hexecute;
	}

	@Override
	public String toString() {
		return "UserLog [username=" + username + ", tquery=" + tquery + ", sentence=" + sentence + ", numRegis=" + numRegis + ", hexecute="
				+ hexecute.get(Calendar.HOUR_OF_DAY) + ":" + hexecute.get(Calendar.MINUTE) + ":" + hexecute.get(Calendar.SECOND) + " " + hexecute.get(Calendar.DAY_OF_MONTH) + "/" + hexecute.get(Calendar.MONTH) + "/" + hexecute.get(Calendar.YEAR)  + "]";
	}

}
