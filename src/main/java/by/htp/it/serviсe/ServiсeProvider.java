package by.htp.it.serviсe;


import by.htp.it.serviсe.impl.NewsServiсeImpl;
import by.htp.it.serviсe.impl.UserServiсeImpl;

public final class ServiсeProvider {
	private static final ServiсeProvider INSTANCE = new ServiсeProvider();
	
	private UserServiсe userServise = new UserServiсeImpl();
	private NewsServiсe newsServise = new NewsServiсeImpl();

	private ServiсeProvider() {
		
	}
	
	public static ServiсeProvider getInstance() {
		return INSTANCE;
	}

	public UserServiсe getUserServise() {
		return userServise;
	}
	
	public NewsServiсe getNewsServise() {
		return newsServise;
	}
}
