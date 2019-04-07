package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import models.UserLog;

public class Logs implements Serializable, PropertyChangeListener{

	private ArrayList<UserLog> logs;
	private Map<String, ArrayList<UserLog>> maplogs = new HashMap<String, ArrayList<UserLog>>();
	
	public Logs() {
		this.logs = new ArrayList<UserLog>();
	}

	public void propertyChange(PropertyChangeEvent e) {
		this.logs.add((UserLog) e.getNewValue());
		this.maplogs.put(e.getPropertyName(), this.logs);
	}
	
	public ArrayList<String> getSentenceFromDatabaseAndUser(String database, String user){
		ArrayList<UserLog> u = this.maplogs.get(database);
		ArrayList<String> u2 = new ArrayList<String>();
		for (UserLog userLog : u) {
			if(userLog.getUsername().equals(user)) {
				u2.add(userLog.getSentence() + ", " + userLog.getHexecute() + ", " + userLog.getTquery() + " || ");
			}
		}
		return u2;
	}
	
	public ArrayList<String> getSentence(String database, String user, String type){
		ArrayList<UserLog> u = this.maplogs.get(database);
		ArrayList<String> u2 = new ArrayList<String>();
		for (UserLog userLog : u) {
			if(userLog.getUsername().equals(user) && userLog.getTquery().equalsIgnoreCase(type)) {
				u2.add(userLog.getSentence() + ", " + userLog.getHexecute() + " || ");
			}
		}
		return u2;
	}

	public ArrayList<String> getSentenceFromDatabaseAndType(String database, String type) {
		ArrayList<UserLog> u = this.maplogs.get(database);
		ArrayList<String> u2 = new ArrayList<String>();
		for (UserLog userLog : u) {
			if(userLog.getTquery().equalsIgnoreCase(type)) {
				System.out.println(userLog.getSentence());
				u2.add(userLog.getSentence() + ", " + userLog.getHexecute() + ", " + userLog.getUsername() + " || ");
			}
		}
		return u2;
	}
	
	
}
