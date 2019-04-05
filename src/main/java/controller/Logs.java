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
	
	
}
