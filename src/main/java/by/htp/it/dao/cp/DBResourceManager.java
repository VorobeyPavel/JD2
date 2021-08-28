package by.htp.it.dao.cp;

import java.util.ResourceBundle;

public class DBResourceManager {

	private final static String DB_RESOURCE = "by.htp.it.dao.cp.db";
	
	private final static DBResourceManager instance = new DBResourceManager();
	
	private ResourceBundle resourceBundle = ResourceBundle.getBundle(DB_RESOURCE);

	public static DBResourceManager getInstance() {
		return instance;
	}

	public String getValue(String key) {
		return resourceBundle.getString(key);
	}
}
