package by.htp.it.serviсe.exception;

public class ServiceExceptionValidationPassword extends ServiceException {

	private static final long serialVersionUID = 1L;

	public ServiceExceptionValidationPassword() {
		super();
	}

	public ServiceExceptionValidationPassword(String message) {
		super(message);
	}

	public ServiceExceptionValidationPassword(Exception e) {
		super(e);
	}

	public ServiceExceptionValidationPassword(String message, Exception e) {
		super(message, e);
	}

}
