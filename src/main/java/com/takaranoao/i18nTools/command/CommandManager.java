package com.takaranoao.i18nTools.command;

import com.takaranoao.i18nTools.Main;
import com.takaranoao.i18nTools.command.impl.CommandFindJavaFiles;
import com.takaranoao.i18nTools.command.impl.CommandHelp;
import com.takaranoao.i18nTools.i18n.I18n;

import java.util.*;

public class CommandManager {
    private static final Map<String, Command> commandImpl = new HashMap<>();
    private static final String DEFAULT_COMMAND = "help";

    public static void init() {
        commandImpl.put("help", new CommandHelp());
        commandImpl.put("list_java", new CommandFindJavaFiles());
    }

    public static void onCommand(String[] args) {
        if(commandImpl.isEmpty())init();

        ArrayList<String> list = new ArrayList<>(Arrays.asList(args));
        String command = list.remove(0).toLowerCase();
        if (!commandImpl.containsKey(command)) {
            if (!commandImpl.containsKey(DEFAULT_COMMAND)) {
                Main.LOGGER.info(I18n.get("command.default.not_found"));
                return;
            }
            Main.LOGGER.info(I18n.get("command.default.info"));
            onCommand(commandImpl.get(DEFAULT_COMMAND), DEFAULT_COMMAND);
            return;
        }
        onCommand(commandImpl.get(command),command, list);
    }

    private static void onCommand(Command commandImpl, String name) {
        onCommand(commandImpl, name, new ArrayList<>());
    }

    private static void onCommand(Command commandImpl, String name, ArrayList<String> args) {
        commandImpl.exec(name, new CommandSender(), args);
    }
}
