package by.htp.it.dao.exception;

public class DAOExceptionInvalidPassword extends DAOException {

	private static final long serialVersionUID = 1L;
	
	public DAOExceptionInvalidPassword() {
		super();
	}

	public DAOExceptionInvalidPassword(String message) {
		super(message);
	}

	public DAOExceptionInvalidPassword(Exception e) {
		super(e);
	}

	public DAOExceptionInvalidPassword(String message, Exception e) {
		super(message, e);
	}

}
