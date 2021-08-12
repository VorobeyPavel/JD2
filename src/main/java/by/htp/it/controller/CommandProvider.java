package by.htp.it.controller;

import java.util.HashMap;
import java.util.Map;

import by.htp.it.controller.impl.AfterAuthorization;
import by.htp.it.controller.impl.AutorizationUser;
import by.htp.it.controller.impl.ChangeLocal;
import by.htp.it.controller.impl.GoToAuthorizationPage;
import by.htp.it.controller.impl.GoToMainPage;
import by.htp.it.controller.impl.GoToRegistrationPage;
import by.htp.it.controller.impl.RegistrationNewUser;
import by.htp.it.controller.impl.UnknowCommand;

public class CommandProvider {

	private Map<CommandName, Command> commands = new HashMap<>();

	public CommandProvider() {
		commands.put(CommandName.AUTHORIZATION_PAGE, GoToAuthorizationPage.getInstance());
		commands.put(CommandName.REGISTRATION_PAGE, GoToRegistrationPage.getInstance());
		commands.put(CommandName.REGISTRATION_NEW_USER, RegistrationNewUser.getInstance());
		commands.put(CommandName.AUTHORIZATION_USER, AutorizationUser.getInstance());
		commands.put(CommandName.UNKNOWN_COMMAND, UnknowCommand.getInstance());
		commands.put(CommandName.GO_TO_MAIN_PAGE, GoToMainPage.getInstance());
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
