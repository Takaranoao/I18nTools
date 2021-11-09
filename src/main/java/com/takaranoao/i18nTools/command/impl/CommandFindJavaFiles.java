package com.takaranoao.i18nTools.command.impl;

import com.takaranoao.i18nTools.command.Command;
import com.takaranoao.i18nTools.command.CommandSender;
import com.takaranoao.i18nTools.i18n.I18n;
import com.takaranoao.i18nTools.utils.I18nFileUtils;

import java.util.ArrayList;

public class CommandFindJavaFiles extends Command {
    @Override
    public void exec(String name, CommandSender sender, ArrayList<String> args) {
        if (args.size() <= 0) {
            sender.sendMessage(I18n.get("command.find_java_files.need_more_arg"));
            return;
        }
        for (String s : I18nFileUtils.listJavaFile(args.get(0))) {
            sender.sendMessage(s);
        }
    }
}
