package by.htp.it.controller;

import java.util.HashMap;
import java.util.Map;

import by.htp.it.controller.impl.AddComment;
import by.htp.it.controller.impl.AddNews;
import by.htp.it.controller.impl.AddToFavorites;
import by.htp.it.controller.impl.AfterAuthorization;
import by.htp.it.controller.impl.AutorizationUser;
import by.htp.it.controller.impl.ChangeLocal;
import by.htp.it.controller.impl.ChangePassword;
import by.htp.it.controller.impl.DeleteFromFavorites;
import by.htp.it.controller.impl.DeleteNews;
import by.htp.it.controller.impl.DoNotPublish;
import by.htp.it.controller.impl.EditNews;
import by.htp.it.controller.impl.Exit;
import by.htp.it.controller.impl.GoToAddNewsPage;
import by.htp.it.controller.impl.GoToAllNewsPage;
import by.htp.it.controller.impl.GoToAuthorizationPage;
import by.htp.it.controller.impl.GoToChangePasswordPage;
import by.htp.it.controller.impl.GoToCheckOfferedNewsPage;
import by.htp.it.controller.impl.GoToEditNewsPage;
import by.htp.it.controller.impl.GoToMainPage;
import by.htp.it.controller.impl.GoToOfferNewsPage;
import by.htp.it.controller.impl.GoToPublish;
import by.htp.it.controller.impl.GoToRegistrationPage;
import by.htp.it.controller.impl.OfferNews;
import by.htp.it.controller.impl.OpenProfile;
import by.htp.it.controller.impl.ReadAllNews;
import by.htp.it.controller.impl.ReadNews;
import by.htp.it.controller.impl.RegistrationNewUser;
import by.htp.it.controller.impl.UnknowCommand;
import by.htp.it.controller.impl.ViewFavoriteNews;
import by.htp.it.controller.impl.ViewMyOfferedNews;
import by.htp.it.controller.impl.ViewOfferedNews;

public class CommandProvider {

	private Map<CommandName, Command> commands = new HashMap<>();

	public CommandProvider() {
		commands.put(CommandName.AUTHORIZATION_PAGE, GoToAuthorizationPage.getInstance());
		commands.put(CommandName.REGISTRATION_PAGE, GoToRegistrationPage.getInstance());
		commands.put(CommandName.REGISTRATION_NEW_USER, RegistrationNewUser.getInstance());
		commands.put(CommandName.AUTHORIZATION_USER, AutorizationUser.getInstance());
		commands.put(CommandName.UNKNOWN_COMMAND, UnknowCommand.getInstance());
		commands.put(CommandName.GO_TO_MAIN_PAGE, GoToMainPage.getInstance());
		commands.put(CommandName.GO_TO_ADD_NEWS_PAGE, GoToAddNewsPage.getInstance());
		commands.put(CommandName.AFTER_AUTHORIZATION, AfterAuthorization.getInstance());
		commands.put(CommandName.CHANGE_LOCAL, ChangeLocal.getInstance());
		commands.put(CommandName.ADD_NEWS, AddNews.getInstance());
		commands.put(CommandName.GO_TO_EDIT_NEWS_PAGE, GoToEditNewsPage.getInstance() );
		commands.put(CommandName.EXIT, Exit.getInstance());
		commands.put(CommandName.READ_NEWS, ReadNews.getInstance());
		commands.put(CommandName.ADD_TO_FAVORITES, AddToFavorites.getInstance());
		commands.put(CommandName.DELETE_FROM_FAVORITES, DeleteFromFavorites.getInstance());
		commands.put(CommandName.ADD_COMMENT, AddComment.getInstance());
		commands.put(CommandName.OPEN_PROFILE, OpenProfile.getInstance());
		commands.put(CommandName.VIEW_FAVORITE_NEWS, ViewFavoriteNews.getInstance());
		commands.put(CommandName.VIEW_OFFERED_NEWS, ViewOfferedNews.getInstance());
		commands.put(CommandName.GO_TO_OFFER_NEWS_PAGE, GoToOfferNewsPage.getInstance());
		commands.put(CommandName.VIEW_MY_OFFERED_NEWS, ViewMyOfferedNews.getInstance());
		commands.put(CommandName.CHANGE_PASSWORD, ChangePassword.getInstance());
		commands.put(CommandName.READ_ALL_NEWS, ReadAllNews.getInstance());
		commands.put(CommandName.GO_TO_ALL_NEWS_PAGE, GoToAllNewsPage.getInstance() );
		commands.put(CommandName.GO_TO_CHANGE_PASSWORD_PAGE, GoToChangePasswordPage.getInstance());
		commands.put(CommandName.DELETE_NEWS, DeleteNews.getInstance());
		commands.put(CommandName.EDIT_NEWS, EditNews.getInstance());
		commands.put(CommandName.OPEN_PROFILE, OpenProfile.getInstance());
		commands.put(CommandName.GO_TO_PUBLISH, GoToPublish.getInstance());
		commands.put(CommandName.GO_TO_CHECK_OFFERED_NEWS_PAGE, GoToCheckOfferedNewsPage.getInstance() );
		commands.put(CommandName.DO_NOT_PUBLISH, DoNotPublish.getInstance() );
		commands.put(CommandName.OFFER_NEWS, OfferNews.getInstance());
		
		
	}

	public Command findCommand(String name) {
		if (name == null) {
			name = CommandName.UNKNOWN_COMMAND.toString();
		}
		CommandName commandName;
		try {
			commandName = CommandName.valueOf(name.toUpperCase());
		} catch (IllegalArgumentException e) {
			commandName = CommandName.UNKNOWN_COMMAND;
		}
		Command command = commands.get(commandName);
		return command;
	}

}
