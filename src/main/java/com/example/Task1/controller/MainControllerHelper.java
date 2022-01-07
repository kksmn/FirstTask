package com.example.Task1.controller;

import com.example.Task1.сommand.ICommand;
import com.example.Task1.сommand.impl.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

import static java.util.logging.Level.ALL;

public class MainControllerHelper {
    private static MainControllerHelper instance = null;
    private static final String COMMAND = "command";

    HashMap<String, ICommand> commands = new HashMap<String, ICommand>();

    private MainControllerHelper() {
        commands.put("addBook", new CommandAddBook());
        commands.put("addReader", new CommandAddReader());
        commands.put("findBook", new CommandFindBook());
        commands.put("getBook", new CommandGetBook());
        commands.put("getBookTable", new CommandGetBookTable());
        commands.put("getReader", new CommandGetReader());
        commands.put("getReaderTable", new CommandGetReaderTable());
        commands.put("returnBook", new CommandReturnBook());
    }

    public ICommand getCommand(HttpServletRequest request) {
        ICommand command = null;
        command = commands.get(request.getParameter(COMMAND));
        return command;
    }
    public static MainControllerHelper getInstance() {
        if (instance == null) {
            instance = new MainControllerHelper();
        }
        return instance;
    }
}
