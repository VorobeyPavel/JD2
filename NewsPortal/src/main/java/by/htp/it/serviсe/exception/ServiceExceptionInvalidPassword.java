package by.htp.it.serviсe.exception;

public class ServiceExceptionInvalidPassword extends ServiceException {

	private static final long serialVersionUID = 1L;
	
	public ServiceExceptionInvalidPassword() {
		super();
	}

	public ServiceExceptionInvalidPassword(String message) {
		super(message);
	}

	public ServiceExceptionInvalidPassword(Exception e) {
		super(e);
	}

	public ServiceExceptionInvalidPassword(String message, Exception e) {
		super(message, e);
	}
	

}
