package com.takaranoao.i18nTools.command.impl;

import com.takaranoao.i18nTools.command.Command;
import com.takaranoao.i18nTools.command.CommandSender;
import com.takaranoao.i18nTools.command.exception.CommandException;
import com.takaranoao.i18nTools.utils.I18nFileUtils;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;

public class CommandFindJavaFiles extends Command {
    @Override
    public void exec(String name, CommandSender sender, ArrayList<String> args) throws CommandException {
        needArgs(args,1);
        Path path = FileSystems.getDefault().getPath(args.get(0));
        for (Path p : I18nFileUtils.listJavaFile(path)) {
            sender.sendMessage(p.toString());
        }
    }
}
