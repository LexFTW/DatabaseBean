package connections.mysql;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import connections.IConnection;
import models.UserLog;

public class MySQL implements Serializable, IConnection{

	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private String ip, port, database, user, password;
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public MySQL() {
		this.ip = "localhost";
		this.port = "3306";
		this.database = "unknown";
		this.user = "root";
		this.password = "";
		System.out.println("[INFO] - Por defecto, se puso una base de datos desconocida, es recomendable que la introduzcas más adelante para su correcto funcionamiento.");
	}

	public MySQL(String ip, String port, String database, String user, String password) {
		this.ip = ip;
		this.port = port;
		this.database = database;
		this.user = user;
		this.password = password;
		
		if(!this.connection()) {
			System.err.println("[ERROR] - No se pudo establecer conexión");
		}else {
			System.out.println("[INFO] - Conexión Establecida");
		}
	}

	public boolean connection() {
		Map<String, String> persistenceMap = new HashMap<String, String>();
		persistenceMap.put("javax.persistence.jdbc.url", "jdbc:mysql://"+this.ip+":"+this.port+"/"+this.database+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
		persistenceMap.put("javax.persistence.jdbc.user", this.user);
		persistenceMap.put("javax.persistence.jdbc.password", this.password);
		persistenceMap.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
		
		this.emf = Persistence.createEntityManagerFactory("connection", persistenceMap);
		this.em = this.emf.createEntityManager();
		
		if(this.em.isOpen()) {
			return true;
		}
		
		return false;
	}

	public boolean insert(String query) {
		UserLog userlog = new UserLog();
		this.em.getTransaction().begin();
		Query q = this.em.createNativeQuery(query);
		int result = q.executeUpdate();
		this.em.getTransaction().commit();
		
		if(result >= 1) {
			userlog.setUsername(this.emf.getProperties().get("hibernate.connection.username").toString());
			userlog.setHexecute(Calendar.getInstance());
			userlog.setTquery("INSERT");
			userlog.setNumRegis(result);
			userlog.setSentence(query);
			this.pcs.firePropertyChange(this.database, null, userlog);
			return true;
		}
		
		return false;
	}

	public boolean delete(String query) {
		UserLog userlog = new UserLog();
		this.em.getTransaction().begin();
		Query q = this.em.createNativeQuery(query);
		int result = q.executeUpdate();
		this.em.getTransaction().commit();
		
		if(result >= 1) {
			userlog.setUsername(this.emf.getProperties().get("hibernate.connection.username").toString());
			userlog.setHexecute(Calendar.getInstance());
			userlog.setTquery("DELETE");
			userlog.setNumRegis(result);
			userlog.setSentence(query);
			this.pcs.firePropertyChange(this.database, null, userlog);
			return true;
		}
		
		return false;
	}
	
	public boolean update(String query) {
		UserLog userlog = new UserLog();
		this.em.getTransaction().begin();
		Query q = this.em.createNativeQuery(query);
		int result = q.executeUpdate();
		this.em.getTransaction().commit();
		
		if(result >= 1) {
			userlog.setUsername(this.emf.getProperties().get("hibernate.connection.username").toString());
			userlog.setHexecute(Calendar.getInstance());
			userlog.setTquery("UPDATE");
			userlog.setNumRegis(result);
			userlog.setSentence(query);
			this.pcs.firePropertyChange(this.database, null, userlog);
			return true;
		}
		
		return false;
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.removePropertyChangeListener(listener);
	}

	public boolean callProcedure(String query) {
		this.em.getTransaction().begin();
		Query q = this.em.createNativeQuery("{"+query+"}");
		q.executeUpdate();
		this.em.getTransaction().commit();
		return true;
	}

	
	public void close() {
		this.em.close();
		this.emf.close();
	}

	public String getIp() {
		return ip;
	}

	public String getPort() {
		return port;
	}

	public String getDatabase() {
		return database;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
