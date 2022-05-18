package by.htp.it.dao.exception;

public class DAOExceptionLoginOrEmailExists extends DAOException {

	private static final long serialVersionUID = 1L;

	public DAOExceptionLoginOrEmailExists() {
		super();
	}

	public DAOExceptionLoginOrEmailExists(String message) {
		super(message);
	}

	public DAOExceptionLoginOrEmailExists(Exception e) {
		super(e);
	}

	public DAOExceptionLoginOrEmailExists(String message, Exception e) {
		super(message, e);
	}
	
	
}
