package com.takaranoao.i18nTools.command;

import java.util.ArrayList;

public abstract class Command {
    public abstract void exec(String name, CommandSender sender, ArrayList<String> args);

}
