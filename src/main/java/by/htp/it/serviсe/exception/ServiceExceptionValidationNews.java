package by.htp.it.serviсe.exception;

import java.util.List;

public class ServiceExceptionValidationNews extends ServiceException {

	private static final long serialVersionUID = 1L;

	private List<String> errors;

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public List<String> getErrors() {
		return errors;
	}

	public ServiceExceptionValidationNews(List<String> errors) {
		this.errors = errors;
	}

	public ServiceExceptionValidationNews(Exception e, List<String> errors) {

		super(e);
		this.errors = errors;
	}

	public ServiceExceptionValidationNews(String string) {
		super();
	}

}
