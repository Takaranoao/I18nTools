package com.takaranoao.i18nTools.command.impl;

import com.takaranoao.i18nTools.command.Command;
import com.takaranoao.i18nTools.command.CommandSender;
import com.takaranoao.i18nTools.command.exception.BadCommandException;
import com.takaranoao.i18nTools.utils.I18nFileUtils;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;

public class CommandGetJavaFileStr extends Command {
    @Override
    public void exec(String name, CommandSender sender, ArrayList<String> args) throws BadCommandException {
        needArgs(args,1);
        Path path = FileSystems.getDefault().getPath(args.get(0));
        I18nFileUtils.getJavaFileString(path).forEach(
                sender::sendMessage
        );
    }
}
