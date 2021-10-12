package by.htp.it.serviсe.exception;

public class ServiceExceptionValidationComment extends ServiceException {

	private static final long serialVersionUID = 1L;

	public ServiceExceptionValidationComment() {
		super();
	}

	public ServiceExceptionValidationComment(String message) {
		super(message);
	}

	public ServiceExceptionValidationComment(Exception e) {
		super(e);
	}

	public ServiceExceptionValidationComment(String message, Exception e) {
		super(message, e);
	}

}
