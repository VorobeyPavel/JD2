package by.myproject.news.controller;

import java.util.HashMap;
import java.util.Map;

import by.myproject.news.controller.impl.AfterAuthorization;
import by.myproject.news.controller.impl.AutorizationUser;
import by.myproject.news.controller.impl.GoToAuthorizationPage;
import by.myproject.news.controller.impl.GoToMainPage;
import by.myproject.news.controller.impl.GoToRegistrationPage;
import by.myproject.news.controller.impl.RegistrationNewUser;
import by.myproject.news.controller.impl.UnknowCommand;
import by.myproject.news.controller.impl.ChangeLocal;

public class CommandProvider {

	private Map<CommandName, Command> commands = new HashMap<>();

	public CommandProvider() {
		commands.put(CommandName.AUTHORIZATION_PAGE, GoToAuthorizationPage.getInstance());
		commands.put(CommandName.REGISTRATION_PAGE, GoToRegistrationPage.getInstance());
		commands.put(CommandName.REGISTRATION_NEW_USER, RegistrationNewUser.getInstance());
		commands.put(CommandName.AUTHORIZATION_USER, AutorizationUser.getInstance());
		commands.put(CommandName.UNKNOWN_COMMAND, UnknowCommand.getInstance());
		commands.put(CommandName.GO_TO_MAIN_PAGE, GoToMainPage.getInstance());
		commands.put(CommandName.AFTER_AUTHORIZATION, AfterAuthorization.getInstance());
		commands.put(CommandName.CHANGE_LOCAL, ChangeLocal.getInstance());
		
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
