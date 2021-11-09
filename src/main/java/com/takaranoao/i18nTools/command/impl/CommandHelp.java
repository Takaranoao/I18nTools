package com.takaranoao.i18nTools.command.impl;

import com.takaranoao.i18nTools.command.Command;
import com.takaranoao.i18nTools.command.CommandSender;
import com.takaranoao.i18nTools.i18n.I18n;

import java.util.ArrayList;

public class CommandHelp extends Command {

    @Override
    public void exec(String name, CommandSender sender, ArrayList<String> args) {
        sender.sendMessage(I18n.get("command.help"));
    }
}
