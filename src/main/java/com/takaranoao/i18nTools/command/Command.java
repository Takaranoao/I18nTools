package com.takaranoao.i18nTools.command;

import com.takaranoao.i18nTools.command.exception.BadCommandException;
import com.takaranoao.i18nTools.command.exception.CommandException;
import com.takaranoao.i18nTools.i18n.I18n;

import java.util.ArrayList;

public abstract class Command {

    public abstract void exec(String name, CommandSender sender, ArrayList<String> args) throws CommandException;

    public void needArgs(ArrayList<String> args, int minArgs) throws BadCommandException {
        if (args.size() < minArgs) {
            throw new BadCommandException(I18n.get("command.need_more_arg"));
        }
    }
}
